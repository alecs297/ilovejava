<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="posts" scope="request" type="java.util.List<dev.palmes.ilovejava.model.Post>"/>
<c:choose>
    <c:when test="${empty posts}">
        <h1 class="w-full text-center text-slate-600">Nothing to see here, such empty</h1>
    </c:when>
    <c:otherwise>
        <c:forEach items="${posts}" var="post_item">
            <c:set value="${post_item}" var="post" scope="request"/>
            <jsp:include page="post_list_item.jsp"/>
        </c:forEach>
    </c:otherwise>
</c:choose>