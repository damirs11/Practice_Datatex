<%@ page import="com.company.models.documents.Document" %>
<%@ page import="static com.company.storage.PersonsAndDocumentsStorage.*" %>
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
    <%
        if (request.getParameter("id") != null) {
            for (Document document : getDocuments()) {
                if (document.getId() == Integer.parseInt(request.getParameter("id"))) {
                    out.print("Идентификатор документа - " + document.getId() + "</br>");
                    out.print("Регистрационный номер документа - " + document.getRegId() + "</br>");
                    out.print("Дата регистрации документа - " + document.getRegDate() + "</br>");
                    out.print("Автор документа - " + document.getAuthor() + "</br>");
                }
            }
        }
    %>
</table>
</body>
</html>