<%@ tag trimDirectiveWhitespaces="true" language="java" pageEncoding="ISO-8859-1"%>
<%@ tag import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ attribute name="path" required="true"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="classBS" required="false"%>
<%@ attribute name="method" required="false"%>
<%@ attribute name="confirm" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value ="${pageContext.request.contextPath}${path}"/>
<c:set var="path" value ="${UrlUtils.putIdInPath(pageContext.getAttribute('path'), pageContext.getAttribute('id'))}"/>
<c:choose>
	<c:when test="${not empty classBS && not empty method && not empty id && empty confirm}"><a class="${classBS}" rel="nofollow" data-method="${method}" href="${path}"><jsp:doBody/></a></c:when>
	<c:when test="${not empty confirm}"><a data-confirm="${confirm}" class="${classBS}" rel="nofollow" data-method="${method}" href="${path}"><jsp:doBody/></a></c:when>
	<c:when test="${not empty classBS && not empty id}"><a class="${classBS}" href="${path}"><jsp:doBody/></a></c:when> 
	<c:when test="${not empty method}"><a href="${path}" rel="nofollow" data-method="${method}" class="${classBS}"><jsp:doBody/></a></c:when>
	<c:otherwise><a href="${path}"><jsp:doBody/></a></c:otherwise>
</c:choose>