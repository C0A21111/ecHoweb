import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import DAO.ItemsDAO;
import DAO.OrdersDAO;
import DAO.UsersDAO;

import bean.AutOfStockBean;
import bean.AutOfStockDTO;
import bean.CancelledDTO;
import bean.EachOrdersBean;
import bean.EachOrdersDTO;
import bean.ItemsDTO;

@WebServlet("/AwaitAcception")
public class AwaitAcceptionServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/awaitAcception.jsp";

        // sessionとapplicationの取得
        ServletContext application = req.getServletContext();
        HttpSession session = req.getSession(true);

        // 確定待ち先頭の番号、自身の整理番号を取得
        int foremost_1 = (int) application.getAttribute("foremost_1");
        int docked_number = (int) session.getAttribute("docked_number");

        // ユーザの状態を1:確定待ちにする
        if ((int) session.getAttribute("status") == 0) {

            UsersDAO udao = new UsersDAO();
            udao.updateStatus(docked_number, 1);
            session.setAttribute("status", 1);

        }

        // 前がキャンセルの場合確定待ち先頭の番号をカウントアップ
        CancelledDTO cancelled_number = (CancelledDTO) application.getAttribute("cancelled_number");
        int cnt = 0;
        for (int i = docked_number - 1; i > 0; i--) {
            if ((cancelled_number.contains(i))) {
                cnt++;
            } else {
                break;
            }
        }

        // 確定待ち先頭の番号が自身の整理番号と一致すれば
        if ((foremost_1 + cnt) == docked_number) {

            // 在庫の確認
            ItemsDTO idto = (ItemsDTO) application.getAttribute("idto");
            EachOrdersDTO edto = (EachOrdersDTO) session.getAttribute("edto");
            AutOfStockDTO adto = new AutOfStockDTO();

            for (int i = 0; i < edto.size(); i++) {
                // 在庫が不足している場合
                if (edto.get(i).getItemCount() > idto.get(edto.get(i).getItemId() - 1).getStock()) {
                    // err_msg[]に商品名、希望個数、在庫数を格納
                    EachOrdersBean eb = edto.get(i);
                    AutOfStockBean ab = new AutOfStockBean();
                    int id = eb.getItemId() - 1;
                    ab.setName(eb.getItemName());
                    ab.setCount(eb.getItemCount());
                    ab.setStock(idto.get(id).getStock());
                    // ユーザの希望個数を在庫数に変更, 小計sebtotalを更新
                    eb.setItemCount(idto.get(id).getStock());
                    eb.setSubtotal(idto.get(id).getPrice() * eb.getItemCount());
                    // 在庫不測のあった商品をdtoに入れる
                    adto.add(ab);
                }
            }

            // 在庫に不足がなければ
            if (adto.size() == 0) {

                OrdersDAO odao = new OrdersDAO();
                ItemsDAO idao = new ItemsDAO();
                for (int i = 0; i < edto.size(); i++) {

                    // カートをDB:Ordersに反映させる
                    int item_id = edto.get(i).getItemId();
                    int item_count = edto.get(i).getItemCount();
                    odao.insert(docked_number, item_id, item_count);

                    // 注文分の商品を在庫から減らす（DB:items, applicationスコープ:idto）
                    // ( 在庫-注文数 )で在庫を上書き
                    int restock = idto.get(i).getStock() - item_count;
                    idao.updateStock(String.valueOf(item_id), String.valueOf(restock));

                    // idtoを再取得（更新）
                    idto = idao.select();
                    application.setAttribute("idto", idto);
                }

                // ユーザの状態を「2:会計待ち」に更新
                UsersDAO udao = new UsersDAO();
                udao.updateStatus(docked_number, 2);

                // 確定待ち先頭の番号をカウントアップ
                application.setAttribute("foremost_1", foremost_1 + cnt + 1);

                // フォワード先を注文完了画面に変更
                forwardURL = "/accepted.jsp";

                // 在庫に不足があれば
            } else {
                session.setAttribute("adto", adto);
                forwardURL = "./autOfStock.jsp";
            }

            // 注文（edto）をsessionにセット
            session.setAttribute("edto", edto);

        } else {
            session.setAttribute("cnt", String.valueOf(cnt));
            session.setAttribute("testfore1", String.valueOf(foremost_1));
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
