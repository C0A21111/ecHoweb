import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet("/ConfirmCancellation")
public class ConfirmCancellationServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/confirmCancellation.jsp";
        HttpSession session = req.getSession(true);

        String cbtn = (String) req.getParameter("cbtn");

        // 機能拡張用
        // switch (cbtn) {
        // case "selectitem":
        // break;
        // case "checkselection":
        // break;
        // case "autofstock":
        // break;
        // case "awaitacception":
        // break;
        // }

        session.setAttribute("cbtn", cbtn);

        // JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
