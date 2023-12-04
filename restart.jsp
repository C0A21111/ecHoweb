<%@page contentType="text/html;charset=utf-8" %>
<%@page import="bean.*" %>

<jsp:useBean id ="sys_status" scope="application" class="java.lang.String" />

<html>

<head>
  <% if (sys_status=="true") { %>
    <title>サービス稼働完了</title>
  <% } else if (sys_status=="false") { %>
    <title>サービス停止完了</title>
  <% } else { %>
    <title>値異常</title>
  <% } %>
  <link rel="stylesheet" type="text/css" href="css/checkerOn.css" media="all" id="cssMain">
</head>

<body>

  <header></header>

  <div class="body-layout">
  <div class="box p-msg text-center">

    <p class="p-msg">
      <% if (sys_status=="true") { %>
        サービスの稼働が完了しました．
      <% } else if (sys_status=="false") { %>
        サービスの停止が完了しました．
      <% } else { %>
        値異常
      <% } %>
    </p>

    <form action="./Certification" method="post" accept-charset="UTF-8">  
      <button class="btn-white-blue">ホームへ戻る</button>
    </form>

  </div>
  </div>

  <footer></footer>

</body>
</html>
