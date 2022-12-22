<jsp:useBean id="thread" scope="request" type="dev.palmes.ilovejava.model.Thread"/>
<%@ page import="com.asosyalbebe.moment4j.Moment" %>

<h2 class="text-slate-600 leading-none">Created on ${Moment.moment(thread.entry.creationDate).format("dd/MM/YY HH:mm")}
    by <a class="text-java-blue"
          href="/users/${thread.entry.author.username}">${thread.entry.author.username}</a>
</h2>