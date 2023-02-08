<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:layoutj pageTitle="Classes">
	
<h1>Classes</h1>
<c:if test="${not empty student_klasses}">
<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#student_classes"><strong>Student Classes<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="student_classes" class="panel-collapse collapse in">
	  
	<table class= "table table-hover">
	  <thead>
		<tr>
			<th class="text-center">Course Title</th>
			<th class="text-center">Course code</th>
			<th class="text-center">Semester</th>
			<th class="text-center">Section</th>
			<th class="text-center" style="width: 20%">Grade</th>
			<th class="text-center" style="width: 20%">Assignment Status</th>
		</tr>
	  </thead>

	  <tbody>
	  <c:forEach var="k" items="${student_klasses}" varStatus="loop">
	    <c:set var="course" value="${student_courses[loop.index]}"/>
	  	<c:if test="${k.isCurrent()}">
	  	  <tr>
			<td><t:link_to path="${UrlUtils.KLASSES_PATH}/:id" id="${k.id}">${course.title}</t:link_to></td>
			<td>${course.course_code}</td>
			<td>${k.semester}</td>
			<td>${k.section}</td>
			<td>
			<%-- <% g = c.get_student_grade_percent(current_user).round(2) %> --%>
              <c:set var="g" value="100"/>
          
              <div class="progress" style="position: relative; text-align: center">
                <div class="progress-bar" role="progressbar" aria-hidden="true" style="width:${g}%;background-color:${UrlUtils.get_color_for_grade(g/100.0)}"></div>
                <span style="position:absolute; left: 0; right: 0">
          		  ${g}%    
                </span>
          	  </div>
        	</td>
			<td>
		  	  STUDENT STATUS BAR<%-- <%= render "student_status_bar", assigneds: c.assigned %> --%>
			</td>
	      </tr>
		</c:if>				  
	  </c:forEach>	
	  </tbody>
	</table>				
				
	<div class="panel-body">
	  <div class="panel panel-default">
	    <div class="panel-heading">
		  <h4 class="panel-title">
			<a data-toggle="collapse" href="#prev_student_classes"><strong>Past Classes<span class="caret"></span></strong></a>
		  </h4>
		</div>
	  <div id="prev_student_classes" class="panel-collapse collapse">


	  <table class="table table-hover">
		<thead>
		  <tr>
			<th>Course Title</th>
			<th>Course code</th>
			<th>Semester</th>
			<th>Section</th>
			<th style="width: 20%">Grade</th>
		  </tr>
		</thead>
		
		<tbody>
	    <c:forEach var="k" items="${student_klasses}" varStatus="loop">
		<c:set var="course" value="${student_courses[loop.index]}"/>
		<c:if test="${k.isPast()}">
	  	  <tr>
			<td><t:link_to path="${UrlUtils.KLASSES_PATH}/:id" id="${k.id}">${course.title}</t:link_to></td>
			<td>${course.course_code}</td>
			<td>${k.semester}</td>
			<td>${k.section}</td>
			<td>
			<%-- <% g = c.get_student_grade_percent(current_user).round(2) %> --%>
              <c:set var="g" value="100"/>
              <div class="progress" style="position: relative; text-align: center">
                <div class="progress-bar" role="progressbar" aria-hidden="true" style="width:${g}%;background-color:${UrlUtils.get_color_for_grade(g/100.0)}"></div>
                <span style="position:absolute; left: 0; right: 0">
          		  ${g}%    
                </span>
          	  </div>
        	</td>
			<td>
		  	  STUDENT STATUS BAR<%-- <%= render "student_status_bar", assigneds: c.assigned %> --%>
			</td>
	      </tr>
		</c:if>				  
	  	</c:forEach>
	    </tbody>
	  </table>	
	  </div>
	 </div>
    </div>
   </div>
  </div>
</div>
</c:if>

<c:if test="${not empty grader_klasses}">
<br>
<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#grader_classes"><strong>Grader/TA Classes<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="grader_classes" class="panel-collapse collapse in">


	<table class="table table-hover table-responsive table-bordered">
	  <thead>
		<tr>
	  	  <th>Course Title</th>
	  	  <th>Course code</th>
	  	  <th>Semester</th>
	  	  <th>Section</th>
	  	  <th>Assignments Ungraded</th>
		</tr>
	  </thead>

	  <tbody>
	  <c:forEach var="k" items="${grader_klasses}" varStatus="loop">
	    <c:set var="course" value="${grader_courses[loop.index]}"/>
	    <c:if test="${k.isCurrent()}">
	  	  <tr>
			<td><t:link_to path="${UrlUtils.KLASSES_PATH}/:id" id="${k.id}">${course.title}</t:link_to></td>
			<td>${course.course_code}</td>
			<td>${k.semester}</td>
			<td>${k.section}</td>
			<td>
