<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="dev.palmes.ilovejava.model.Thread" %>
<%@ page import="dev.palmes.ilovejava.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body>
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<%
    Thread thread = (Thread) request.getAttribute("thread");
%>
<div class="w-screen min-h-screen px-24 text-5xl lg:text-base grid mb-12">
    <div class="w-full lg:w-1/2 lg:max-w-screen-2xl place-self-center">
        <h1 class="text-8xl lg:text-5xl font-bold underline underline-offset-4 decoration-4 decoration-java-pink/80 break-keep block">
            <%=thread.getTitle()%>
        </h1>
        <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <jsp:include page="/WEB-INF/includes/thread/taglist.jsp"/>
        <jsp:include page="/WEB-INF/includes/thread/date-author.jsp"/>
        <div class="my-2">
            <%
                List<Post> posts = new ArrayList<>();
                posts.add(((Thread) request.getAttribute("thread")).getEntry());
                request.setAttribute("posts", posts);
            %>
            <jsp:include page="/WEB-INF/includes/thread/post.jsp"/>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
<script src="/static/js/threads.js"></script>
</body>
</html>