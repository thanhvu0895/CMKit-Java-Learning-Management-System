<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="url" required="true"%>
<%@ attribute name="id" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<c:set var="url" value="${pageContext.request.contextPath}${url}"/> <!-- ADD CONTEXT PATH TO url URL  -->

<form url="${url}" method="post" accept-charset="UTF-8" data-remote="true" method="post">
	<jsp:doBody/>
</form>

<%-- 

/**
 *	Constructor with url and id used at the same time 
 * @param: url: url url for forms  
 * @param: id: user's id 
 * @return: this form accept url with :id in path, then replace :id to url  
 * 
/ 
<t:form_with url="${}" id="${}"> 
 	FORM BODY GOES HERE
</t:form_with>


/**
 * Constructor with url only to be used in jsp
 * @param: url -> String that represents path to servlet
 * @return: an url url with context path added
 *
/
<t:form_with url="${}">
	FORM BODY GOES HERE  
</t:form_with>

 --%>