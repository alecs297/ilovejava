<jsp:useBean id="post" scope="request" type="dev.palmes.ilovejava.model.Post"/>
<%@ page import="com.asosyalbebe.moment4j.Moment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-full text-5xl lg:text-base mb-8 lg:mb-0">
    in <a class="font-bold inline-block mb-2 lg:mb-0" href="/posts/${post.id}">${post.thread.title}</a>
    <span
            class="text-slate-600 leading-none text-4xl lg:text-base">on ${Moment.moment(post.creationDate).format("dd/MM/YY hh:mm")} </span>
    <a class="w-full text-4xl lg:text-base my-2 block text-slate-600"
       href="/posts/${post.id}">${post.getContentSummary(100)}</a>
</div>