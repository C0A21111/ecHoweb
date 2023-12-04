<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="adto" scope="session" class="bean.AutOfStockDTO" />
<jsp:useBean id ="docked_number" scope="session" type="java.lang.Integer" />
<jsp:useBean id ="user_name" scope="session" class="java.lang.String" />

<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/user.css" media="all" id="cssMain">
  <title>在庫不足</title>
</head>

<body>

  <form action="./ConfirmCancellation" method="post" target="_blank" rel="noopener noreferrer" class="form-cancel">
    <button name="cbtn" value="autofstock" class="btn-cancel">キャンセル</button>
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
    <p>大変申し訳ございません．</p>
    <p>一部の商品のご用意ができませんでした．</p>
    <div class="msg-space"></div>
    <p>ご用意できる分をそのまま購入いただくか、</p>
    <p>商品の再選択をしていただくことが出来ます</p>
    <div class="msg-space"></div>
    <p>※商品の再選択中に在庫が減ることはございません</p>
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

  <div class="aut-box">
    <form action="./AutOfStock" method="post" accept-charset="UTF-8" onSubmit="return checkReturn()">
      <button name="btn" value="商品を再選択" class="btn-return">商品を再選択</button>
    </form>
    <form action="./AutOfStock" method="post" accept-charset="UTF-8">
      <button name="btn" value="そのまま購入" class="btn-next">そのまま購入</button>
    </form>
  </div>
  
  <table class="table-aut">
    <tr>
      <th>商品名</th>
      <th>希望個数</th>
      <th>残り在庫</th>
    </tr>
    <%
    for(int i = 0; i < adto.size(); i++){
      AutOfStockBean ab = adto.get(i);
    %>
      <tr>
          <td><%= ab.getName() %></td>
          <td><%= ab.getCount() %>個</td>
          <td><%= ab.getStock() %>個</td>
      </tr>
    <% } %>
  </table>

  </div>

  <footer></footer>

</body>
</html>
