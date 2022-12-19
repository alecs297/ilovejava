<!DOCTYPE html>
<html >
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
    <body class="overflow-x-hidden">
        <jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>

        <div class="w-screen px-16 h-[90vh] break-words grid">
            <div class="max-w-full place-self-center text-7xl lg:text-5xl text-center">
                <h1 class="font-bold">Welcome to <span class="text-java-pink font-black">I Love Java</span>, the open forum for <span class="text-java-blue font-black">Java enthusiasts</span>!</h1>
                <div class="lg:max-w-[66%] my-16 lg:text-2xl font-normal tracking-tight break-words mx-auto">
                    <p> We are a community of programmers, developers, and students who are passionate about Java programming. Here, you can share your knowledge, ask questions, and learn from others who love Java as much as you do.
                    </p><br>
                    <p> Whether you're a seasoned pro or a beginner just starting out, you'll find something of value here. Our members come from all levels of experience, and we encourage everyone to participate and share their insights.
                    </p><br>
                    <p> We hope that you'll <a class="underline underline-offset-4 decoration-4 decoration-java-blue/80 break-keep block lg:inline-block" href="/new">join us</a> in building a vibrant and supportive community for Java developers.
                        <br>
                        Thank you for visiting <span class="text-java-pink font-black">I Love Java</span>, and we look forward to seeing you around the forum!
                    </p>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/includes/footer.jsp"/>
    </body>
</html>