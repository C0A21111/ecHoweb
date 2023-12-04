<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="edto" scope="session" class="bean.EachOrdersDTO" />
<jsp:useBean id ="docked_number" scope="session" type="java.lang.Integer" />
<jsp:useBean id ="user_name" scope="session" class="java.lang.String" />

<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/user.css" media="all" id="cssMain">
  <title>確定待機</title>
  <script>
    setTimeout("location.reload()",1000*7)
  </script>
</head>

<body>

  <form action="./ConfirmCancellation" method="post" target="_blank" rel="noopener noreferrer" class="form-cancel">
    <button name="cbtn" value="checkselection" class="btn-cancel">キャンセル</button>
  </form>

  <div class="main-content">

  <header>
    <div class="header-list">
      <a class="n-pwd">商品選択</a>
      <span class="triangle">▶</span>
      <a class="n-pwd">注文確認</a>
      <span class="triangle">▶</span>
      <a class="pwd">確定待機</a>
      <span class="triangle">▶</span>
      <a class="n-pwd">完了</a>
    </div>
  </header>

  <h3>No. <%=docked_number%>　<%= user_name %> さん</h3>

  <div class="await-message">
    <p><span class="bold">以下の内容で注文を受け付けています．</span></p>
    <p><span class="bold">少々お待ちください．</span></p>
    <div class="loader"></div>
    <p>画面を変更しないでください...</p>
  </div>

  <%
  int total =0;
  for(int i = 0; i < edto.size(); i++){
      EachOrdersBean eb = edto.get(i);
  %>
  <div class="check-list">
    <p class="check-list-title">No.<%= eb.getItemId() %>　<%= eb.getItemName() %></p>
    <div class="check-item">
      <div class="check-item-img"></div>
      <div class="check-item-table">
        <div class="check-item-tr"><a>単価</a><a class="right">￥<%= eb.getItemPrice() %></a></div>
        <div class="check-item-tr"><a>注文数</a><a class="right"><%= eb.getItemCount() %></a></div>
        <%
        int subtotal = eb.getSubtotal(); 
        total += subtotal;
        %>
        <div class="check-item-tr subtotal "><a>小計</a><a class="right"><span class="red">￥<%= eb.getSubtotal() %></span></a></div>
      </div>  
    </div>
  </div>
  <% } %>

  <div class="total-price form-nickname">
    <a>合計：</a>
    <a><span class="red">￥<%= total %></span></a>
  </div>

  </div>

  <footer></footer>

</body>

</html>
