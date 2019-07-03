<%@ page import="com.company.models.documents.Document" %>
<%@ page import="static com.company.storage.PersonsAndDocumentsStorage.*" %>
<%@ page import="com.company.models.staff.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
    </tr>
    <%
        for (Map.Entry<Person, List<Document>> link : getPersonsWithDocuments().entrySet()) {
            out.print("<tr onclick=location.href='authorDocuments?id=" + link.getKey().getId() + "'>" +
                    "<td>" + link.getKey().getId() + "</td>" +
                    "<td>" + link.getKey().getName() + "</td>" +
                    "<td>" + link.getKey().getSecondName() + "</td>" +
                    "<td>" + link.getKey().getMiddleName() + "</td>" +
                    "</tr>");
        }
    %>
</table>
</body>
</html>