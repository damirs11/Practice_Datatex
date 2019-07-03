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
</head>
<body>
<table>
    <%
        if (request.getParameter("id") != null) {
            for (Entry<Person, List<Document>> link : getPersonsWithDocuments().entrySet()) {
                if (link.getKey().getId() == Integer.parseInt(request.getParameter("id"))) {
                    for (Document document : link.getValue()) {
                        out.print("Идентификатор документа: <a href='documents?id=" + document.getId() + "'> " + document.getId() + "</a></br>");
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