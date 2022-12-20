<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body>
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<div class="w-screen my-48 lg:my-28">
    <%
        String content = (String) request.getAttribute("content");
        //out.println(content);
    %>
    <jsp:include page="/WEB-INF/includes/thread/thread.jsp"/>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>