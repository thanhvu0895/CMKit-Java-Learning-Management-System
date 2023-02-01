<%@ tag trimDirectiveWhitespaces="true" language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="url" required="true"%>
<%@ attribute name="method" required="false"%>
<%@ attribute name="formId" required="false"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="secondId" required="false"%>
<%@ attribute name="thirdId" required="false"%>
<%@ attribute name="classBS" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<c:set var="url" value="${pageContext.request.contextPath}${url}"/> <!-- ADD CONTEXT PATH TO url URL  -->
<c:set var="url" value ="${UrlUtils.putIdInPath(pageContext.getAttribute('url'), pageContext.getAttribute('id'))}"/>
<c:set var="url" value="${UrlUtils.putSecondInPath(pageContext.getAttribute('url'), pageContext.getAttribute('secondId'))}" />
<c:set var="url" value="${UrlUtils.putSecondInPath(pageContext.getAttribute('url'), pageContext.getAttribute('thirdId'))}" />

<form action="${url}" method="post" <c:if test="${not empty formId}">id="${formId}"</c:if> <c:if test="${not empty classBS}">class="${classBS}"</c:if> accept-charset="UTF-8" data-remote="true">
  <c:choose>
    <c:when test ="${not empty method}">
	  <input type="hidden" name="method" value="${method}"  />
    </c:when>
	</c:choose>  
<jsp:doBody/>
</form>

<%-- 

/**
 *	Constructor with url, id and authenticity_token 
 * @param: url: url url for forms  
 * @param: id: user's id 
 * @param: authenticity_token: for invite form only
 * @return: this form accept url with :id in path, then replace :id to url  
 * 
/ 
<t:form_with url="${}" id="${}"> 
 	FORM BODY GOES HERE
</t:form_with>


/**
 *	Constructor with url, id and method 
 * @param: url: url url for forms  
 * @param: id: user's id 
 * @return: this form accept url with :id in path, then replace :id to url  
 * 
/ 
<t:form_with url="${}" id="${}" method="${}"> 
 	FORM BODY GOES HERE
</t:form_with>


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