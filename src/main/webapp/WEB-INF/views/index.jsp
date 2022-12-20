<!DOCTYPE html>
<html >
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
    <body class="overflow-x-hidden">
        <jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>

        <div class="w-screen px-16 min-h-[90vh] break-words grid">
            <div class="max-w-full place-self-center font-bold text-7xl lg:text-5xl">
                <h1>Welcome to <span class="text-java-pink font-black">I Love Java</span></h1>
                <h1>The open, <span class="text-java-blue font-black">Java</span> dedicated Forum</h1>
                <div class="lg:max-w-[66%] my-16 text-5xl lg:text-3xl tracking-tight break-words">
                    Join the Bean community,
                    <a class="underline underline-offset-8 decoration-8 decoration-java-blue/80 break-keep block lg:inline-block" href="/explore">explore existing discussions</a>
                    or
                    <a class="underline underline-offset-8 decoration-8 decoration-java-pink/80 break-keep block lg:inline-block" href="/new">start your own</a>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/includes/footer.jsp"/>
    </body>
</html>