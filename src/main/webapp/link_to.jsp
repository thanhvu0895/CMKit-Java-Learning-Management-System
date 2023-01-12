<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:forEach var="user" items="${users}">
  <t:link_to_copy path="${UrlUtils.RESEND_USER_INVITE_PATH}" id="${user.id}">
  	<p>${user.id}</p>
  </t:link_to_copy>
</c:forEach>