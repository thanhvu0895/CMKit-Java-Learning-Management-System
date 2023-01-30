<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${grade_category.title} Category">

<ol class="breadcrumb">
     <li><t:link_to path="${UrlUtils.DEPARTMENT_COURSES_PATH}" id="${departmentid}">Courses</t:link_to></li>
     <li><t:link_to path="${UrlUtils.COURSES_PATH}/:id" id="${course.id}">${course.course_code}&nbsp${course.title}</t:link_to></li>	
  <li class="active">New Grade Category </li>
</ol>

<h1>Editing ${grade_category.title} Category</h1>

<%@ include file="_form.jsp" %>
</t:layoutj>