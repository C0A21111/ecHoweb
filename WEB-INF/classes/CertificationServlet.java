import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import DAO.CheckersDAO;

import javax.servlet.annotation.*;

import bean.CancelledDTO;
import bean.CheckersDTO;

@WebServlet("/Certification")
public class CertificationServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        req.setCharacterEncoding("utf-8");
        String forwardURL = "/loginFailed.html";

        // テスト用
        ServletContext application = getServletContext();
        CancelledDTO cancelled_number = new CancelledDTO();
        application.setAttribute("registered_number", 40);
        application.setAttribute("foremost_1", 41);
        application.setAttribute("obj_count", 25);
        application.setAttribute("cancelled_number", cancelled_number);
        session.setAttribute("pwd_number", 1);

        // ログインページから
        if (session.getAttribute("loginpassed") == null) {
            String name = req.getParameter("checker_name");
            String password = req.getParameter("checker_password");
            CheckersDAO cdao = new CheckersDAO();
            CheckersDTO cdto;
            cdto = cdao.login(name, password);
            if (cdto.get(0).getId() != 9999) { // 見つかった
                // id,name,passのArrayListをsessionに格納
                session.setAttribute("loginpassed", cdto.get(0));
                forwardURL = "/checkerHome.jsp";
            }
            // 販売オペレーションページ、在庫管理ページから（ログイン済み）
        } else if (session.getAttribute("loginpassed") != null) {
            forwardURL = "/checkerHome.jsp";
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
