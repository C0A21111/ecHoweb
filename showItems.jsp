<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="idto" scope="application" class="bean.ItemsDTO" />

<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/items.css" media="all" id="cssMain">
  <title>商品一覧</title>
</head>

<body>

  <header></header>

  <table>
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

  <footer></footer>

</body>

</html>
