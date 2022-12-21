<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form>
    <c:forEach var="tag" items="${tags}">
    <input type="checkbox" name="tag" value="${item.getId()}" <c:if test="${thread.getTags().contains(tag)}">checked</c:if> />
        ${tag.getDisplayName()}<br>
    </c:forEach>
</form>
${dbg}