import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import DAO.EachOrdersDAO;
import DAO.UsersDAO;

import javax.servlet.annotation.*;
import bean.UsersDTO;
import bean.EachOrdersDTO;

@WebServlet("/Operation")
public class OperationServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/operation.jsp";
        HttpSession session = req.getSession(true);

        // DAOオブジェクトを生成
        UsersDAO udao = new UsersDAO();
        EachOrdersDAO edao = new EachOrdersDAO();
        int foremost_2 = udao.frontMostSelect(2).get(0).getDockedNumber();

        String btn = "";
        // ボタンの値を取得
        if (req.getParameter("btn") != null) {
            btn = req.getParameter("btn");
        }
        // pwd_numberの値を取得
        int pwd_number;
        if (session.getAttribute("pwd_number") == null) {
            session.setAttribute("pwd_number", foremost_2);
        }
        pwd_number = (int) session.getAttribute("pwd_number");

        int display_number = 1;
        switch (btn) {

            case "ジャンプ":
                // フォームに入力された値を取得
                display_number = Integer.parseInt(req.getParameter("jump_number"));
                break;
            case "１つ前へ":
                display_number = pwd_number - 1;
                break;
            case "１つ後へ":
                display_number = pwd_number + 1;
                break;
            case "保留":
                // statusを8:保留に更新
                udao.updateStatus(pwd_number, 8);
                display_number = pwd_number;
                break;
            case "取引完了":
                // statusを3:取引完了に更新
                udao.updateStatus(pwd_number, 3);
                display_number = pwd_number + 1;
                break;
            case "強制完了":
                // statusを3:取引完了に更新
                udao.updateStatus(pwd_number, 3);
                ServletContext application = req.getServletContext();
                application.setAttribute("foremost_1", pwd_number);
                display_number = pwd_number + 1;
                break;
            case "保留中先頭へ":
                // 表示ユーザを保留中の先頭に設定
                display_number = udao.frontMostSelect(8).get(0).getStatus();
                break;
            default:
                // 表示ユーザを会計待ちの先頭に設定
                display_number = 1;
                break;

        }

        // 全件検索
        UsersDTO udto = udao.select("All");
        EachOrdersDTO edto;

        // display_numberをudto.size()と比較し、ユーザが存在しているかを確認
        if ((0 < display_number) && (display_number <= udto.size())) {
            // していればdisplay_numberをsessonへ
            session.setAttribute("pwd_number", display_number);
        } else {
            // していなければ元のdisplay_numberに戻す（sessionから取得しなおす）
            display_number = (int) session.getAttribute("pwd_number");
        }

        edto = edao.select((int) session.getAttribute("pwd_number"));

        String pwd_status = "";
        switch (udto.get(display_number - 1).getStatus()) {
            case 0:
                pwd_status = "選択中";
                break;
            case 1:
                pwd_status = "確定待ち";
                break;
            case 2:
                pwd_status = "会計待ち";
                break;
            case 3:
                pwd_status = "取引完了";
                break;
            case 9:
                pwd_status = "キャンセル済み";
            default:
                pwd_status = "pwd_statusの値が正しく取得できませんでした";
        }

        session.setAttribute("pwd_number", display_number);
        session.setAttribute("edto", edto);
        req.setAttribute("udto", udto);
        req.setAttribute("pwd_status", pwd_status);
        req.setAttribute("foremost_2", foremost_2);

        // JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
