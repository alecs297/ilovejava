<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dev.palmes.ilovejava.model.Thread" %>
<%@ page import="dev.palmes.ilovejava.model.Post" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body>
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<div class="w-screen text-center my-48 lg:my-28">
    <%
        Thread thread = (Thread) request.getAttribute("thread");
    %>
    <h2><%=thread.getTitle()%>
    </h2>
    <p><%=thread.getEntry().getContent()%>
    </p>
    <table>
        <%
            for (Post post : thread.getEntry().getChildren()
            ) {
                out.println("<tr>" + post.getContent() + "</tr>");
            }
        %>
    </table>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>