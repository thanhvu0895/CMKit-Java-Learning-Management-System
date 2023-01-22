<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="${department.title} | Files">
<ol class="breadcrumb">
  <%@ include file="_department_crumbs.jsp" %>
  <li class="active">Files</li>
</ol>

<jsp:include page="_tabs.jsp"><jsp:param name="current" value=":files"/></jsp:include>

<br>
Department files are available to all professors in the department. 
In addition, a student or grader in any class in the department can access the files in their respective folders if given a link.
To copy a link, right click on the download or view button and click <code>copy link location</code>. (The exact wording may change depending on your browser and operating system.)


</t:layoutj>