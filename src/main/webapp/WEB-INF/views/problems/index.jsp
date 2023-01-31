<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${assignment.title} | Rubric">

<ol class="breadcrumb">
<%@ include file="../assignments/_assignment_crumbs.jsp" %>
<li><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${assignment.id}">${assignment.title}</t:link_to></li>
<li class="active">Rubric</li>
</ol>

<h1>${assignment.title} Rubric</h1>

<table class="table table-striped">
  <thead>
    <tr>
      <th>Problem</th>
      <th>Points</th>
	  <th>Edit</th>
	  <th>Delete</th>
	  <th>Move</th>
    </tr>
  </thead>

  <tbody>
  <c:forEach items="${problems}" var="problem">
   <tr>
        <td>${problem.title}</td>
        <td>${problem.points}</td>
        <td><t:link_to path="${UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH}" classBS="btn btn-default" id="${problem.id}" secondId="${assignment.id}">Edit</t:link_to></td>
        <td><t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEM_PATH}" classBS="btn btn-danger" id="${problem.id}" secondId="${assignment.id}" confirm="Are you sure?">Delete</t:link_to></td>
		<td><t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEM_MOVE_DOWN_PATH}" classBS="btn btn-default" method="POST" id="${problem.id}" secondId="${assignment.id}"><span class="glyphicon glyphicon-arrow-down"></span></t:link_to>
		<t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEM_MOVE_UP_PATH}" classBS="btn btn-default" method="POST" id="${problem.id}" secondId="${assignment.id}"><span class="glyphicon glyphicon-arrow-up"></span></t:link_to></td>
   </tr>
   </c:forEach>
 </tbody>
</table>

<br>

<t:link_to path="${UrlUtils.NEW_ASSIGNMENT_PROBLEM_PATH}" classBS="btn btn-success" secondId="${assignment.id}"><span class="glyphicon glyphicon-plus"></span> New Problem</t:link_to>
<t:link_to path="${UrlUtils.ASSIGNMENT_VIEW_COPY_RUBRIC_PATH}" classBS="btn btn-primary" secondId="${assignment.id}"><span class="glyphicon glyphicon-copy"></span> Copy problem from another assignment</t:link_to>

</t:layoutj>