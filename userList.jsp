<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="upgdto" scope="session" class="bean.UsersDTO" />
<jsp:useBean id ="page_number" scope="session" type="java.lang.Integer" />

<html>

<head>
  <title>ユーザ取引状況一覧</title>
  <link rel="stylesheet" type="text/css" href="css/checkerOn.css" media="all" id="cssMain">
</head>

<body>

    <header></header>

    <div class="body-layout">

    <form action="./UserList" method="post" accept-charset="UTF-8">
        <button name="btn" value="前20件" class="btn-blue-white">前20件</button>
        <button name="btn" value="確定待ち最前" class="btn-white-blue">確定待ち最前</button>
        <button name="btn" value="会計待ち最前" class="btn-white-blue">会計待ち最前</button>
        <button name="btn" value="次20件"class="btn-blue-white">次20件</button>
    </form>

    <table class="table">
      <tr>
          <th>No.</th>
          <th>Username</th>
          <th>status</th>
      </tr>
      <%
      String status_txt = "";
      int status_n;
      for(int i = 1; i <= upgdto.size(); i++){
          UsersBean ub = upgdto.get(i-1);
      %>
        <tr>
          <td><%= ub.getDockedNumber() %></td>
          <td><%= ub.getUserName() %></td>
          <%
          status_n = ub.getStatus();
          switch (status_n) {
            case 0: status_txt = "" + status_n + "：選択中";      break;
            case 1: status_txt = "" + status_n + "：確定待ち";    break;
            case 2: status_txt = "" + status_n + "：会計待ち";    break;
            case 3: status_txt = "" + status_n + "：取引完了";    break;
            case 9: status_txt = "" + status_n + "：キャンセル済み";    break;
          }%>
          <td><%= status_txt %></td>
        </tr>
      <% } %>
    </table>

    </div>

    <footer></footer>

</body>
</html>
