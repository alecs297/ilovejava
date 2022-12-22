<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="posts" scope="request" type="java.util.List<dev.palmes.ilovejava.model.Post>"/>
<c:forEach var="post" items="${posts}">
    <div class="mb-2 lg:mb-4">
        <span class="mt-4 text-2xl lg:text-base text-black/70">
            <a href="/users/${post.author.username}">[ ${post.author.username} ]</a>
        </span>
        <div class="border-l-2 lg:mb-2">
            <div class="content prose prose-2xl lg:prose-base max-w-screen  lg:max-w-screen-2xl bg-slate-100 p-4 py-8 lg:py-4 rounded-br-lg rounded-tr-lg
                prose-p:text-4xl lg:prose-p:text-base prose-headings:underline prose-headings:decoration-2 prose-headings:decoration-java-blue/80 prose-headings:underline-offset-8"
                 id="${post.id}">
                    ${post.content}
            </div>
            <div class="flex justify-start">
                <button class="w-full text-left text-4xl lg:text-base ml-2 lg:ml-4 mt-2 pb-4 text-black/70 underline underline-offset-8 decoration-java-blue/80 hover:decoration-2"
                        id="reply-${post.id}">
                    Reply
                </button>
                <div class="flex">
                    <c:if test="${(!post.removed) && (post.author.id == sessionScope.user.id || sessionScope.user.admin)}">
                        <c:if test="${post.author.id == sessionScope.user.id}">
                            <button class="w-full text-left text-4xl lg:text-base ml-2 lg:ml-4 mt-2 pb-4 text-black/70 underline underline-offset-8 decoration-java-blue/80 hover:decoration-2"
                                    id="edit-${post.id}">
                                Edit
                            </button>
                        </c:if>
                        <button class="w-full text-left text-4xl lg:text-base ml-2 lg:ml-4 mt-2 pb-4 text-black/70 underline underline-offset-8 decoration-java-pink/80 hover:decoration-2"
                                id="remove-${post.id}">
                            Remove
                        </button>
                    </c:if>
                </div>
            </div>
            <div class="pl-2">
                <div class="pb-1">
                    <c:set var="posts" value="${post.children}" scope="request"/>
                    <jsp:include page="/WEB-INF/includes/thread/post.jsp"/>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
