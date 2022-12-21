<jsp:useBean id="thread" scope="request" type="dev.palmes.ilovejava.model.Thread"/>
<jsp:useBean id="item" scope="request" type="dev.palmes.ilovejava.model.Tag"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form>
    <c:forEach var="tag" items="${tags}">

        <label>
            <input type="checkbox" name="tag" value="${item.id}"
                   <c:if test="${thread.tags.contains(tag)}">checked</c:if> />
        </label>
        ${tag.displayName}<br>
    </c:forEach>
</form>
${dbg}