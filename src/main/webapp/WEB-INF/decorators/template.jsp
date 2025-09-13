<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="vi">
<head>
  <meta charset="utf-8">
  <title><decorator:title default="Template"/></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
  <decorator:head/>
</head>
<body>
  <jsp:include page="/commons/web/header.jsp"/>
  <main class="container py-3">
    <decorator:body/>
  </main>
  <jsp:include page="/commons/web/footer.jsp"/>
  <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
