<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="New Class">

<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.DEPARTMENT_COURSES_PATH}" id="${departmentid}">Courses</t:link_to></li>
  <li><t:link_to path="${UrlUtils.COURSES_PATH}/:id" id="${course.id}">${course.course_code}&nbsp;${course.title}&nbsp;</t:link_to></li>
  <li class="active">New Class</li>
</ol> 

<h1>New Class</h1>

<%@ include file="_form.jsp" %>


</t:layoutj>