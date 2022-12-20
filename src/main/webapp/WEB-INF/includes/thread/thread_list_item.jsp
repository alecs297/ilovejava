<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.asosyalbebe.moment4j.Moment" %>

<div class="w-full text-2xl lg:text-base">
  <span class="inline-block">
    [
    <c:forEach var="tag" items="${thread.getTags()}">
      <a class="text-java-pink text-xl lg:text-base" href="/expore/${tag.getId()}>">${tag.getDisplayName()}</a>
    </c:forEach>
    ]
  </span>
  <a class="font-bold inline-block" href="/thread/${thread.getId()}">${thread.getTitle()}</a>
  <h2 class="text-slate-600 leading-none">Created on ${Moment.moment(thread.getEntry().getCreationDate()).format("dd/MM/YY hh:mm")} by <a class="text-java-blue" href="/user/${thread.getEntry().getAuthor().getUsername()}">${thread.getEntry().getAuthor().getUsername()}</a></h2>
  <a class="w-full my-2 block text-slate-600" href="/thread/${thread.getId()}">${thread.getEntry().getContentSummary(100)}</a>
</div>