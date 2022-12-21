<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="min-h-screen flex flex-col">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<div class="grow w-screen text-center">
    <img class="mx-auto pointer-events-none w-2/3 lg:w-1/4 aspect-square"
         src="${pageContext.request.contextPath}/static/images/has-bean.png"
         alt="Content not found error icon"/>
    <h1 class="my-16 lg:my-8 text-5xl lg:text-xl font-bold">Sorry, this content is no longer available</h1>
    <span class="text-4xl lg:text-base">You can always go <a class="underline"
                                                             href="${pageContext.request.contextPath}/">home</a> or <a
            class="underline"
            href="/explore">explore</a> new content</span>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>