<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="w-56 text-xl h-full align-bottom">
  <div class="block lg:hidden p-2 w-full h-full">
    <span id="navbar-toggle" class="inline w-fit h-full text-java-blue font-mono text-7xl cursor-pointer select-none float-right">
      {<span class="invisible text-java-pink">/</span>}
    </span>
  </div>
  <div id="navbar-content" class="absolute bg-background-default left-0 right-0 h-screen lg:h-max lg:static hidden lg:block cursor-pointer float-right">
    <div class="w-full px-48 lg:p-0">
      <nav class="block lg:hidden py-12 text-5xl">
        <h2 class="text-6xl font-bold mb-12 cursor-default select-none">Navigation</h2>
        <jsp:include page="content.jsp" />
      </nav>
      <c:choose>
        <c:when test="${empty user}">
          <h2 class="lg:hidden text-6xl font-bold mb-12 cursor-default select-none">Account</h2>
          <a class="text-5xl lg:text-xl block underline decoration-wavy decoration-java-blue underline-offset-8 rounded-lg px-0 lg:px-6 py-3 align-top" href="/login">Login</a>
        </c:when>
        <c:otherwise>
          <jsp:include page="profile.jsp" />
        </c:otherwise>
      </c:choose>

    </div>
  </div>
</div>