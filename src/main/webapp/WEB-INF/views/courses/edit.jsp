<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="${course.title} | Settings">
<ol class="breadcrumb">
  <%@ include file="_course_crumbs.jsp" %>
  <li class="active">Settings</li>
</ol>

<jsp:include page="_tabs.jsp"><jsp:param name="current" value=":settings"/></jsp:include>

<jsp:include page="_form.jsp"><jsp:param name="method" value="PATCH"/></jsp:include>

<br><br><br>

    <a data-toggle="collapse" href="#delete_hidden" class="btn btn-danger">Delete Course</a>
    <div id="delete_hidden" class="collapse collapse-in">
	  <a data-toggle="collapse" href="#delete_hidden2" class="btn btn-danger">Really Delete Course</a>
      <div id="delete_hidden2" class="collapse collapse-in">
        <t:link_to path="${UrlUtils.COURSES_PATH}/:id" classBS="btn btn-warning" id="${course.id}" method="DELETE" confirm="Are you really, really, really sure you want to delete this course and all of its content? THIS CANNOT BE UNDONE!">Really, Really Delete Course</t:link_to>
      </div>
    </div>	

</t:layoutj>
<%-- <h1>DONE</h1> --%>