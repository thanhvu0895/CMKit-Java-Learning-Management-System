<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title}: ${klass.semester} ${klass.section}) | Settings">
<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
  <li class="active">Settings</li>
</ol>

<jsp:include page="../klasses/_tabs.jsp"><jsp:param name="current" value=":settings"/></jsp:include>

<br>

<div class="panel panel-default">
  <div class="panel-heading"><h4 class="panel-title"><strong>Options</strong></h4></div>
  <div class="panel-body">
	<jsp:include page="_form.jsp"><jsp:param name="method" value="PATCH"/></jsp:include>
  </div>
</div>

<br>

<div class="panel panel-default">
  <div class="panel-heading">
	<h4 class="panel-title">
	  <strong><span class="glyphicon glyphicon-apple" aria-hidden="true"></span> Professors / Admins</strong>
	</h4>
  </div>
  <div class="panel-body">
	<p>
	  <table class="table table-hover">
		<thead>
		  <tr>
			<td>Name</td>
			<td>Email</td>
		  </tr>
		</thead>
		<tbody>
		<c:forEach items="${klass_professor_users}" var="u" varStatus="loop">
			<tr>
			 <td>${u.getFull_preferred_name()}</td>
			 <td>${u.email}</td>  
			 <td><t:link_to path="${UrlUtils.PROFESSORS_PATH}/:id" classBS="btn btn-danger" id="${klass_professors[loop.index].id}" confirm="Are you sure?" method="DELETE">Remove</t:link_to></td>
			</tr>
		  </c:forEach>
		</tbody>
	  </table>
	</p>
	<t:form_with url="${UrlUtils.PROFESSORS_PATH}" classBS="form-inline">
	<div class="form-group">
		<label for="Emails (separate multiple with commas):">Emails (separate multiple with commas):</label>
		<input class="form-control" type="text" name="emails" id="emails" />
	  </div>
	 
	 <div class="form-group">
		<input value="${klass.id}" type="hidden" name="klass_id" id="klass_id" />
	 </div>

	  <div class="form-group">
		<input type="submit" name="commit" value="Add Professor/Admin" class="btn btn-success" data-disable-with="Add Professor/Admin" />
	  </div>
	
	</t:form_with>		
  </div>
</div>

<br><br><br>
 
<c:if test="${department.isDepartmentAdmin(current_user)}">
  <div class="pull-right">
    <a data-toggle="collapse" href="#delete_hidden" class="btn btn-danger">Delete Class</a>
    <div id="delete_hidden" class="collapse collapse-in">
      <a data-toggle="collapse" href="#delete_hidden2" class="btn btn-danger">Really Delete Class</a>
      <div id="delete_hidden2" class="collapse collapse-in">
	    <t:link_to path="${UrlUtils.KLASSES_PATH}/:id" classBS="btn btn-warning" id="${klass.id}" confirm="Are you really, really, really sure you want to delete this class? THIS CANNOT BE UNDONE!">Really, Really Delete Class</t:link_to>
      </div>
    </div>
  </div>
</c:if>

</t:layoutj>