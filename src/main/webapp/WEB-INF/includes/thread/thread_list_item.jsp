<jsp:useBean id="thread" scope="request" type="dev.palmes.ilovejava.model.Thread"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-full text-2xl lg:text-base">
    <jsp:include page="taglist.jsp"/>
    <a class="font-bold inline-block"
       href="/threads/${thread.id}">${thread.title} <c:if test="${thread.removed == true}"><span class="text-slate-600">- Removed</span></c:if></a>
    <jsp:include page="date-author.jsp"/>
    <a class="w-full my-2 block text-slate-600" href="/threads/${thread.id}">${thread.entry.getContentSummary(100)}</a>
</div>