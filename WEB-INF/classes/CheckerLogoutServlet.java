import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/Logout")
public class CheckerLogoutServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        req.setCharacterEncoding("utf-8");
        String forwardURL = "/checkerLogin.html";

        if (session.getAttribute("loginpassed") != null) {
            session.removeAttribute("loginpassed");
        }

        // フォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
