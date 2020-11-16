<%@ page import="org.owasp.encoder.Encode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PassUserInputToJSP</title>
</head>
<body>

<% String name = Encode.forHtml(request.getParameter("name"));
    if (name != null) { %>
<p>Hello, <% out.print(name); %>!</p>
<%  } else { %>
<p>Hello, everyone!</p>
<%  }  %>

</body>
</html>
