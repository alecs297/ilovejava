<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="w-full text-2xl lg:text-base">
  <jsp:include page="taglist.jsp"/>
  <a class="font-bold inline-block" href="/threads/${thread.getId()}">${thread.getTitle()}</a>
  <jsp:include page="date-author.jsp"/>
  <a class="w-full my-2 block text-slate-600" href="/threads/${thread.getId()}">${thread.getEntry().getContentSummary(100)}</a>
</div>