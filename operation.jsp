<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*, java.util.*, pnw.checker.*, pnw.common.*" %>

<jsp:useBean id ="udto" scope="request" class="bean.UsersDTO" />
<jsp:useBean id ="edto" scope="session" class="bean.EachOrdersDTO" />
<jsp:useBean id ="pwd_number" scope="session" type="java.lang.Integer" />
<jsp:useBean id ="pwd_status" scope="request" class="java.lang.String" />
<jsp:useBean id ="foremost_1" scope="application" type="java.lang.Integer" />
<jsp:useBean id ="foremost_2" scope="request" type="java.lang.Integer" />
<jsp:useBean id ="registered_number" scope="application" type="java.lang.Integer" />

<html>

<head>
    <meta charset="UTF-8">
    <title>販売オペレーション</title>
    <link rel="stylesheet" type="text/css" href="css/checkerOn.css" media="all" id="cssMain">
</head>

<body>

<header>
  <h2>販売オペレーション</h2>
  <form action="./Certification" method="post" class="header-btn">
      <button class="btn-white-blue">ホームへ戻る</button>
  </form>
</header>

<div class="body-layout"><div class="center">
<div class="body-left">

<div class="box-jump">
<p>整理番号指定で直接移動</p>
<form action="./Operation" method="post" accept-charset="UTF-8">
  <input type="text" name="jump_number" required class="form-size"><br>
  <input type="submit" name="btn" value="ジャンプ" class="btn-blue-white">
</form>
</div>

<%-- ユーザ取引状況一覧（別タブ）ボタン --%>
<div>
  <form action="./UserList" method="post" target="_blank" rel="noopener noreferrer" accept-charset="UTF-8">
    <button class="btn-white-blue">ユーザ取引状況一覧（別タブ）</button>
  </form>
</div>

<%-- Usersテーブル --%>
<table class="users-table-mini">
  <tr>
      <th>No</th>
      <th>name</th>
      <th>status</th>
  </tr>
  <%
  String status_txt = "";
  int status_n;
  for(int i = Math.max(foremost_2 -3, 1); i <= Math.min(foremost_1 +2, registered_number); i++){
      UsersBean ub = udto.get(i-1);
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

<div class="body-right">
<div class="box-operation">

<%-- 一つ前へボタン --%>
<%-- 待機中の最前へボタン --%>
<%-- 一つ後へボタン --%>
<form action="./Operation" method="post" accept-charset="UTF-8" class="form-operate">
  <button name="btn" class="btn-white-blue btn-operate" value="１つ前へ">１つ前へ</button>
  <button name="btn" class="btn-white-blue btn-operate">会計待ちの先頭へ</button>
  <button name="btn" class="btn-white-blue btn-operate" value="１つ後へ">１つ後へ</button>
</form>

<%-- ニックネームの取得 --%>
<% String user_name = udto.get(pwd_number-1).getUserName(); %>
<%-- 整理番号とニックネームの表示 --%>
<p><b><%= pwd_number %>：　<%= user_name %></b></p><br>
<p><b>取引状況：　<%= pwd_status %></b></p>

<%-- EachOrdersテーブル --%>
<table class="eachorders-table">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>price</th>
    <th>count</th>
    <th>subtotal</th>
  </tr>
  <% 
  int total =0;
  for(int i = 0; i < edto.size(); i++) {
      EachOrdersBean eb = edto.get(i);
  %>
    <tr>
      <td><%= eb.getItemId() %></td>
      <td><%= eb.getItemName() %></td>
      <td><%= eb.getItemPrice() %></td>
      <td><%= eb.getItemCount() %></td>
      <%
      int subtotal = eb.getSubtotal(); 
      total += subtotal;
      %>
      <td><%= subtotal %></td>
    </tr>
  <% } %>
</table>

<%-- 合計金額の表示 --%>
<table border="0" cellspacing="1" bgcolor="red">
  <p bgcolor="white">合計金額は<strong><font color="red"><%=total%></font>円</strong>です</p>
</table>

<script>
  function checkExecute() {
    if (window.confirm('※　強制的に取引を完了します．\n　　よろしいですか？')) {
      return true;
    }
    else {
      window.alert('キャンセルされました');
      return false;
    }
  }
</script>

<%-- 取引完了ボタン --%>
<% if (pwd_status!="取引完了" & pwd_status!="キャンセル済み") {%>
  <% if (pwd_status=="会計待ち") {%>
    <form action="./Operation" method="post" accept-charset="UTF-8">
      <button name="btn" value="取引完了" class="btn-blue-white">取引完了</button>
    </form>
  <%} else {%>
    <form action="./Operation" method="post" accept-charset="UTF-8" onSubmit="return checkExecute()">
      <button name="btn" value="強制完了" class="btn-red">強制完了</button>
    </form>
  <%}%>
<%}%>

</div>
</div>
</div></div>

<footer></footer>

</body>

</html>