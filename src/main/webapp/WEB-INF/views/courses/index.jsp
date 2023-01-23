<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:layoutj pageTitle="Courses">

<h1>Courses</h1>

<table class= "table table-hover">
  <thead>
    <tr>
      <th>Title</th>
      <th>Course code</th>
    </tr>
  </thead>


  <tbody>
   <c:forEach var="course" items="${courses}"> <%// TODO: COURSE ORDER BY COURSE CODE %>
      <tr>
        <td><t:link_to path="${UrlUtils.COURSES_PATH}/:id" id="${course.id}">${course.title}</t:link_to></td>
        <td>${course.course_code}</td>
      </tr>
  </c:forEach>
  
  </tbody>
</table>

<br>

<t:link_to path="${UrlUtils.NEW_COURSE_PATH}" classBS="btn btn-primary">New Course</t:link_to>
</t:layoutj>