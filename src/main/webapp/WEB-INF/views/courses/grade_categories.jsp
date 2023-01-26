<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title} | Grade Categories">
<ol class="breadcrumb">
  <%@ include file="_course_crumbs.jsp" %>
  <li class="active">Grade Categories</li>
</ol>

<jsp:include page="_tabs.jsp"><jsp:param name="current" value=":grade_categories"/></jsp:include>

<jsp:include page="../grade_categories/_grade_category_list.jsp"><jsp:param name="parent" value="Course"/></jsp:include>

</t:layoutj>