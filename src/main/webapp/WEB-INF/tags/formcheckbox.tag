<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="value" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
<c:when test="${value}">
	<input name="user[admin]" type="hidden" value="${user.admin}" /><input type="checkbox" value="${user.admin}" checked="checked" name="user[admin]" id="user_admin" />
</c:when>

<c:otherwise>
	<input name="user[admin]" type="hidden" value="${user.admin}" /><input type="checkbox" value="${user.admin}" name="user[admin]" id="user_admin" />
</c:otherwise>
</c:choose>

