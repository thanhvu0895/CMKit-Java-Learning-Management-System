<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title}: ${klass.semester} ${klass.section}) | Students">

<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
  <li class="active">Settings</li>
</ol>

<jsp:include page="../klasses/_tabs.jsp"><jsp:param name="current" value=":students"/></jsp:include>

<br>

<div class="panel panel-default">
  <div class="panel-heading"><h4 class="panel-title"><strong>Add Students</strong></h4></div>
  <div class="panel-body">
	<t:form_with url="${UrlUtils.STUDENTS_PATH}" classBS="form-inline">
	  <div class="form-group">
		<label for="Emails (separate multiple with commas)">Emails (separate multiple with commas)</label>
		<input class="form-control" type="text" name="emails" id="emails">
	  </div>

	  <div class="form-group">
		<input value="${klass.id}" type="hidden" name="klass_id" id="klass_id">
	  </div>

	  <div class="form-group">
		<input type="submit" name="commit" value="Add Student" class="btn btn-success" data-disable-with="Add Student">
	  </div>
	</t:form_with>
  </div>
</div>

<center><h3>Students</h3></center>

<p>
  <table class="table table-hover" data-toggle="table" data-classes="table-no-bordered" data-search="true">
	<thead>
	  <tr>
		<th data-sort-name="_name_data.raw-name" data-sortable="true" data-field="name">Name</th>
		<th data-sortable="true" data-field="email">Email</th>
		<th>Assignment Statuses</th>
		<th>Assignment Grades</th>
		<th data-sort-name="_grade_data.raw-grade" data-sortable="true" data-field="grade">Class Grade</th>
	  </tr>
	</thead>
	<tbody>
	 <c:forEach var="student" items="${students}" varStatus="loop">
	  <tr>
	   <td data-raw-name="${student_users[loop.index].getFull_name()}"><t:link_to path="${UrlUtils.STUDENTS_PATH}/:id" id="${student.id}">${student_users[loop.index].getFull_name()}</t:link_to></td>
	   <td><a href="mailto:${student_users[loop.index].email}">${student_users[loop.index].email}</a></td>
	   <td>
		    <div class="progress"> 
			  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="1" aria-valuemin="0" aria-valuemax="3" style="width:33.33333333333333%">
				1 ready to grade
			  </div>
			  <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="1" aria-valuemin="0" aria-valuemax="3" style="width:33.33333333333333%">
				1 overdue
			  </div>
			  <div>
				<center>1 missing</center>
			  </div>
			</div>
		</td>
	   <td><div class="progress"></div>
	   <td><div class="progress" style="position: relative; text-align: center">
			  <div class="progress-bar" role="progressbar" aria-hidden="true" style="width:233.0%;background-color:rgb(0,255,0)"></div>
		      <span style="position:absolute; left: 0; right: 0">
			    233.0%
			  </span>
		    </div></td>
	  </tr>
	 </c:forEach>
	</tbody>
  </table>
	
	
  <center>
    <a href="mailto:${student_emails}" class="btn btn-default">Email All Students</a>
  </center>

</t:layoutj>