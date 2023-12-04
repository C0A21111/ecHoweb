<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="idto" scope="application" class="bean.ItemsDTO" />

<html>

<head>
  <title>在庫管理</title>
  <link rel="stylesheet" type="text/css" href="css/checkerOn.css" media="all" id="cssMain">
</head>

<body>

<header>
  <h2>在庫管理</h2>
  <form action="./Certification" method="post" class="header-btn">
      <button class="btn-white-blue">ホームへ戻る</button>
  </form>
</header>

<div class="body-layout">

<div class="box">

<h3>商品の管理（ 追加 / 削除 / 在庫補充 ）</h3>

<p>新規商品追加： 名前・価格・数量を入力</p>
<p>在庫更新： ID・数量を入力</p>
<p>在庫追加： ID・数量を入力</p>
<p>価格更新： ID・価格を入力</p>
<p>商品削除： IDを入力</p>

<form action="./ManageStock" method="post" accept-charset="UTF-8">
  ⅠⅮ　：<input type="text" name="item_id"><br>
  商品名：<input type="text" name="item_name" ><br>
  価格　：<input type="text" name="item_price" ><br>
  数量　：<input type="text" name="item_stock" ><br>
  <button name="btn" value="新規商品追加" class="btn-white-blue">新規商品追加</button>
  <button name="btn" value="在庫更新" class="btn-white-blue">在庫更新</button>
  <button name="btn" value="在庫追加" class="btn-white-blue">在庫追加</button>
  <button name="btn" value="価格更新" class="btn-white-blue">価格更新</button>
  <button name="btn" value="商品削除" class="btn-white-blue">商品削除</button>
</form>

</div>

<table class="table">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>price</th>
    <th>stock</th>
  </tr>
  <%
  for(int i = 0; i < idto.size(); i++){
      ItemsBean ib = idto.get(i);
  %>
    <tr>
      <td><%= ib.getId() %></td>
      <td><%= ib.getName() %></td>
      <td><%= ib.getPrice() %></td>
      <td><%= ib.getStock() %></td>
    </tr>
  <% } %>
</table>

</div>
</div>

<footer></footer>

</body>

</html>
