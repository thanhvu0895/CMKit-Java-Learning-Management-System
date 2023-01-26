<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#grade_cats"><strong>Grade Categories<span class="caret"></span></strong></a>
	  </h4>
	</div>
	
	<div id="grade_cats" class="panel-collapse collapse in">
	  <div class="panel-body">
	  
		<p>
		  <table class="table table-hover">
			<thead>
			  <tr>
				<td>Category</td>
				<td>Weight</td>
				<td>Edit</td>
				<td>Delete</td>
			  </tr>
			</thead>
			<tbody>
			<c:if test="${param.parent == 'Klass'}"> 
			<c:forEach var="c" items="${klass_grade_categories}">
				<tr>
				  <td>${c.title}</td>
				  <td>${c.weight}</td>
				  <td><t:link_to path="${UrlUtils.EDIT_GRADE_CATEGORY_PATH}" classBS="btn btn-default"  id="${c.id}">Edit</t:link_to></td>
				  <%-- <% if c.assignments.empty? %> --%> <%// TODO: WRITE HAS ASSIGNMENT for grade category %>
				    <td><t:link_to path="${UrlUtils.GRADE_CATEGORIES_PATH}" classBS="btn btn-danger" id="${c.id}" confirm="Are you sure you want to delete this category?">Delete</t:link_to></td>
				  <%-- <% else %> --%>
				    <%-- <td><%= link_to 'Delete', nil , data: { confirm: 'Assignments are using this grade category.' }, class: "btn btn-danger", disabled: "disabled" %></td> --%>
				  <%-- <% end %> --%>
				</tr>
			</c:forEach>
			</c:if>
			
			<c:if test="${param.parent == 'Course'}">
			<c:forEach var="c" items="${course_grade_categories}"> 
				<td>${c.title}</td>
				  <td>${c.weight}</td>
				  <td><t:link_to path="${UrlUtils.EDIT_GRADE_CATEGORY_PATH}" classBS="btn btn-default"  id="${c.id}">Edit</t:link_to></td>
				  <%-- <% if c.assignments.empty? %> --%> <%// TODO: WRITE HAS ASSIGNMENT for grade category %>
				    <td><t:link_to path="${UrlUtils.GRADE_CATEGORIES_PATH}" classBS="btn btn-danger" id="${c.id}" confirm="Are you sure you want to delete this category?">Delete</t:link_to></td>
				  <%-- <% else %> --%>
				    <%-- <td><%= link_to 'Delete', nil , data: { confirm: 'Assignments are using this grade category.' }, class: "btn btn-danger", disabled: "disabled" %></td> --%>
				  <%-- <% end %> --%>
			</c:forEach>
			</c:if>	
			</tbody>
		  </table>
		</p>
		
		<p>
		<c:if test="${param.parent == 'Klass'}">
			<t:link_to path="${UrlUtils.NEW_GRADE_CATEGORY_PATH}" classBS="btn btn-primary"  id="${klass.id}">New Grade Category</t:link_to>
		</c:if>
		<c:if test="${param.parent == 'Course'}">
			<t:link_to path="${UrlUtils.NEW_GRADE_CATEGORY_PATH}" classBS="btn btn-primary"  id="${course.id}">New Grade Category</t:link_to>
		</c:if>
		</p>
		
	  </div>
	</div>
  </div>
</div> 
