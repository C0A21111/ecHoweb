import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import DAO.ItemsDAO;
import bean.ItemsDTO;

@WebServlet("/ShowStock")
public class ShowStockServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/manageStock.jsp";

        ServletContext application = req.getServletContext();

        // CheckersDAOオブジェクトを生成
        ItemsDAO idao = new ItemsDAO();

        ItemsDTO idto;
        idto = idao.select();

        application.setAttribute("idto", idto);

        // JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
