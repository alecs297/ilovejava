<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="posts" scope="request" type="java.util.List<dev.palmes.ilovejava.model.Post>"/>
<c:forEach var="post" items="${posts}">
    <div class="my-8 lg:my-4">
        <span class="mt-8 text-black/70">
        <a href="/users/${post.author.username}">[ ${post.author.username} ]</a>
    </span>
        <div class="border-l-2 my-2">
            <div class="ml-4 content prose prose-2xl lg:prose-base w-full bg-slate-200 p-2 rounded-lg">
                    ${post.content}
            </div>
            <button class="w-full text-left ml-4 mt-2 text-black/70 underline underline-offset-8 decoration-java-blue/80 hover:decoration-2"
                    id="reply-${post.id}">
                Reply
            </button>
            <div class="pl-2">
                <div class="mt-4">
                    <c:set var="posts" value="${post.children}" scope="request"/>
                    <jsp:include page="/WEB-INF/includes/thread/post.jsp"/>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
