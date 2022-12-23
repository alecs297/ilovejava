<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="dev.palmes.ilovejava.model.Thread" %>
<%@ page import="dev.palmes.ilovejava.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="min-h-screen flex flex-col overflow-x-hidden">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<%
    Thread thread = (Thread) request.getAttribute("thread");
%>
<div class="grow w-screen min-h-screen lg:px-24 text-5xl lg:text-base grid mb-12">
    <div class="w-screen px-8 lg:p-0 lg:w-1/2 lg:max-w-screen-2xl place-self-center">
        <c:if test="${thread.removed}">
            <h2 class="text-6xl lg:text-4xl mt-4 lg:mb-2 font-bold text-java-pink break-keep block">
                Removed
            </h2>
        </c:if>
        <h1 class="text-7xl lg:text-5xl mt-4 mb-2 font-bold underline underline-offset-4 decoration-4 decoration-java-pink/80 break-keep block">
            <%=thread.getTitle()%>
        </h1>
        <div class="text-xl lg:text-lg">
            Tagged as
            <jsp:include page="/WEB-INF/includes/thread/taglist.jsp"/>
        </div>
        <div class="text-2xl lg:text-base mb-4 lg:mb-2">
            <jsp:include page="/WEB-INF/includes/thread/date-author.jsp"/>
        </div>
        <div class="my-2 z-0">
            <%
                List<Post> posts = new ArrayList<>();
                posts.add(((Thread) request.getAttribute("thread")).getEntry());
                request.setAttribute("posts", posts);
            %>
            <jsp:include page="/WEB-INF/includes/thread/post.jsp"/>
        </div>
    </div>
    <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/content/threads.js"></script>
</body>
</html>