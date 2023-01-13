<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="model" required="false"%>
<%@ attribute name="label" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${not empty model}">
		<label for="${model}_${label}">${label}</label>
	</c:when>
	<c:otherwise>
		<label for="${label}">${label}</label>
	</c:otherwise>
</c:choose>
