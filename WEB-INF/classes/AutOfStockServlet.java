import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.*;

@WebServlet("/AutOfStock")
public class AutOfStockServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "";

        HttpSession session = req.getSession(true);

        // btnの値を取得
        String btn = req.getParameter("btn");

        switch (btn) {
            case "そのまま購入":
                forwardURL = "/checkSelection.jsp";
                break;

            case "商品を再選択":
                forwardURL = "/selectItem.jsp";
                session.removeAttribute("edto");

                break;
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
