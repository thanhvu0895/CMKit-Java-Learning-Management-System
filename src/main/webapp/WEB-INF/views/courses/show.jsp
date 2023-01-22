<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:layoutj pageTitle="${course.title} | Assignments">
<ol class="breadcrumb">
  <%@ include file="_course_crumbs.jsp" %>
  <li class="active">Assignments</li>
</ol>

<jsp:include page="_tabs.jsp"><jsp:param name="current" value=":assignments"/></jsp:include>

<p>
  <table class="table table-hover">
	<thead>
	  <tr>
		<td>Assignment</td>
		<td>Category</td>
		<td>Points</td>
		<td>Type</td>
	  </tr>
	</thead>
	<tbody>
	  <c:forEach var="a" items="${assignments}">
		<tr>
		  <td><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${a.id}">${a.title}</t:link_to></td>
		  <%-- <%-- <td>${a.grade_category ? a.grade_category.title : "None"}</td> --%> 
		  <%-- <td>${a.get_point_value}</td> --%>
		  <%-- <td><%= a.assignment_type.humanize %></td> --%>
		</tr>
	  </c:forEach>
	</tbody>
  </table>
</p>

<p>
  <t:link_to path="${UrlUtils.NEW_ASSIGNMENT_PATH}?course=:id" id="${course.id}" classBS="btn btn-success">
    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span><span class="glyphicon glyphicon-file" aria-hidden="true"></span> New Assignment
  </t:link_to>
 <t:link_to path="${UrlUtils.SHOW_COPY_ASSIGNMENT_PATH}?course=:id" id="${course.id}" classBS="btn btn-primary">
 	<span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span> Copy Assignment
 </t:link_to>
</p>


</t:layoutj>