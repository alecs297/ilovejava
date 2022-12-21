<!DOCTYPE html>
<html >
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="overflow-x-hidden">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>

<div class="w-screen px-16 min-h-[85vh] grid">
  <form class="w-full text-4xl lg:text-base lg:w-1/2 lg:max-w-screen-sm place-self-center space-y-8 lg:space-y-3" method="post" action="/login">
    <div class="my-16">
      <h1 class="text-8xl lg:text-5xl font-black tracking-tight">Your account</h1>
      <h2 class="text-4xl lg:text-2xl tracking-tight">You can change your details here</h2>
    </div>
    <input value="${user.getUsername()}" class="w-full block px-8 py-5 lg:py-3 border-2 rounded ${empty error ? "border-java-blue" : "border-java-pink"}" name="username" type="text" placeholder="Username" />
    <input value="${user.getEmail()}" class="w-full block px-8 py-5 lg:py-3 border-2 rounded ${empty error ? "border-java-blue" : "border-java-pink"}" name="email" type="text" placeholder="Email" />
    <input class="w-full block px-8 py-5 lg:py-3 border-2 rounded-lg lg:rounded ${empty error ? "border-java-blue" : "border-java-pink"}" name="currentPassword" type="password" placeholder="Password" />
    <input class="w-full block px-8 py-5 lg:py-3 border-2 rounded-lg lg:rounded ${empty error ? "border-java-blue" : "border-java-pink"}" name="newPassword" type="password" placeholder="New Password" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button class="w-full text-5xl lg:text-lg block px-8 py-6 lg:py-3 rounded-lg lg:rounded-lg text-background-default ${empty error ? "bg-java-blue" : "bg-java-pink"}" type="submit">${empty error ? "Login" : "Wrong credentials"}</button>
  </form>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>