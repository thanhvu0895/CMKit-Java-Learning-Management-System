<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:layoutj pageTitle="${department.title} | Classes">
<ol class="breadcrumb">
  <%@ include file="_department_crumbs.jsp" %>
  <li class="active">Classes</li>
</ol>
<jsp:include page="_tabs.jsp"><jsp:param name="current" value=":klasses"/></jsp:include>

<table class="table table-hover" data-toggle="table" data-classes="table-no-bordered" data-search="true" data-sort-name="Course_Code" data-sort-order="asc">
  <thead>
    <tr>
	  <th data-sortable="true" data-field="Title">Course Title</th>
      <th data-sortable="true" data-field="Course_Code">Course code</th>
      <th data-sortable="true" data-field="Semester">Semester</th>
      <th data-sortable="true" data-field="Section">Section</th>
	  <th>View</th>
	</tr>
  </thead>
  <tbody>
  <c:forEach var="klass" items="${klasses}" varStatus="loop">
	  <tr>
	    <td>${courses[loop.index].title}</td>
        <td>${courses[loop.index].course_code}</td>
        <td>${klass.semester}</td>
        <td>${klass.section}</td>
        <td><t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" classBS="btn btn-default"  secondId="${klass.id}">View</t:link_to></td>
	  </tr>
	</c:forEach>
  </tbody>
</table>

</t:layoutj>