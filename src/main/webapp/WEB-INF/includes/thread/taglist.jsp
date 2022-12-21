<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<span class="inline-block">
    [
    <c:forEach var="tag" items="${thread.getTags()}">
      <a class="text-java-pink text-xl lg:text-base" href="/tags/${tag.getId()}>">${tag.getDisplayName()}</a>
    </c:forEach>
    ]
</span>