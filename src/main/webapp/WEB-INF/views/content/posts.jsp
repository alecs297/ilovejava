<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body>
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<div class="w-screen text-center my-48 lg:my-28">
    <%
        String content = (String) request.getAttribute("content");
        out.println(content);
    %>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>