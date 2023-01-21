<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<t:layoutj pageTitle="New Course">

<ol class="breadcrumb">
  <%@ include file="../departments/_department_crumbs.jsp" %>
  <li class="active">New Course</li>
</ol>

<h1>New Course</h1>

<%@ include file="_form.jsp" %>

</t:layoutj>