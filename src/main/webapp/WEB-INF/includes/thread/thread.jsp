<%@ page import="dev.palmes.ilovejava.model.Tag" %>
<%@ page import="java.util.UUID" %>
<%@ page import="dev.palmes.ilovejava.model.Post" %>
<%@ page import="dev.palmes.ilovejava.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.asosyalbebe.moment4j.Moment" %>

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
%>
<div class="w-full text-2xl lg:text-base">
  <span class="inline-block">
    [
    <%for (Tag tag : thread.getTags()) {%>
      <a class="text-java-pink text-xl lg:text-base" href="/expore/<%=tag.getId()%>"><%=tag.getDisplayName()%></a>
    <%}%>
    ]
  </span>
  <div>
    <a class="font-bold" href="/thread/<%=thread.getId()%>"><%=thread.getTitle()%></a>
    <h2 class="text-slate-600 leading-none">Created on <%=Moment.moment(thread.getEntry().getCreationDate()).format("dd/MM/YY hh:mm")%> by <a class="text-java-blue" href="/user/<%=thread.getEntry().getAuthor().getUsername()%>"><%=thread.getEntry().getAuthor().getUsername()%></a></h2>
    <a class="w-full my-2 block text-slate-600" href="/thread/<%=thread.getId()%>"><%=thread.getEntry().getContentSummary(100)%></a>
  </div>
</div>