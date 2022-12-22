<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tags" scope="request" type="java.util.List"/>
<c:forEach var="tag" items="${tags}">
    <label>
        <input type="checkbox" name="tag-${tag.id}"/>
        ${tag.displayName}
    </label>
</c:forEach>