import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import DAO.TestDAO;
import bean.CancelledDTO;

import javax.servlet.annotation.*;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/checkerHome.jsp";
        HttpSession session = req.getSession(true);
        ServletContext application = getServletContext();
        CancelledDTO cancelled_number = new CancelledDTO();

        String btn = (String)req.getParameter("btn");

        application.setAttribute("cancelled_number", cancelled_number);
        session.setAttribute("pwd_number", 1);

        TestDAO tdao = new TestDAO();
        tdao.cut();
        tdao.all3();

        // 注文完了テスト用
        if (btn.equals("complete-test")) {
            application.setAttribute("foremost_1", 41);
        }
        // 注文確定待機テスト用
        if (btn.equals("await-test")) {
            tdao.await();
            application.setAttribute("foremost_1", 40);
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
