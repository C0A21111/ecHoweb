import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import DAO.UsersDAO;

@WebServlet("/SelectItem")
public class SelectItemServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/selectItem.jsp";
        ServletContext application = req.getServletContext();
        HttpSession session = req.getSession(true);

        if (session.getAttribute("docked_number")==null) {

            // UsersDAO,ItemsDAOオブジェクトを生成
            UsersDAO udao = new UsersDAO();

            // Usersテーブルにユーザを登録
            String user_name = (String) req.getParameter("user_name");
            udao.insert(user_name);

            // applicationスコープの登録人数をカウントアップ
            int n;
            if (application.getAttribute("registered_number") == null) {
                n = 1;
            } else {
                n = (int) application.getAttribute("registered_number");
                n++;
            }
            application.setAttribute("registered_number", n);

            // docked_numberとuser_nameをsessionスコープに格納
            session.setAttribute("status", 0);
            session.setAttribute("docked_number", n);
            session.setAttribute("user_name", user_name);
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
