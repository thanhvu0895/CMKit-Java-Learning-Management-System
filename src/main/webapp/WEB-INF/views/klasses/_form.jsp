<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<fmt:formatDate var="day" value="${now}" pattern="dd" />
<fmt:formatDate var="month" value="${now}" pattern="MM" />

<c:if test="${param.method == 'PATCH'}">
  <fmt:parseDate  value="${klass.start_date}" var="selected_start_date" type="date" pattern="yyyy-MM-dd"/>
  <fmt:formatDate var="start_year" value="${selected_start_date}" pattern="yyyy" />
  <fmt:formatDate var="start_day" value="${selected_start_date}" pattern="dd" />
  <fmt:formatDate var="start_month" value="${selected_start_date}" pattern="MM" />
  
  <fmt:parseDate  value="${klass.end_date}" var="selected_end_date" type="date" pattern="yyyy-MM-dd"/>
  <fmt:formatDate var="end_year" value="${selected_end_date}" pattern="yyyy" />
  <fmt:formatDate var="end_day" value="${selected_end_date}" pattern="dd" />
  <fmt:formatDate var="end_month" value="${selected_end_date}" pattern="MM" />
</c:if>
<jsp:useBean id="monthNames" class="java.text.DateFormatSymbols" />
<c:set value="${monthNames.months}" var="months" />
<t:form_with url="${param.method == 'PATCH' ? UrlUtils.KLASSES_PATH.concat('/:id') : UrlUtils.KLASSES_PATH}" id="${klass.id}" method="${param.method == 'PATCH' ? 'PATCH' : ''}"> 

  <div class="field">
	<input value="${course.id}" type="hidden" name="klass[course_id]" id="klass_course_id" />
  </div>

  <div class="form-group">
	<label for="klass_semester">Semester</label>
	<input class="form-control" <c:if test="${param.method == 'PATCH'}">value="${klass.semester}"</c:if> type="text" name="klass[semester]" id="klass_semester" />
  </div>

  <div class="form-group">
	<label for="klass_section">Section</label>
	<input class="form-control" type="number" name="klass[section]" <c:if test="${param.method == 'PATCH'}">value="${klass.section}"</c:if> id="klass_section" />
  </div>
  
  <c:choose>
  <c:when test="${param.method != 'PATCH'}">
    <div class="form-group">
    <label for="klass_start_date">Start date</label>
    <select id="klass_start_date_1i" name="klass[start_date(1i)]">
    <c:forEach begin="${year - 5}" end="${year + 5}" var="y">
	  <c:choose>
		<c:when test="${year != y}"><option value="${y}">${y}</option></c:when> 
		<c:otherwise><option value="${y}" selected="selected">${y}</option></c:otherwise>
	  </c:choose>
	</c:forEach>
    </select>
    <select id="klass_start_date_2i" name="klass[start_date(2i)]">
	<c:forEach begin="1" end="12" var="m">
	  <c:choose>
		<c:when test="${month != m}"><option value="${m}">${months[m - 1]}</option></c:when> 
		<c:otherwise><option value="${m}" selected="selected">${months[m - 1]}</option></c:otherwise>
	  </c:choose>
     </c:forEach>
    </select>
    <select id="klass_start_date_3i" name="klass[start_date(3i)]">
	<c:forEach begin="1" end="31" var="d">
      <c:choose>
		<c:when test="${day != d}"><option value="${d}">${d}</option></c:when> 
		<c:otherwise><option value="${d}" selected="selected">${d}</option></c:otherwise>
      	</c:choose>
      </c:forEach>
    </select>
  </div>
   
  <div class="form-group">
	<label for="klass_end_date">End date</label>
	<select id="klass_end_date_1i" name="klass[end_date(1i)]">
    <c:forEach begin="${year - 5}" end="${year + 5}" var="y">
	  <c:choose>
		<c:when test="${year != y}"><option value="${y}">${y}</option></c:when> 
		<c:otherwise><option value="${y}" selected="selected">${y}</option></c:otherwise>
	  </c:choose>
	</c:forEach>
    </select>
    <select id="klass_end_date_2i" name="klass[end_date(2i)]">
	<c:forEach begin="1" end="12" var="m">
	  <c:choose>
		<c:when test="${month != m}"><option value="${m}">${months[m - 1]}</option></c:when> 
		<c:otherwise><option value="${m}" selected="selected">${months[m - 1]}</option></c:otherwise>
	  </c:choose>
     </c:forEach>
    </select>
    <select id="klass_end_date_3i" name="klass[end_date(3i)]">
	<c:forEach begin="1" end="31" var="d">
      <c:choose>
		<c:when test="${day != d}"><option value="${d}">${d}</option></c:when> 
		<c:otherwise><option value="${d}" selected="selected">${d}</option></c:otherwise>
      	</c:choose>
      </c:forEach>
    </select>
  </div>
  </c:when>
  <c:otherwise>
    <div class="form-group">
    <label for="klass_start_date">Start date</label>
    <select id="klass_start_date_1i" name="klass[start_date(1i)]">
    <c:forEach begin="${start_year - 5}" end="${start_year + 5}" var="y">
	  <c:choose>
		<c:when test="${start_year != y}"><option value="${y}">${y}</option></c:when> 
		<c:otherwise><option value="${y}" selected="selected">${y}</option></c:otherwise>
	  </c:choose>
	</c:forEach>
    </select>
    <select id="klass_start_date_2i" name="klass[start_date(2i)]">
	<c:forEach begin="1" end="12" var="m">
	  <c:choose>
		<c:when test="${start_month != m}"><option value="${m}">${months[m - 1]}</option></c:when> 
		<c:otherwise><option value="${m}" selected="selected">${months[m - 1]}</option></c:otherwise>
	  </c:choose>
     </c:forEach>
    </select>
    <select id="klass_start_date_3i" name="klass[start_date(3i)]">
	<c:forEach begin="1" end="31" var="d">
      <c:choose>
		<c:when test="${start_day != d}"><option value="${d}">${d}</option></c:when> 
		<c:otherwise><option value="${d}" selected="selected">${d}</option></c:otherwise>
      	</c:choose>
      </c:forEach>
    </select>
  </div>
   
  <div class="form-group">
	<label for="klass_end_date">End date</label>
	<select id="klass_end_date_1i" name="klass[end_date(1i)]">
    <c:forEach begin="${end_year - 5}" end="${end_year + 5}" var="y">
	  <c:choose>
		<c:when test="${end_year != y}"><option value="${y}">${y}</option></c:when> 
		<c:otherwise><option value="${y}" selected="selected">${y}</option></c:otherwise>
	  </c:choose>
	</c:forEach>
    </select>
    <select id="klass_end_date_2i" name="klass[end_date(2i)]">
	<c:forEach begin="1" end="12" var="m">
	  <c:choose>
		<c:when test="${end_month != m}"><option value="${m}">${months[m - 1]}</option></c:when> 
		<c:otherwise><option value="${m}" selected="selected">${months[m - 1]}</option></c:otherwise>
	  </c:choose>
     </c:forEach>
    </select>
    <select id="klass_end_date_3i" name="klass[end_date(3i)]">
	<c:forEach begin="1" end="31" var="d">
      <c:choose>
		<c:when test="${end_day != d}"><option value="${d}">${d}</option></c:when> 
		<c:otherwise><option value="${d}" selected="selected">${d}</option></c:otherwise>
      	</c:choose>
      </c:forEach>
    </select>
  </div>
  </c:otherwise>  
  </c:choose>
  
  <div class="actions">
    <input type="submit" name="commit" value="Save Class" class="btn btn-primary" data-disable-with="Save Class" />
  </div>

</t:form_with>
