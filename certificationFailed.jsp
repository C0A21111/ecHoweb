<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/user.css" media="all" id="cssMain">
  <title>セッション無効</title>
</head>

<body>

  <div class="space"></div>

  <div class="main-content">

  <div class="await-message">
    <p>整理番号の取得に失敗しました．</p>
    <p>初めからやり直してください．</p>
  </div>
  <form action="registeringUser.html" method="post">
      <button class="btn-return">戻る</button>
  </form>

  </div>

  <footer></footer>

</body>

</html>
