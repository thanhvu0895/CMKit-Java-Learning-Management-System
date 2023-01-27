<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:choose>
<c:when test="${param.method == 'PATCH'}">
<t:form_with url="${UrlUtils.GRADE_CATEGORY_PATH}" id="${grade_category.id}" method="PATCH"> 
 	<div class="form-group">
    <label for="grade_category_title">Title</label>
    <input class="form-control" value="${grade_category.title}" type="text" name="grade_category[title]" id="grade_category_title" />
  </div>

  <div class="form-group">
    <label for="grade_category_Weight (percent)">Weight (percent)</label>
    <input class="form-control" step="any" type="number" value="${grade_category.weight}" name="grade_category[weight]" id="grade_category_weight" />
  </div>

  <div class="field">
    <input value="${course.id}" type="hidden" name="grade_category[course_id]" id="grade_category_course_id" />
  </div>
  
  <div class="actions">
    <input type="submit" name="commit" value="Update Grade category" class="btn btn-primary" data-disable-with="Update Grade category" />
  </div>
</t:form_with>

</c:when>

<c:otherwise>
<t:form_with url="${UrlUtils.GRADE_CATEGORIES_PATH}"> 
 	<div class="form-group">
    <label for="grade_category_title">Title</label>
    <input class="form-control" type="text" name="grade_category[title]" id="grade_category_title" />
  </div>

  <div class="form-group">
    <label for="grade_category_Weight (percent)">Weight (percent)</label>
    <input class="form-control" step="any" type="number" name="grade_category[weight]" id="grade_category_weight" />
  </div>

  <div class="field">
    <input value="${course.id}" type="hidden" name="grade_category[course_id]" id="grade_category_course_id" />
  </div>
  
  <div class="actions">
    <input type="submit" name="commit" value="Create Grade category" class="btn btn-primary" data-disable-with="Create Grade category" />
  </div>
</t:form_with>
</c:otherwise>
</c:choose>


