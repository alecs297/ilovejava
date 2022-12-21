<jsp:useBean id="user" scope="request" type="dev.palmes.ilovejava.model.User"/>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="min-h-screen flex flex-col">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<div class="grow w-screen my-48 lg:my-28">
    <div class="w-full mx-auto lg:w-2/3">
        <h1 class="text-8xl lg:text-5xl mt-4 lg:mb-8 font-bold underline underline-offset-4 decoration-4 decoration-java-pink/80 break-keep block">${user.username}</h1>

        <div class="flex justify-start space-x-4">
            <h2 class="text-4xl lg:text-2xl mt-4 lg:mb-8 font-bold underline underline-offset-4 decoration-4 decoration-java-blue/80 ">
                Threads</h2>
            <h2 class="text-4xl lg:text-2xl mt-4 lg:mb-8 font-bold">
                Posts</h2>
        </div>

        <jsp:include page="/WEB-INF/includes/thread/thread_list.jsp"/>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>