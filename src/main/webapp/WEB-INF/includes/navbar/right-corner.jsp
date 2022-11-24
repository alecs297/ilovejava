<div class="w-56 text-xl h-full align-bottom">
  <div class="block lg:hidden p-2 w-full h-full">
    <span id="navbar-toggle" class="inline w-fit h-full text-java-blue font-mono text-7xl cursor-pointer select-none float-right">
      {<span class="invisible text-java-pink">/</span>}
    </span>
  </div>
  <div id="navbar-content" class="absolute bg-background-default left-0 right-0 lg:static m-auto hidden lg:block cursor-pointer p-2 float-right">
    <div class="w-full h-screen px-48 lg:p-0">
      <nav class="block lg:hidden py-12 text-5xl">
        <h2 class="text-6xl font-bold mb-12 cursor-default select-none">Navigation</h2>
        <jsp:include page="content.jsp" />
      </nav>
      <jsp:include page="profile.jsp" />
    </div>
  </div>
</div>