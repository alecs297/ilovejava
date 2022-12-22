<jsp:useBean id="user" scope="session" type="dev.palmes.ilovejava.model.User"/>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="min-h-screen flex flex-col overflow-x-hidden">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>

<div class="grow w-screen px-16 min-h-[85vh] grid">
    <form class="w-full text-4xl lg:text-base lg:w-1/2 lg:max-w-screen-sm place-self-center"
          method="post" action="/account">
        <div class="my-16">
            <h1 class="text-8xl lg:text-5xl font-black tracking-tight">Your account</h1>
            <h2 class="text-4xl lg:text-2xl tracking-tight">You can change your details here</h2>
        </div>
        <label class="text-slate-600 text-xl" for="username">Username</label>
        <input value="${user.username}"
               class="w-full block px-8 py-5 mb-8 lg:mb-4 lg:py-3 border-2 rounded ${empty error ? "border-java-blue" : "border-java-pink"}"
               id="username" name="username" type="text" placeholder="Username"/>
        <label class="text-slate-600 text-xl" for="email">Email</label>
        <input value="${user.email}"
               class="w-full block px-8 py-5 mb-8 lg:mb-4 lg:py-3 border-2 rounded ${empty error ? "border-java-blue" : "border-java-pink"}"
               id="email" name="email" type="text" placeholder="Email"/>
        <label class="text-slate-600 text-xl" for="currpass">Current Password</label>
        <input class="w-full block px-8 py-5 mb-8 lg:mb-4 lg:py-3 border-2 rounded-lg lg:rounded ${empty error ? "border-java-blue" : "border-java-pink"}"
               id="currpass" name="currentPassword" type="password" placeholder="Password"/>
        <label class="text-slate-600 text-xl" for="newpass">New password</label>
        <input class="w-full block px-8 py-5 mb-8 lg:mb-4 lg:py-3 border-2 rounded-lg lg:rounded ${empty error ? "border-java-blue" : "border-java-pink"}"
               id="newpass" name="newPassword" type="password" placeholder="New Password"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="w-full text-5xl lg:text-lg block px-8 py-6 lg:py-3 rounded-lg lg:rounded-lg text-background-default ${empty error ? "bg-java-blue" : "bg-java-pink"}"
                type="submit">${empty error ? "Update" : error}</button>
    </form>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>