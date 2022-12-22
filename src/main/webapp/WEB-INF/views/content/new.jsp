<jsp:useBean id="error" scope="request" type="java.lang.String"/>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="min-h-screen flex flex-col">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<div class="grow w-screen px-24 text-5xl lg:text-base grid mb-12">
    <div class="w-full lg:w-1/2 lg:max-w-screen-2xl place-self-center">
        <h1 class="text-8xl lg:text-5xl font-bold mt-8">Start a new thread</h1>
        <p class="text-5xl lg:text-2xl mb-12">Remember to check out our <a class="underline text-java-pink"
                                                                           href="/rules" referrerpolicy="no-referrer"
                                                                           target="_blank">rules</a> before posting.</p>
        <form class="w-full" action="/threads" method="POST">
            <input class="block w-full px-4 py-4 lg:py-2 border-2 rounded ${empty error ? "border-java-blue" : "border-java-pink"}"
                   id="title" name="title" type="text" placeholder="Title"/>
            <label class="block text-slate-400 text-4xl lg:text-sm px-1 mb-3" for="title">Make it clear and
                concise</label>
            <textarea
                    class="block mt-4 resize-none w-full px-4 py-4 lg:py-2 border-2 rounded ${empty error ? "border-java-blue" : "border-java-pink"}"
                    id="content" name="content" placeholder="What's on your mind ?"></textarea>
            <div id="preview-content" class="w-full bg-slate-200 rounded-lg prose prose-xl p-2 hidden max-w-full overflow-x-scroll"></div>
            <label class="block text-slate-400 text-4xl lg:text-sm px-1 mb-3" for="content">Markdown is supported
                !</label>
            <div class="text-5xl lg:text-2xl my-8 lg:my-4 text-java-blue">
                <h2 class="text-5xl lg:text-2xl text-black">Choose some tags</h2>
                <jsp:include page="fetch_tags.jsp" />
            </div>
            <div class="text-5xl lg:text-3xl">
                <label class="inline-block align-middle w-24 lg:w-12 border-2 rounded-3xl cursor-pointer inline-flex items-center mb-3">
                    <input id="preview-btn" type="checkbox" value="" class="sr-only peer">
                    <span class="rounded-full aspect-square bg-slate-400 w-12 lg:w-6 m-0 visible peer-checked:invisible"></span>
                    <span class="rounded-full aspect-square bg-java-blue w-12 lg:w-6 m-0 invisible peer-checked:visible"></span>
                </label>
                <span class="inline-block text-4xl lg:text-2xl">Preview Markdown</span>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class=" w-full mt-4 text-5xl lg:text-lg block px-8 py-6 lg:py-3 rounded-lg lg:rounded-lg text-background-default ${empty error ? "bg-java-blue" : "bg-java-pink"}"
                    type="submit">${empty error ? "Create thread" : "Wrong credentials"}</button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/new.js"></script>
</body>
</html>