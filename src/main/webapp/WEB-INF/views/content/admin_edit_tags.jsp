<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="tags" scope="request" type="java.util.List<dev.palmes.ilovejava.model.Tag>"/>

<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/includes/head.jsp"/>
    <body class="min-h-screen flex flex-col">
        <jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
        <c:if test="${not empty error}">
            <h1 class="text-4xl text-java-pink">${error}</h1>
        </c:if>
        <div class="w-screen min-h-full my-48 lg:my-28 grow">
            <div class="w-full mx-auto lg:w-2/3">
                <table class="table text-center w-full">
                    <thead>
                        <tr class="font-bold w-full border-b-2 border-java-pink/80">
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th colspan="2">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tag" items="${tags}">
                            <tr class="w-full" id="edit-tag-${tag.id}">
                                <td class="w-2/12 text-slate-600">${tag.id}</td>
                                <td class="w-2/12"><input class="w-full p-1 text-center" type="text" name="name" value="${tag.displayName}"></td>
                                <td class="w-6/12"><input class="w-full p-1 text-center" type="text" name="description" value="${tag.description}"></td>
                                <td class="w-1/12"><button id="save-tag-${tag.id}" class="w-full underline underline-offset-8 decoration-java-blue/80">Update</button></td>
                                <td class="w-1/12"><button id="delete-tag-${tag.id}" class="w-full underline underline-offset-8 decoration-java-pink/80">Delete</button></td>
                            </tr>
                        </c:forEach>
                        <tr class="w-full" id="new-tag">
                            <td><input class="w-full p-1 text-center" type="text" name="id" placeholder="New Tag ID"></td>
                            <td><input class="w-full p-1 text-center" type="text" name="name" placeholder="New Tag name"></td>
                            <td><input class="w-full p-1 text-center" type="text" name="description" placeholder="New Tag description"></td>
                            <td colspan="2"><button class="w-full underline underline-offset-8 decoration-java-blue/80" id="create-tag">Create Tag</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        </div>
        <jsp:include page="/WEB-INF/includes/footer.jsp"/>
        <script src="/static/js/content/tags.js"></script>
    </body>
</html>