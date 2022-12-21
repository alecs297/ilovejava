<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
    <body class="min-h-screen flex flex-col">
        <jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
        <div class="grow w-screen text-center my-48 lg:my-28">
            <img class="mx-auto pointer-events-none w-2/3 lg:w-1/4 aspect-square" src="${pageContext.request.contextPath}/static/images/has-bean.png" alt="Content not found error icon"/>
            <h1 class="my-16 lg:my-8 text-5xl lg:text-xl font-bold">${empty error ? "Sorry, you don't have the permission to do that" : error}</h1>
            <span class="text-4xl lg:text-base">You can always go <a class="underline" href="/">home</a> or <a class="underline" href="/explore">explore</a> new content</span>
        </div>
        <jsp:include page="/WEB-INF/includes/footer.jsp"/>
    </body>
</html>