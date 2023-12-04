<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="sys_status" scope="application" class="java.lang.String" />

<html>

<head>
  <meta charset="UTF-8">
  <title>管理者ホーム</title>
  <link rel="stylesheet" type="text/css" href="css/checkerOn.css" media="all" id="cssMain">
</head> 

<body>

  <header>
  <h2>管理ホーム</h2>
  </header>

  <div class="body-layout">
  <div class="box">

  <form action="./ShowStock" method="post" accept-charset="UTF-8">
    <button name="btn"  class="btn-border">在庫管理</button>
  </form>

  <form action="./Operation" method="post" accept-charset="UTF-8">
    <button name="btn"  class="btn-border">販売オペレーション</button>
  </form>

  <%-- ボタンクリック時の確認ポップアップ --%>
  <script>
    function checkOn() {
      if (window.confirm('サービスを稼働します')) {
        return true;
      }
      else {
        window.alert('キャンセルされました');
        return false;
      }
    }
    function checkOff() {
      if (window.confirm('サービスを停止します')) {
        return true;
      }
      else {
        window.alert('キャンセルされました');
        return false;
      }
    }
  </script>
  
  <% if (sys_status=="true") { %>
  <form action= "./Restart" method="post" onSubmit="return checkOff()">
    <button name="btn" value="停止" class="btn-border-attention">停止</button>
    <p>現在のサービス状態　：　<span class="on-color">　稼働中　</span></p>
  </form>
  <% } else if (sys_status=="false") { %>
  <form action= "./Restart" method="post" onSubmit="return checkOn()">
    <button name="btn" value="稼働" class="btn-border-attention">稼働</button>
    <p>現在のサービス状態　：　<span class="off-color">　未稼働　</span></p>
  </form>
  <% } else { %>
  <form action= "./Restart" method="post" onSubmit="return checkOn()">
    <button name="btn" value="稼働" class="btn-border-attention">稼働</button>
    <p>現在のサービス状態　：　<span class="off-color">　未稼働　</span></p>
  </form>
  <% } %>
  
  </div>

  <form action= "./Logout" method="post">
    <button class="btn-blue-white">ログアウト</button>
  </form>

  </div>

  <footer></footer>

</body>

</html>
