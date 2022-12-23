<jsp:useBean id="nbPages" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="currentPage" scope="request" type="java.lang.Integer"/>
<jsp:useBean id="pageSize" scope="request" type="java.lang.Integer"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${nbPages > 0}">
    <div class="flex justify-center">
        <div class="flex my-2 text-xl text-slate-600">
            <
            <ul class="flex space-x-2 mx-2">
                <% for (int i = 0; i <= nbPages; i++) { %>
                <li class="<%= currentPage == i ? "underline decoration-java-blue/80" : ""%>"><a
                        href="?page=<%=i%>&size=${pageSize}"><%=i + 1%>
                </a>
                </li>
                <% } %>
            </ul>
            >
        </div>
    </div>
</c:if>