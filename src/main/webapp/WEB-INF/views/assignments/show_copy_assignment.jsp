<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="pageTitle" value="Copy Assignment To ${course.title}"/>

<c:if test="${not empty klass}">
  <c:set var="pageTitle" value="Copy Assignment To ${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}"/>
</c:if>

<t:layoutj pageTitle="${pageTitle}">
<ol class="breadcrumb">
<c:choose>
 <c:when test="${not empty klass}">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
 </c:when>
 <c:otherwise>
  <li><t:link_to path="${UrlUtils.DEPARTMENT_COURSES_PATH}" id="${departmentid}">Courses</t:link_to></li>
  <li><t:link_to path="${UrlUtils.COURSES_PATH}/:id" id="${course.id}">${course.course_code}&nbsp${course.title}</t:link_to></li>
 </c:otherwise>
</c:choose>
  <li class="active">Copy Assignment</li>
</ol>

<%-- Only show courses and classes that user has admin permission for --%>
<div class="panel-group">

<c:forEach items="${current_user.getDepartmentsCheckAdmin()}" var="d">

	<div class="panel panel-default">
		  <div class="panel-heading">
			<h4 class="panel-title">
			  <a data-toggle="collapse" href="#d${d.id}"><strong>${d.title} department courses<span class="caret"></span></strong></a>		
			</h4>
		  </div>
		  <div id="d${d.id}" class="panel-collapse collapse in">
		  <div class="panel-body">
		  <c:forEach var="c" items="${d.getCourses()}">
		  <div class="panel panel-default">
			  <div class="panel-heading">
				<h4 class="panel-title">
				<a data-toggle="collapse" href="#c${c.id}"><strong>${c.title}&nbsp;${c.course_code} assignments<span class="caret"></span></strong></a>
				 </h4>
			  </div>
			  <div id="c${c.id}" class="panel-collapse collapse">
			    <div class="panel-body">
			    
			      <table class="table table-hover">
			  		<thead></thead>
					<tbody>
					 <c:forEach var="a" items="${c.getAssignments()}">
					 <tr>
					  <td>${a.title}</td>
					  <td>
						<c:choose> 
						 <c:when test="${not empty klass}"><t:link_to path="${UrlUtils.NEW_ASSIGNMENT_PATH}?copy=${a.id}&class=${klass.id}" classBS="btn btn-success">Copy</t:link_to></c:when>
						 <c:otherwise><t:link_to path="${UrlUtils.NEW_ASSIGNMENT_PATH}?copy=${a.id}&course=${c.id}" classBS="btn btn-success">Copy</t:link_to></c:otherwise>
						</c:choose> 
					  </td>
					 </tr>
					  </c:forEach>
					</tbody>
			   	  </table>	  
				</div>
			  </div>
			</div>
		  </c:forEach>
		  </div>
		</div>
	  </div>
</c:forEach>
	</div>

</t:layoutj>