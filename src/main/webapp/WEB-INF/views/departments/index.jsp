<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:layoutj pageTitle="Departments">
<h1>Departments</h1>

<table class="table table-hover" data-toggle="table" data-classes="table-no-bordered" data-search="true">
  <thead>
    <tr>
      <th>Title</th>
    </tr>
  </thead>

  <tbody>
  <c:forEach var="department" items="${departments}">
      <tr>
        <td><t:link_to path="${UrlUtils.DEPARTMENT_COURSES_PATH}" id="${department.id}">${department.title}</t:link_to></td>
      </tr>
  </c:forEach>
  </tbody>
</table>

<br>

<t:link_to path="${UrlUtils.NEW_DEPARTMENT_PATH}" classBS="btn btn-primary" >New Department</t:link_to>

</t:layoutj>