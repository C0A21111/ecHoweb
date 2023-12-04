import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import DAO.UsersDAO;
import bean.CancelledDTO;

import javax.servlet.annotation.*;

@WebServlet("/Cancelled")
public class CancelledServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/cancelled.html";
        HttpSession session = req.getSession(true);
        ServletContext application = req.getServletContext();

        // 機能拡張用
        // String cbtn = (String) session.getAttribute("cbtn");

        // ユーザの状態を「9:キャンセル済み」にする
        int docked_number = (int) session.getAttribute("docked_number");
        UsersDAO udao = new UsersDAO();
        udao.updateStatus(docked_number, 9);

        CancelledDTO cancelled_number = (CancelledDTO) application.getAttribute("cancelled_number");
        cancelled_number.add(docked_number);
        application.setAttribute("cancelled_number", cancelled_number);

        // 各セッション情報の削除
        session.removeAttribute("status");
        session.removeAttribute("docked_number");
        session.removeAttribute("user_name");
        session.removeAttribute("edto");
        session.removeAttribute("adto");

        // JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
