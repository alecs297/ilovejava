<%@ page import="com.asosyalbebe.moment4j.Moment" %>

<h2 class="text-slate-600 leading-none">Created on ${Moment.moment(thread.getEntry().getCreationDate()).format("dd/MM/YY hh:mm")} by <a class="text-java-blue" href="/users/${thread.getEntry().getAuthor().getUsername()}">${thread.getEntry().getAuthor().getUsername()}</a></h2>