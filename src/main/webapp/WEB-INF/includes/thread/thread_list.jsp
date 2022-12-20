<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.UUID" %>
<%@ page import="dev.palmes.ilovejava.model.Thread" %>
<%@ page import="dev.palmes.ilovejava.model.Post" %>
<%@ page import="dev.palmes.ilovejava.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="dev.palmes.ilovejava.model.Tag" %>
<%@ page import="java.util.List" %>
<%
    dev.palmes.ilovejava.model.Thread thread = new dev.palmes.ilovejava.model.Thread();
    thread.setTitle("Loremp ipsum in dolor mescouilles.csv");
    thread.setId(UUID.randomUUID());
    thread.setEntry(new Post());
    thread.getEntry().setAuthor(new User());
    thread.getEntry().getAuthor().setUsername("aaaaaa");
    thread.getEntry().getAuthor().setId(UUID.randomUUID());
    thread.getEntry().setContent("Salut à tous les amis c'est david lafarge pokémon et aujourd'hui on se retrouve en compagnie de miss jirachi coucou");
    thread.getEntry().setCreationDate(new Date());
    Tag a = new Tag();
    a.setId("aa");
    Tag b = new Tag();
    a.setDisplayName("TagA");
    b.setDisplayName("TagB");
    thread.setTags(new ArrayList<>());
    thread.getTags().add(a);
    thread.getTags().add(b);
    List<Thread> threads = new ArrayList<>();
    threads.add(thread);
    threads.add(thread);
    threads.add(thread);
    threads.add(thread);
    threads.add(thread);
    request.setAttribute("threads", threads);
%>
<c:choose>
    <c:when test="${empty threads}">
        <h1 class="w-full text-center text-slate-600">Nothing to see here, such empty</h1>
    </c:when>
    <c:otherwise>
        <c:forEach items="${threads}" var="thread_item">
            <c:set value="${thread_item}" var="thread" scope="request"/>
            <jsp:include page="thread_list_item.jsp"/>
        </c:forEach>
    </c:otherwise>
</c:choose>