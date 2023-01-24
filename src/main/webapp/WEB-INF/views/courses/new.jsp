<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="New Course">

<ol class="breadcrumb">
  <%@ include file="../departments/_department_crumbs.jsp" %>
  <li class="active">New Course</li>
</ol>

<h1>New Course</h1>

<t:form_with url="${UrlUtils.COURSES_PATH}">

  <input type="hidden" name="department" id="department" value="${department.id}" />
  <div class="form-group">
	<label for="course_title">Title</label>
	<input class="form-control" type="text" name="course[title]" id="course_title" />
  </div>

  <div class="form-group">
	<label for="course_course_code">Course code</label>
	<input class="form-control" type="text" name="course[course_code]" id="course_course_code" />
  </div>
  
  <div class="form-group">
	<f:check_box check_box="active" model="course" value="true"></f:check_box>
	<label for="course_Active">Active</label>
  </div>
  
  <div class="actions">
	<input type="submit" name="commit" value="Create Course" class="btn btn-success" data-disable-with="Create Course" />
  </div>
</t:form_with>

</t:layoutj>