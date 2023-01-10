<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="path" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var = "path" value = "${pageContext.request.contextPath}${path}"/>
<a href=${path}><jsp:doBody/></a>

