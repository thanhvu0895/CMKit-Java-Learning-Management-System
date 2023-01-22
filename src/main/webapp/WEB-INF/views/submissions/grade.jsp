<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="
<c:choose> 
<c:when test="${submission.contributors.any?}"> 
	Submission from 
<% //TODO: CONVERT THIS SUBMISSION FORM STATEMENT %>
</c:when> 
<c:otherwise>     
	Previous Submission   
</c:otherwise> </c:choose>">
</t:layoutj>