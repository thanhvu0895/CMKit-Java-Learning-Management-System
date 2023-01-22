<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title} | Files">
<ol class="breadcrumb">
  <%@ include file="_course_crumbs.jsp" %>
  <li class="active">Files</li>
</ol>

<jsp:include page="_tabs.jsp"><jsp:param name="current" value=":files"/></jsp:include>
</t:layoutj>