import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DAO.UsersDAO;

import javax.servlet.annotation.*;
import bean.UsersDTO;

@WebServlet("/UserList")
public class UserListServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "/userList.jsp";

        String btn = "";
        // btnの値を取得
        HttpSession session = req.getSession(true);
        if (req.getParameter("btn") != null) {
            btn = req.getParameter("btn");
        }
        // CheckersDAOオブジェクトを生成
        UsersDAO udao = new UsersDAO();
        UsersDTO udto = udao.select("All");

        // sessionからpage_numberを取得（初回は1とする）
        int page_number;
        if (session.getAttribute("page_number") == null) {
            page_number = 1;
        } else {
            page_number = (int) (session.getAttribute("page_number"));
        }

        // btnの値に応じてpage_numberを更新
        switch (btn) {
            case "前20件":
                if (1 < page_number) {
                    page_number--;
                }
                break;
            case "次20件":
                if (page_number < (int) (udto.size() / 20) + 1) {
                    page_number++;
                }
                break;
            case "確定待ち最前":
                // 確定待ち最前のdocked_numberの属するページ数をpage_numberに入れる
                page_number = udao.frontMostSelect(1).get(0).getDockedNumber() / 20 + 1;
                break;
            case "会計待ち最前":
                // 会計待ち最前のdocked_numberの属するページ数をpage_numberに入れる
                page_number = udao.frontMostSelect(2).get(0).getDockedNumber() / 20 + 1;
                break;
        }

        // 全件検索
        UsersDTO upgdto = udao.pageSelect("page", page_number);
        // sessionスコープにDTOを格納
        session.setAttribute("upgdto", upgdto);
        session.setAttribute("page_number", page_number);

        // JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
