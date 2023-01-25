<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="New Department">
<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.DEPARTMENTS_PATH}">Departments</t:link_to></li>
  <li class="active">New</li>
</ol>

<h1>New Department</h1>

<jsp:include page="_form.jsp"/>
</t:layoutj>
<%-- <h1>DONE</h1> --%>