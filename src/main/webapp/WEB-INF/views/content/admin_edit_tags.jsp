<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="tags" scope="request" type="java.util.List<dev.palmes.ilovejava.model.Tag>"/>
<%@page import="dev.palmes.ilovejava.model.Tag" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<body class="min-h-screen flex flex-col">
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>

<script>
    async function updateTag(tagId, tagName) {
        if (!confirm("Are you sure you want to update '" + tagName + "' ?")) return
        let request = new XMLHttpRequest();
        let csrf = document.getElementById("csrf");
        let payload = new FormData(document.getElementById("form" + tagId));
        payload.append(csrf.name, csrf.value);
        request.open("PUT", "/tags/" + tagId, true);
        request.send(payload);
        request.onreadystatechange = () => {
            if (request.readyState === 4 && request.status === 200) {
                console.log("Delete request succeeded");
                window.location.reload();
            } else {
                console.log("worked but other response idk");
                window.location.reload();
            }
        }
    }

    async function deleteTag(tagId, tagName) {
        if (!confirm("Are you sure you want to delete '" + tagName + "' ?")) return
        let request = new XMLHttpRequest();
        let csrf = document.getElementById("csrf");
        let payload = new FormData();
        payload.append(csrf.name, csrf.value);
        request.open("DELETE", "/tags/" + tagId, true);
        request.send(payload);
        request.onreadystatechange = () => {
            if (request.readyState === 4 && request.status === 200) {
                console.log("Delete request succeeded");
                window.location.reload();
            } else {
                console.log("worked but other response idk");
                window.location.reload();
            }
        }
    }

    async function createTag() {
        if (!confirm("Are you sure you want to add an item ?")) return
        let request = new XMLHttpRequest();
        let csrf = document.getElementById("csrf");
        const form = document.getElementById("createTag");
        let payload = new FormData(form);
        payload.append(csrf.name, csrf.value);
        request.open("POST", "/tags");
        request.send(payload);
        request.onreadystatechange = () => {
            if (request.readyState === 4 && request.status === 200) {
                console.log("Delete request succeeded");
                window.location.reload();
            } else {
                console.log("worked but other response idk");
                window.location.reload();
            }
        }
    }
</script>

<div class="w-screen min-h-full my-48 lg:my-28 grow">
    <div class="w-full mx-auto lg:w-2/3">
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Update</th>
                <th>Delete</th>

            </tr>
            <% for (Tag tag : tags) { %>
            <tr>
                <!-- TODO : Proper CSRF Token -->
                <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <form id="form<%=tag.getId() %>" action="/tags/<%=tag.getId() %>" method="PUT">
                    <td><input type="text" name="name" value="<%= tag.getDisplayName() %>"></td>
                    <td><input type="text" name="description" value="<%= tag.getDescription() %>"></td>
                </form>
                    <td><button onclick="updateTag('<%=tag.getId() %>', '<%=tag.getDisplayName() %>')">Update</button></td>
                    <td><button onclick="deleteTag('<%=tag.getId() %>', '<%=tag.getDisplayName() %>')">Delete</button></td>
            </tr>
            <% } %>
            <tr>
                <form id="createTag" action="/tags" method="POST">
                    <td><input type="text" name="name" placeholder="Name"></td>
                    <td><input type="text" name="description" placeholder="Description"></td>
                </form>
                <td><button onclick="createTag()">Create Tag</button></td>
            </tr>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>