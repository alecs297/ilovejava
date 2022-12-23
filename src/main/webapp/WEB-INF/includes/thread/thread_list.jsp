<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="threads" scope="request" type="java.util.List<dev.palmes.ilovejava.model.Thread>"/>
<c:choose>
    <c:when test="${empty threads}">
        <h1 class="w-full text-center text-slate-600">Nothing to see here, such empty</h1>
    </c:when>
    <c:otherwise>
        <c:forEach items="${threads}" var="thread_item">
            <c:set value="${thread_item}" var="thread" scope="request"/>
            <jsp:include page="thread_list_item.jsp"/>
        </c:forEach>
    </c:otherwise>
</c:choose>

