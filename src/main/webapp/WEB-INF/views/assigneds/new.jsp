<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" timeZone="GMT-5"/>
<fmt:formatDate var="day" value="${now}" pattern="dd" timeZone="GMT-5"/>
<fmt:formatDate var="month" value="${now}" pattern="MM" timeZone="GMT-5"/>
<fmt:formatDate var="hour" value="${now}" pattern="HH" timeZone="GMT-5"/>
<fmt:formatDate var="minute" value="${now}" pattern="mm" timeZone="GMT-5"/>
<jsp:useBean id="monthNames" class="java.text.DateFormatSymbols"/>
<c:set value="${monthNames.months}" var="months" />

<t:layoutj pageTitle="${assignment.title} | Assign">
<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li><t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${klass.id}">${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</t:link_to></li>
  <li><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${assignment.id}">${assignment.title}</t:link_to></li>
  <li class="active">Assign</li>
</ol>

<h1>Assigning ${assignment.title}</h1>
<t:form_with url="${UrlUtils.ASSIGNMENT_ASSIGN_PATH}" secondId="${assignment.id}">
  <div class="field">
    <input value="${assignment.id}" type="hidden" name="assigned[assignment_id]" id="assigned_assignment_id">
  </div>
  
  <div class="field">
    <input value="${klass.id}" type="hidden" name="assigned[klass_id]" id="assigned_klass_id">
  </div>
  
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
  
  <div ${!assignment.studentResponsible() ? "hidden=true" : ""}>
  <div class="form-group">
    <label for="assigned_Maximum contributors to one submission:">Maximum contributors to one submission:</label>
	<input step="1" class="form-control" type="number" value="1" name="assigned[max_contributors]" id="assigned_max_contributors">
  </div>
  
  <div class="form-group">
  	<f:check_box check_box="allow_late_submissions" model="assigned"></f:check_box>
	<label for="assigned_allow_late_submissions">Allow late submissions</label>
  </div>
 
 <%@ include file="_resubmission_fields.jsp"%>
 
  </div>
  
  <div class="actions">
  	<input type="submit" name="commit" value="Assign" class="btn btn-success" data-disable-with="Assign">
  </div>
 </t:form_with>
</t:layoutj>