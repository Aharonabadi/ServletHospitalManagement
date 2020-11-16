<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PassUserInputToJSP</title>
</head>
<body>

<h2>Unsafe:</h2>

<p><c:out value="${name}" escapeXml="false" /></p>

</body>
</html>
