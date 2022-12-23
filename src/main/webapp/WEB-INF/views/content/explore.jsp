<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tag" scope="request" class="dev.palmes.ilovejava.model.Tag"/>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="min-h-screen flex flex-col">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<div class="w-screen min-h-full my-48 lg:my-8 grow">
    <div class="w-full mx-8 lg:mx-auto lg:w-2/3">
        <div class="mb-16">
            <c:if test="${not empty pageTitle}">
                <h1 class="text-8xl lg:text-5xl mb-2 font-bold underline underline-offset-4 decoration-4 decoration-java-pink/80 break-keep block">
                        ${pageTitle}
                </h1>
            </c:if>
            <c:if test="${not empty pageSubTitle}">
                <h1 class="text-4xl lg:text-2xl break-keep block">
                        ${pageSubTitle}
                </h1>
            </c:if>
        </div>
        <jsp:include page="/WEB-INF/includes/thread/thread_list.jsp"/>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/thread/page_list.jsp"/>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>