<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/user.css" media="all" id="cssMain">
  <title>キャンセル確認</title>
</head>

<body>

  <header></header>

  <div class="main-content">

  <div class="await-message">
    <p>下のキャンセルボタンでキャンセルが確定します．</p>
    <p><span class="red">※この操作は取り消せません．</span></p>
    <p>キャンセルをやめる場合はこのタブを閉じて元の画面に戻ってください．</p>
  </div>

  <form action="./Cancelled" method="post">
    <button class="btn-next">キャンセル</button>
  </form>

  </div>

  <footer></footer>

</body>

</html>
