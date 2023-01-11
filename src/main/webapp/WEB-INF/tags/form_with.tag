<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="url" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var ="url" value="${pageContext.request.contextPath}${url}"/>
<form action="${url}" method="post">
	<jsp:doBody/>
</form>

