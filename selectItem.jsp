<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="idto" scope="application" class="bean.ItemsDTO" />
<jsp:useBean id ="docked_number" scope="session" type="java.lang.Integer" />
<jsp:useBean id ="user_name" scope="session" class="java.lang.String" />

<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/user.css" media="all" id="cssMain">
  <title>商品選択</title>
</head>

<body>

  <% if (docked_number != null) { %>

  <form action="./ConfirmCancellation" method="post" target="_blank" rel="noopener noreferrer" class="form-cancel">
    <button name="cbtn" value="selectitem" class="btn-cancel">キャンセル</button>
  </form>

  <header>
  <div class="header-list">
    <a class="pwd">商品選択</a>
    <span class="triangle">▶</span>
    <a class="n-pwd">注文確認</a>
    <span class="triangle">▶</span>
    <a class="n-pwd">確定待機</a>
    <span class="triangle">▶</span>
    <a class="n-pwd">完了</a>
  </div>
  </header>

  <div class="main-content">

  <h3>No. <%=docked_number%>　<%= user_name %> さん</h3>

  <form action="./CheckSelection" method="post">
    <div class="item-list">
      <%
      for(int i = 0; i < idto.size(); i++){
          ItemsBean ib = idto.get(i);
      %>
        <div class="tr">
          <a class="item-id"><%= ib.getId() %></a>
          <div class="item-image"></div>
          <div class="td">
            <p class="item-name"><%= ib.getName() %></p>
            <p>￥<%= ib.getPrice() %></p>
            <div class="item-stock-count">
              <a><%= ib.getStock() %> 個まで</a>
              <%
              String input_name = "input_" + ib.getId();
              %>
              <div class="select-box selectdiv">
                <select name="<%=input_name%>">
                  <option value="<%=0%>"><%=0%></option>
                  <% for(int j=1; j<=Math.min(ib.getStock(), 10); j++){ %>
                    <option value="<%=j%>"><%=j%></option>
                  <% } %> 
                </select>
              </div>
            </div>
          </div>
        </div>
    <% } %>
    </div>
    <button class="btn-next">注文確認</button>
  </form>

  <% } else { %>
    <p>整理番号の取得に失敗しました。初めからやり直してください。
    <a href="/ecHoweb/checkerLogin.html">戻る</a>
  <% } %>

  </div>

  <footer></footer>

</body>

</html>
