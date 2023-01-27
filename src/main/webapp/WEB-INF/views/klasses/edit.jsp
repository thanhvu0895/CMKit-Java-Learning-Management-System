<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title}: ${klass.semester} ${klass.section}) | Settings">
<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
  <li class="active">Settings</li>
</ol>

<jsp:include page="../klasses/_tabs.jsp"><jsp:param name="current" value=":settings"/></jsp:include>

<br>

<div class="panel panel-default">
  <div class="panel-heading"><h4 class="panel-title"><strong>Options</strong></h4></div>
  <div class="panel-body">
	<jsp:include page="_form.jsp"><jsp:param name="method" value="PATCH"/></jsp:include>
  </div>
</div>

<br>


</t:layoutj>