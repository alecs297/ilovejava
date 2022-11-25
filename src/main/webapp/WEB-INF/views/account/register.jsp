<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="overflow-x-hidden">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>

<div class="w-screen px-16 h-[85vh] grid">
  <form class="w-full text-4xl lg:text-base lg:w-1/2 lg:max-w-screen-sm place-self-center space-y-8 lg:space-y-3"
        method="post" action="/register">
    <div class="my-16">
      <h1 class="text-8xl lg:text-5xl font-black tracking-tight">Nice to meet you !</h1>
      <h2 class="text-4xl lg:text-2xl tracking-tight">Already have an account ? <a class="underline text-java-pink"
                                                                                   href="/login">Login</a></h2>
    </div>
    <div class="grid grid-cols-4 h-max">
      <input id="username" value="${username}"
             class="col-span-3 inline-block px-8 py-5 lg:py-3 border-y-2 border-l-2 rounded-l ${empty error ? "border-java-blue" : "border-java-pink"} focus:border-background-default"
             name="username" type="text" placeholder="Username"/>
      <button id="check-button"
              class="text-background-default w-full border-2 border-r-2 inline-block rounded-r ${empty error ? "bg-java-blue" : "bg-java-pink"} ${empty error ? "border-java-blue" : "border-java-pink"}">
        Check
      </button>
    </div>
    <label class="px-2 text-slate-400 text-2xl lg:text-xs" for="username">4 to 16 alphanumeric characters or dashes,
      unique usernames accepted</label>
    <input id="email" value="${email}"
           class="w-full block px-8 py-5 lg:py-3 border-2 rounded ${empty error ? "border-java-blue" : "border-java-pink"}"
           name="email" type="text" placeholder="Email"/>
    <label class="px-2 text-slate-400 text-2xl lg:text-xs" for="email">All email providers accepted, <span
            class="line-through">including non existent ones</span></label>
    <input id="password"
           class="w-full block px-8 py-5 lg:py-3 border-2 rounded-lg lg:rounded ${empty error ? "border-java-blue" : "border-java-pink"}"
           name="password" type="password" placeholder="Password"/>
    <label class="px-2 text-slate-400 text-2xl lg:text-xs" for="password">All passwords must match the following
      expression, you can do this !<span class="block px-2">^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$</span></label>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button class="w-full text-5xl lg:text-lg block px-8 py-6 lg:py-3 rounded-lg lg:rounded-lg text-background-default ${empty error ? "bg-java-blue" : "bg-java-pink"}"
            type="submit">${empty error ? "Create a free account" : error }</button>
    <p id="help" class="w-full py-12 text-center">Trouble receiving your verification codes ? <span
            class="text-java-pink cursor-pointer underline">Help me</span></p>
  </form>
</div>
<script src="/static/js/smtp.js"></script>
<script src="/static/js/usernamecheck.js"></script>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>