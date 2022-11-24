<div class="h-32 lg:h-20"></div>
<div class="w-screen fixed left-0 right-0 top-0 m-0 border-b-2 border-java-blue bg-background-default p-3 px-8 h-32 lg:h-20">
    <div class=" w-full h-full flex">
        <a href="/" class="h-full flex-none align-middle cursor-pointer align-bottom">
            <img class="aspect-square inline-block h-full" src="/static/images/ilovejava-128x128.png" alt="ilovejava-icon"/>
            <div class="hidden lg:inline-block align-middle">
                <p class="font-black tracking-tight text-xl">I Love Java</p>
                <p class="font-italic tracking-tighter text-sm leading-3">Average Bean Enjoyer's Forum</p>
            </div>
        </a>
        <nav class="invisible lg:visible grow pl-20 w-max space-x-4 text-xl h-full align-bottom leading-[3rem]">
            <jsp:include page="content.jsp" />
        </nav>
        <jsp:include page="right-corner.jsp" />
    </div>
</div>