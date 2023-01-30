<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${assignment.title} | New problem">
<ol class="breadcrumb">
 <%@ include file="../assignments/_assignment_crumbs.jsp" %>
 <li><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${assignment.id}">${assignment.title}</t:link_to></li>
 <li><t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEMS_PATH}" secondId="${assignment.id}">Rubric</t:link_to></li>
 <li class="active">New Problem</li>
</ol>

<h1>New Problem</h1>

<%@ include file="_form.jsp" %>
</t:layoutj>