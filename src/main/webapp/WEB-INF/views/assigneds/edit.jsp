<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:parseDate  value="${assigned.due_date}" var="due_date" type="both" pattern="yyyy-MM-dd'T'HH:mm" timeZone="GMT-5"/>
<fmt:formatDate var="year" value="${due_date}" pattern="yyyy" timeZone="GMT-5"/>
<fmt:formatDate var="day" value="${due_date}" pattern="dd" timeZone="GMT-5"/>
<fmt:formatDate var="month" value="${due_date}" pattern="MM" timeZone="GMT-5"/>
<fmt:formatDate var="hour" value="${due_date}" pattern="HH" timeZone="GMT-5"/>
<fmt:formatDate var="minute" value="${due_date}" pattern="mm" timeZone="GMT-5"/>

<jsp:useBean id="monthNames" class="java.text.DateFormatSymbols"/>
<c:set value="${monthNames.months}" var="months" />

<t:layoutj pageTitle="${assignment.title} | Submission Settings">

<ol class="breadcrumb">
  <%@ include file="../assigneds/_grading_crumbs.jsp"%>
  <li class="active">Submission Settings</li>
</ol>

<jsp:include page="../assigneds/_tabs.jsp"><jsp:param name="current" value=":submission_settings"/></jsp:include>

<h3>Submission Settings for ${assignment.title}</h3>

<t:form_with url="${UrlUtils.ASSIGNMENT_ASSIGNED_PATH}" secondId="${assignment.id}" id="${assigned.id}" method="PATCH">
<div class="form-group">
<label for="assigned_due_date">Due date</label>
    <select id="assigned_due_date_1i" name="assigned[due_date(1i)]">
    <c:forEach begin="${year - 5}" end="${year + 5}" var="y">
	  <c:choose>
		<c:when test="${year != y}"><option value="${y}">${y}</option></c:when> 
		<c:otherwise><option value="${y}" selected="selected">${y}</option></c:otherwise>
	  </c:choose>
	</c:forEach>
    </select>
    <select id="assigned_due_date_2i" name="assigned[due_date(2i)]">
	<c:forEach begin="1" end="12" var="M">
	  <c:choose>
		<c:when test="${month != M}"><option value="${M}">${months[M - 1]}</option></c:when> 
		<c:otherwise><option value="${M}" selected="selected">${months[M - 1]}</option></c:otherwise>
	  </c:choose>
     </c:forEach>
    </select>
    <select id="assigned_due_date_3i" name="assigned[due_date(3i)]">
	<c:forEach begin="1" end="31" var="d">
      <c:choose>
		<c:when test="${day != d}"><option value="${d}">${d}</option></c:when> 
		<c:otherwise><option value="${d}" selected="selected">${d}</option></c:otherwise>
      	</c:choose>
      </c:forEach>
    </select>
    — 
    <select id="assigned_due_date_4i" name="assigned[due_date(4i)]">
    <c:forEach begin="0" end="23" var="h">
      <c:choose>
      	<c:when test="${h < 10 && hour != h}"><option value="${h}">0${h}</option></c:when>
      	<c:when test="${h < 10 && hour == h}"><option value="${h}" selected>0${h}</option></c:when>
      	<c:when test="${h >= 10 && hour != h}"><option value="${h}">${h}</option></c:when>
      	<c:when test="${h >= 10 && hour == h}"><option value="${h}" selected>${h}</option></c:when>
      	</c:choose>
      </c:forEach>
    </select>
    :
    <select id="assigned_due_date_5i" name="assigned[due_date(5i)]">
    <c:forEach begin="0" end="59" var="m">
      <c:choose>
      	<c:when test="${m < 10 && minute != m}"><option value="${m}">0${m}</option></c:when>
      	<c:when test="${m < 10 && minute == m}"><option value="${m}" selected>0${m}</option></c:when>
		<c:when test="${m >= 10 && minute != m}"><option value="${m}">${m}</option></c:when> 
		<c:when test="${m >= 10 && minute == m}"><option value="${m}" selected>${m}</option></c:when>
      	</c:choose>
      </c:forEach>
    </select>
</div>

<c:if test="${assignment.studentResponsible()}">
  <div class="form-group">
    <label for="assigned_Maximum contributors to one submission:">Maximum contributors to one submission:</label>
	<input step="1" class="form-control" type="number" value="${assigned.max_contributors}" name="assigned[max_contributors]" id="assigned_max_contributors">
  </div>
  
  <div class="form-group">
  	<f:check_box check_box="allow_late_submissions" model="assigned" value="${assigned.allow_late_submissions}"></f:check_box>
	<label for="assigned_allow_late_submissions">Allow late submissions</label>
  </div>
 
 <%@ include file="_resubmission_fields.jsp"%>
</c:if>

<br> 

<div class="actions">
  <input type="submit" name="commit" value="Update" class="btn btn-success" data-disable-with="Update">
</div>
</t:form_with>

<br><br>

<p>
<t:link_to path="${UrlUtils.ASSIGNMENT_UNASSIGN_PATH}" classBS="btn btn-danger" id="${assigned.id}" secondId="${assignment.id}" confirm="Are you sure you want to unassign this assignment? ALL EXISTING SUBMISSIONS AND GRADES WILL BE DELETED!">Unassign</t:link_to>
</p>
</t:layoutj>