<jsp:useBean id="post" scope="request" type="dev.palmes.ilovejava.model.Post"/>
<%@ page import="com.asosyalbebe.moment4j.Moment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-full text-2xl lg:text-base">
    in <a class="font-bold inline-block" href="/posts/${post.id}">${post.thread.title}</a>
    on <span class="text-slate-600 leading-none"> ${Moment.moment(post.creationDate).format("dd/MM/YY hh:mm")} </span>
    <a class="w-full my-2 block text-slate-600" href="/posts/${post.id}">${post.getContentSummary(100)}</a>
</div>