ASSIGNMENT UNGRADED
	<%--	  <% to_grade = current_user.assigned_graders.select{|ag| ag.assigned.klass==c && ag.assigned.submissions.map{|s| s.contributors.any? && s.turned_in? && (!s.graded? || s.has_active_regrade_request?)}.include?(true)}.count %>
			  <%# to_grade = c.assigned.select{ |ad| ad.assigned_graders.map{|ag| ag.user}.include?(current_user)}.map{|a| a.submissions.where{|s| !s.graded? || s.has_active_regrade_request?}}.flatten.size %>
			  <% if to_grade == 0 %>
			    <span style="color:green">0</span>
			  <% else %>
			    <span style="color:red"><b><%=to_grade%></b></span>
			  <% end %> --%>
        	</td>
	      </tr>
		</c:if>				  
	  </c:forEach>	
	  </tbody>
	</table>				
				
	<div class="panel-body">
	  <div class="panel panel-default">
	    <div class="panel-heading">
		  <h4 class="panel-title">
			<a data-toggle="collapse" href="#prev_grader_classes"><strong>Past Classes<span class="caret"></span></strong></a>
		  </h4>
		</div>
	  <div id="prev_grader_classes" class="panel-collapse collapse">

	  <table class="table table-hover">
		<thead>
		  <tr>
		  	  <th>Course Title</th>
		  	  <th>Course code</th>
		  	  <th>Semester</th>
		  	  <th>Section</th>
		  </tr>
		</thead>
		
		<tbody>
	  <c:forEach var="k" items="${grader_klasses}" varStatus="loop">
		<c:set var="course" value="${grader_courses[loop.index]}"/>
		<c:if test="${k.isPast()}">
	  	  <tr>
			<td><t:link_to path="${UrlUtils.KLASSES_PATH}/:id" id="${k.id}">${course.title}</t:link_to></td>
			<td>${course.course_code}</td>
			<td>${k.semester}</td>
			<td>${k.section}</td>
	      </tr>
		</c:if>				  
	  </c:forEach>
	    </tbody>
	  </table>
			</div>
		</div>
	</div>
  </div>
 </div>
<br>
</div>
</c:if>

<c:if test="${not empty professor_klasses}">
<br>
<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#professor_classes"><strong>Professor<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="professor_classes" class="panel-collapse collapse in">


		<table class="table table-hover table-responsive table-bordered">
		  <thead>
			<tr>
			  <th>Course Title</th>
			  <th>Course code</th>
			  <th>Semester</th>
			  <th>Section</th>
			  <th>Students</th>
			</tr>
		  </thead>
	
		  <tbody>
		  <c:forEach var="k" items="${professor_klasses}" varStatus="loop">
		    <c:set var="course" value="${professor_courses[loop.index]}"/>
		  	<c:if test="${k.isCurrent()}">
		  	  <tr>
			    <td><t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${k.id}">${course.title}</t:link_to></td>
				<td>${course.course_code}</td>
				<td>${k.semester}</td>
				<td>${k.section}</td>
				<td>${course.student_count}</td>
		      </tr>
			</c:if>				  
		  </c:forEach>	
		  </tbody>
		</table>				
			
						
		<div class="panel-body">
		  <div class="panel panel-default">
		    <div class="panel-heading">
			  <h4 class="panel-title">
				<a data-toggle="collapse" href="#prev_professor_classes"><strong>Past Classes<span class="caret"></span></strong></a>
			  </h4>
			</div>
		  <div id="prev_professor_classes" class="panel-collapse collapse">

		  <table class="table table-hover">
			<thead>
			  <tr>
			  	  <th>Course Title</th>
			  	  <th>Course code</th>
			  	  <th>Semester</th>
			  	  <th>Section</th>
			  </tr>
			</thead>
			
			<tbody>
		  <c:forEach var="k" items="${professor_klasses}" varStatus="loop">
			<c:set var="course" value="${professor_courses[loop.index]}"/>
			<c:if test="${k.isPast()}">
		  	  <tr>
			    <td><t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${k.id}">${course.title}</t:link_to></td>
				<td>${course.course_code}</td>
				<td>${k.semester}</td>
				<td>${k.section}</td>
		      </tr>
			</c:if>				  
		  </c:forEach>
		    </tbody>
		 </table>
		 </div>
	   </div>
    </div>
  </div>
</div>
<br>
</div>
</c:if>

<c:if test="${not empty admin_klasses}">
<br>
<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#all_classes"><strong>[Admin] All Classes<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="all_classes" class="panel-collapse collapse in">


	<table class="table table-hover table-responsive table-bordered">
	  <thead>
		<tr>
		  <th>Course Title</th>
		  <th>Course code</th>
		  <th>Semester</th>
		  <th>Section</th>
		</tr>
	  </thead>

	  <tbody>
	  <c:forEach var="k" items="${admin_klasses}" varStatus="loop">
	    <c:set var="course" value="${admin_courses[loop.index]}"/>
	  	<c:if test="${k.isCurrent()}">
	  	  <tr>
			<td><t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${k.id}">${course.title}</t:link_to></td>
			<td>${course.course_code}</td>
			<td>${k.semester}</td>
			<td>${k.section}</td>
	      </tr>
		</c:if>				  
	  </c:forEach>	
	  </tbody>
	</table>				
			
	<div class="panel-body">
	  <div class="panel panel-default">
	    <div class="panel-heading">
		  <h4 class="panel-title">
			<a data-toggle="collapse" href="#prev_all_classes"><strong>All Past Classes<span class="caret"></span></strong></a>
		 </h4>
	  	</div>
		<div id="prev_all_classes" class="panel-collapse collapse">

		<table class="table table-hover">
		  <thead>
			<tr>
			  <th>Course Title</th>
			  <th>Course code</th>
		  	  <th>Semester</th>
		  	  <th>Section</th>
			</tr>
		  </thead>
		  <tbody>
	  	  <c:forEach var="k" items="${admin_klasses}" varStatus="loop">
		  <c:set var="course" value="${admin_courses[loop.index]}"/>
		  <c:if test="${k.isPast()}">
		  	<tr>
			  <td><t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${k.id}">${course.title}</t:link_to></td>
			  <td>${course.course_code}</td>
			  <td>${k.semester}</td>
			  <td>${k.section}</td>
		    </tr>
		  </c:if>				  
	  	  </c:forEach>
	      </tbody>
	    </table>
		</div>
	   </div>
	  </div>
	</div>
 </div>
<br>
</div>
</c:if>

</t:layoutj>