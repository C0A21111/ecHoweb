<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="edto" scope="session" class="bean.EachOrdersDTO" />
<jsp:useBean id ="docked_number" scope="session" type="java.lang.Integer" />
<jsp:useBean id ="user_name" scope="session" class="java.lang.String" />

<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/user.css" media="all" id="cssMain">
  <title>注文確認</title>
</head>

<body>

  <% if (docked_number != null) { %>

  <form action="./ConfirmCancellation" method="post" target="_blank" rel="noopener noreferrer" class="form-cancel">
      <button name="cbtn" value="checkselection" class="btn-cancel">キャンセル</button>
  </form>

  <header>
    <div class="header-list">
      <a class="n-pwd">商品選択</a>
      <span class="triangle">▶</span>
      <a class="pwd">注文確認</a>
      <span class="triangle">▶</span>
      <a class="n-pwd">確定待機</a>
      <span class="triangle">▶</span>
      <a class="n-pwd">完了</a>
    </div>
  </header>

  <div class="main-content">

  <h3>No. <%=docked_number%>　<%= user_name %> さん</h3>

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
        <div class="check-item-tr subtotal "><a>小計</a><a class="right"><span class="red">￥<%= subtotal %></span></a></div>
      </div>  
    </div>
  </div>
  <% } %>

  <div class="total-price form-nickname">
    <a>合計：</a>
    <a><span class="red">￥<%=total%></span></a>
  </div>

  <script>
    function checkReturn() {
      if (window.confirm('※　注文の選択が一度クリアされます．\n　　よろしいですか？')) {
        return true;
      }
      else {
        window.alert('キャンセルされました');
        return false;
      }
    }
  </script>

  <div class="btn-box">
    <form  action="./selectItem.jsp" method="post" onSubmit="return checkReturn()">
      <button  class="btn-return">戻る</button>
    </form>
    <form action="./AwaitAcception" method="post">
      <button class="btn-next">注文</button>
    </form>
  </div>

  <% } else { %>
  <p>不正なアクセスです．</p>
  <% } %>

  </div>

  <footer></footer>
  
</body>

</html>
