<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="text_field" required="true"%>
<%@ attribute name="classBS" required="true"%>
<%@ attribute name="model" required="false"%>
<%@ attribute name="value" required="required"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
<c:when test="${not empty model}">
	<input class="${classBS}" type="text" value="${value}" name="${model}[${text_field}]" id="${model}_${text_field}"/>
</c:when>
<c:otherwise>
	<input class="${classBS}" type="text" name="${text_field}"  value="${value}">
</c:otherwise>
</c:choose>

<%--
 
/**
 *	Constructor with text_field and classBS required 
 * @param: text_field: String that represents 
 * @param: classBS: String that represents Bootstrap styling 
 * @return: this form accept url with :id in path, then replace :id to url  
 * 
/ 
<t:link_to path="${}" id="${}"> 
 	LINK BODY GOES HERE
</t:link_to>
 --%> 