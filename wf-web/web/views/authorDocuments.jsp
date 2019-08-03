<%@ page import="com.company.models.documents.Document" %>
<%@ page import="static com.company.storage.PersonsAndDocumentsStorage.*" %>
<%@ page import="com.company.models.staff.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map.Entry" %>
<%@page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<button type="button" name="back" onclick="history.back()">Назад</button><br>
<table>
    <%
        if (request.getParameter("id") != null) {
            for (Entry<Person, List<Document>> link : getPersonsWithDocuments().entrySet()) {
                if (link.getKey().getId() == Integer.parseInt(request.getParameter("id"))) {
                    for (Document document : link.getValue()) {
                        out.print("<tr onclick=location.href='documents.jsp?id=" + document.getId() + "'>");
                        out.print("<td>Идентификатор документа: </br></td>");
                        out.print("<td>" + document.getId() + "</td>");
                        out.print("</tr>");
                    }
                }
            }
        } else {
            out.print("error");
        }
    %>
</table>
</body>
</html>