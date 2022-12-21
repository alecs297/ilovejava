<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="dev.palmes.ilovejava.model.Thread" %>
<%@ page import="dev.palmes.ilovejava.model.Post" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/themes/prism-coy.min.css"
          integrity="sha512-LOT5F67SMZVdXMrvQe4S1ZHu5l6xk3CST2qqno9kY329OsJBBpybnq+fM9qG4ZSaNzPOjoGzHAeBamSSJyyuZg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<jsp:include page="/WEB-INF/includes/navbar/navbar.jsp"/>
<%
    Thread thread = (Thread) request.getAttribute("thread");
%>
<div class="w-screen min-h-screen px-24 text-5xl lg:text-base grid mb-12">
    <div class="w-full lg:w-1/2 lg:max-w-screen-2xl place-self-center">
        <div>
            <span class="mt-8 text-black/70">
                <a href="<%=thread.getEntry().getAuthor().getUsername()%>">[ <%=thread.getEntry().getAuthor().getUsername()%> ]</a>
            </span>
            <h1 class="text-8xl lg:text-5xl font-bold underline underline-offset-4 decoration-4 decoration-java-pink/80 break-keep block">
                <%=thread.getTitle()%>
            </h1>

            <div class="text-5xl lg:text-2xl mb-12 mt-4 content">
                <%=thread.getEntry().getContent()%>
            </div>
        </div>
        <div class="border-b-2 border-java-blue/60 flex justify-center">
            <button>Reply</button>
        </div>
        <div>
            <form:form class="my-8 pb-8 border-b-2 border-java-blue/60" action="/threads/${thread.id}" method="post">
                <label for="reply-content">
                    Reply
                </label>
                <textarea
                        class="block mt-4 resize-none w-full px-4 py-4 lg:py-2 border-2 rounded"
                        id="reply-content" name="content" placeholder="What's on your mind ?"></textarea>
                <button class="w-full mt-4 text-5xl lg:text-lg block px-8 py-6 lg:py-3 rounded-lg lg:rounded-lg text-background-default ${empty error ? "bg-java-blue" : "bg-java-pink"}"
                        type="submit">
                    Send Reply
                </button>
            </form:form>
        </div>
        <div>
            <c:set var="posts" value="${thread.entry.children}" scope="request"/>
            <jsp:include page="/WEB-INF/includes/thread/post.jsp"/>
        </div>
    </div>

</div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/dompurify/2.4.1/purify.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/markdown-it/13.0.1/markdown-it.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/prism.min.js"
        integrity="sha512-7Z9J3l1+EYfeaPKcGXu3MS/7T+w19WtKQY/n+xzmw4hZhJ9tyYmcUS+4QqAlzhicE5LAfMQSF3iFTK9bQdTxXg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prism/1.29.0/plugins/autoloader/prism-autoloader.min.js"
        integrity="sha512-SkmBfuA2hqjzEVpmnMt/LINrjop3GKWqsuLSSB3e7iBmYK7JuWw4ldmmxwD9mdm2IRTTi0OxSAfEGvgEi0i2Kw=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    const md = window.markdownit({
        html: true,
        linkify: true,
        typographer: true,
        breaks: true
    });

    contents = document.getElementsByClassName("content")
    for (const el of contents) {
        el.innerHTML = md.render(DOMPurify.sanitize(el.innerHTML));
    }

    function showReplyBox(event, postId) {

        let form = document.createElement("form");
        form.action = '${pageContext.request.contextPath}/posts/' + postId;
        form.method = "post";

        let label = document.createElement("label");
        label.setAttribute("for", "content" + postId);

        let text = document.createElement("textarea");
        text.name = "content";
        text.id = "text-" + postId;
        text.className = "block mt-4 resize-none w-full px-4 py-4 lg:py-2 border-2 rounded content";

        let button = document.createElement("button");
        button.className = "w-full mt-2 block px-8 underline decoration-wavy decoration-java-blue underline-offset-8";
        button.type = "submit";
        button.textContent = "Send"

        let input = document.createElement("input");
        input.type = "hidden";
        input.name = "${_csrf.parameterName}";
        input.value = "${_csrf.token}";

        form.append(label, text, button, input);
        form.classList.add("mx-4", "px-4", "border-l-2", "border-java-pink/80");

        event.after(form);
    }
</script>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
</body>
</html>