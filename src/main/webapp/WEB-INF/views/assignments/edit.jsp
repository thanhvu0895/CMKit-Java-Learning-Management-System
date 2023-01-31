<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${assignment.title} | Edit">

<ol class="breadcrumb">
  <%@ include file="_assignment_crumbs.jsp" %>
  <li><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${assignment.id}">${assignment.title}</t:link_to></li>
  <li class="active">Edit</li>
</ol>

<h1>Editing Assignment</h1>

<%@ include file="_form.jsp" %>
</t:layoutj>