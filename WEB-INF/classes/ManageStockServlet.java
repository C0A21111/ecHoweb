import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import DAO.ItemsDAO;

import bean.ItemsDTO;

@WebServlet("/ManageStock")
public class ManageStockServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/manageStock.jsp";
        ServletContext application = req.getServletContext();
        // HttpSession session = req.getSession(true);

    // データの更新

        ItemsDAO idao = new ItemsDAO();
        ItemsDTO idto = (ItemsDTO) application.getAttribute("idto");

        // ボタンの値を取得
        String btn = req.getParameter("btn");

        String id = "";
        String name = "";
        String price = "";
        String stock = "";

        if (req.getParameter("item_id") != null) {
            id = (String) req.getParameter("item_id");
        }
        if (req.getParameter("item_name") != null) {
            name = (String) req.getParameter("item_name");
        }
        if (req.getParameter("item_price") != null) {
            price = (String) req.getParameter("item_price");
        }
        if (req.getParameter("item_stock") != null) {
            stock = (String) req.getParameter("item_stock");
        }

        // ボタンの種類による分岐
        switch (btn) {

            case "新規商品追加":
                idao.insert(name, price);
                application.setAttribute("obj_count", idto.size());
                break;

            case "在庫更新":
                idao.updateStock(id, stock);
                break;

            case "在庫追加":
                String restock = String
                        .valueOf(Integer.parseInt(stock) + idto.get(Integer.parseInt(id) - 1).getStock());
                idao.updateStock(id, restock);
                break;

            case "価格更新":
                idao.updatePrice(id, price);
                break;

            case "商品削除":
                idao.delete(id);
                application.setAttribute("obj_count", idto.size());
                break;

        }

    // 在庫一覧の表示

        // 全件検索
        idto = idao.select();
        // リクエストスコープにDTOを格納
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
