import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import bean.EachOrdersBean;
import bean.EachOrdersDTO;
import bean.ItemsDTO;

@WebServlet("/CheckSelection")
public class CheckSelectionServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/checkSelection.jsp";

        ServletContext application = req.getServletContext();
        HttpSession session = req.getSession(true);

        if (session.getAttribute("docked_number") == null) {
            forwardURL = "/certificationFailed.jsp";
        }

        EachOrdersDTO edto = new EachOrdersDTO();

        ItemsDTO idto = (ItemsDTO) application.getAttribute("idto");
        int obj_count = (int) application.getAttribute("obj_count");

        for (int i = 1; i <= obj_count; i++) {
            EachOrdersBean eb = new EachOrdersBean();
            int count = Integer.parseInt((String) req.getParameter("input_" + i));
            if (count > 0) {
                eb.setItemId(i);
                eb.setItemName(idto.get(i - 1).getName());
                eb.setItemPrice(idto.get(i - 1).getPrice());
                eb.setItemCount(count);
                eb.setSubtotal(idto.get(i - 1).getPrice() * count);
                edto.add(eb);
            }
        }

        session.setAttribute("edto", edto);

        // JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
