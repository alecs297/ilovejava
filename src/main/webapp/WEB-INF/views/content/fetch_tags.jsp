<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tags" scope="request" type="java.util.List<dev.palmes.ilovejava.model.Tag>"/>
<c:forEach var="tag" items="${tags}">
    <label>
        <input type="checkbox" name="tag" value="${tag.id}"/>
            ${tag.displayName}
    </label>
</c:forEach>