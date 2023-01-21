<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:layoutj pageTitle="${department.title} | Courses">

<ol class="breadcrumb">
	<%@ include file="_department_crumbs.jsp" %>
  <li class="active">Courses</li>
</ol>
<jsp:include page="_tabs.jsp"><jsp:param value=":courses" name="current"/></jsp:include>

<center>
  <input type="checkbox" id="showInactive" onchange="hideRows()">  Show Inactive Courses
</center>

<table id="courses" class="table table-hover" data-toggle="table" data-classes="table-no-bordered" data-search="true" data-sort-name="Course_Code" data-sort-order="asc">
  <thead>
    <tr>
	  <th data-sortable="true" data-field="Title">Title</th>
      <th data-sortable="true" data-field="Course_Code">Course code</th>
	</tr>
  </thead>
  <tbody>
    <c:forEach var="course" items="${courses}">
	  <tr data-active="${course.active}" class="${course.active ? '' : 'active'}" ${course.active ? '' : 'hidden' }>
	    <td><t:link_to text="${course.title}" path="${UrlUtils.COURSES_PATH}/:id" id="${course.id}"></t:link_to></td>
        <td>${course.course_code}</td>
	  </tr>
	 </c:forEach>
  </tbody>
</table>
<br>
<t:link_to text="New Course" path="${UrlUtils.NEW_COURSE_PATH}?department=${department.id}" classBS="btn btn-success" ></t:link_to>

<script>	
	function hideRows()
	{
	  showInactive = document.getElementById("showInactive").checked==true
	  
	  for(r of document.getElementById('courses').rows)
	  {
	    if(!showInactive && r.getAttribute("data-active")=="false")
		{
	      r.hidden = "true"
		}
		else
		{
		  r.hidden=null
		}
	  }
	}
</script>
</t:layoutj>