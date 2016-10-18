<%@ page contentType="text/html;charset=windows-1252"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; 
			charset=windows-1252"/>
		<title>Echo Request Method and Parameters</title>
	</head>
	<body>
		<h1>URL Connection Test</h1>
		<%
		out.println("Request Method: " + request.getMethod());
		out.println("<br/><br/>");

		out.println("<u>List of Parameter Names and Values</u><br/>");
		java.util.Enumeration paramNames = 
			request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName  = (String)paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			out.println("Name: " + paramName + ", Value: " +
				       paramValue);
			out.println("<br/>");
		}
		%>
	</body>
</html>
