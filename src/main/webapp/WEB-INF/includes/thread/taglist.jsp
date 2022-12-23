<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="thread" scope="request" type="dev.palmes.ilovejava.model.Thread"/>
<span class="inline-block">
    [
    <c:forEach var="tag" items="${thread.tags}">
        <a class="text-java-pink text-5xl lg:text-base" href="/tags/${tag.id}">${tag.displayName}</a>
    </c:forEach>
    ]
</span>