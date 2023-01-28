<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${(params == ':copy') ? 'Copy Assignment' : 'New Assignment'}">
<ol class="breadcrumb">
 <c:choose><c:when test="${not empty klass}">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
 </c:when>
 <c:otherwise>
  <li><t:link_to path="${UrlUtils.DEPARTMENT_COURSES_PATH}" id="${departmentid}">Courses</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}</li>
 </c:otherwise></c:choose>
 
 <c:choose>
  <c:when test="${params == ':copy'}"><li class="active">Copy Assignment</li></c:when>
  <c:otherwise><li class="active">New Assignment</li></c:otherwise>
 </c:choose>
</ol>

 <c:choose>
  <c:when test="${params == ':copy'}"><h1>Copy ${assignment.title}</h1></c:when>
  <c:otherwise><h1>New Assignment</h1></c:otherwise>
 </c:choose>

<%@ include file="_form.jsp" %>
</t:layoutj>