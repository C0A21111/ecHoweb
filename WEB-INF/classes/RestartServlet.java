import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import DAO.ItemsDAO;

import bean.CancelledDTO;
import bean.ItemsDTO;

@WebServlet("/Restart")
public class RestartServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "loginFailed.html";

        ServletContext application = getServletContext();
        HttpSession session = req.getSession(true);

        //ボタンの値を取得
        String btn = (String)req.getParameter("btn");

        if (session.getAttribute("loginpassed") != null) {

            forwardURL = "restart.jsp";

            // applicationスコープの調整
            switch (btn) {

                case "稼働":
                    // サービスの状態を示す"sys_status"をtrue(稼働)にする
                    application.setAttribute("sys_status", "true");
                    // itemsテーブルをセット
                    ItemsDAO idao = new ItemsDAO();
                    ItemsDTO idto;
                    idto = idao.select();
                    application.setAttribute("idto", idto);
                    // 商品の数をセット
                    application.setAttribute("obj_count", idto.size());
                    // キャンセル履歴を初期化
                    CancelledDTO cancelled_number = new CancelledDTO();
                    application.setAttribute("cancelled_number", cancelled_number);
                    // 登録人数を初期化
                    application.setAttribute("registered_number", 40);
                    // 確定待機先頭の整理番号を初期化
                    application.setAttribute("foremost_1", 41);
                    break;

                case "停止":
                    // サービスの状態を示す"sys_status"をfalse(停止)にする
                    application.setAttribute("sys_status", "false");
                    // applicationスコープの各オブジェクトを消去
                    application.removeAttribute("idto");
                    application.removeAttribute("obj_count");
                    application.removeAttribute("cancelled_number");
                    application.removeAttribute("registered_number");
                    application.removeAttribute("foremost_1");
                    break;

            }
        }

        // JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
