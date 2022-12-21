<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="min-h-screen flex flex-col">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<div class="w-screen min-h-full my-48 lg:my-28 grow">
    <div class="w-full mx-auto lg:w-2/3">
        <jsp:include page="/WEB-INF/includes/thread/thread_list.jsp"/>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>