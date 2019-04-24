<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<html>
<head>
<title>Add a Role</title>
</head>
<body>
<a href="${pageContext.request.contextPath }/home"><h1>
APEXX PORTAL</h1></a>
<center>Add a Role</center>
<pre>
	<form:form modelAttribute="role" method="post" action="saverole" >
    Name        :	<form:input path="name" class="form-control" />
    Description :	<form:input path="description" class="form-control" />
    Assign authorities: <form:select path="authlist" >
    <form:options items="${authorities}" itemLabel="name" itemValue="id"></form:options>.
    
    </form:select>
			<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
					<input type="submit" value="Add" class="btn btn-submit">
		
		
	</form:form>
	${SPRING_SECURITY_LAST_EXCEPTION }
	</pre>
	<br><br>
</body>
</html>