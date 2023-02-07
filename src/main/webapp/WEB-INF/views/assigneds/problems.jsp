<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="${assignment.title} | Problems">
<ol class="breadcrumb">
  <%@ include file="../assigneds/_grading_crumbs.jsp"%>
  <li class="active">Problems</li>
</ol>

<jsp:include page="../assigneds/_tabs.jsp"><jsp:param name="current" value=":problems"/></jsp:include>

<table class="table table-hover">
  <thead>
    <tr>
	  <th>Problem</th>
	  <th>Points</th>
	  <th>Grade</th>
	</tr>
  </thead>
  <tbody>
    <c:forEach var="p" items="${problems}">
	  <tr>
		<td>${p.title}</td>
		<td>${p.points}</td>
		<td><t:link_to path="${UrlUtils.ASSIGNMENT_SHOW_GRADE_PROBLEM_PATH}" classBS="btn btn-primary" id="${assigned.id}" secondId="${assignment.id}" thirdId="${p.id}">Grade</t:link_to></td>
	  </tr>
	</c:forEach>
  </tbody>
</table>
</t:layoutj>
<!-- //100% DONE -->