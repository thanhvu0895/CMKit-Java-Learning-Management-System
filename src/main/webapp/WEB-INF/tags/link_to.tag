<%@ tag trimDirectiveWhitespaces="true" language="java"
	pageEncoding="ISO-8859-1"%>
<%@ tag import="codingmentor.javabackend.k3.Utils.UrlUtils"%>
<%@ attribute name="path" required="true"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="classBS" required="false"%>
<%@ attribute name="method" required="false"%>
<%@ attribute name="confirm" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}${path}" /> <!-- ADD CONTEXT PATH TO url URL  -->
<c:set var="path"
	value="${UrlUtils.putIdInPath(pageContext.getAttribute('path'), pageContext.getAttribute('id'))}" />
<!-- If url has :id pattern, it will replace with value of id -->
<c:choose>
	<c:when test="${not empty classBS && not empty method}">
		<form method="post" action="${path}" style="display: inline-block">
			<a href="${path}"><button type="submit" class="${classBS}"><jsp:doBody /></button></a>
		</form>
	</c:when>
	<c:when test="${not empty confirm}">
		<form method="post" action="${path}" onsubmit="return confirm('${confirm}');">
		<input type="hidden" name="method" value="delete"/>
		<a href="${path}"><button type="submit" class="${classBS}">
		<jsp:doBody /></button></a></form>
	</c:when>
	<c:when test="${not empty classBS}">
		<a class="${classBS}" href="${path}"><jsp:doBody /></a>
	</c:when>
	<c:when test="${not empty method}">
		<form method="post" action="${path}" style="display: inline-block">
		<input type="hidden" name="method" value="${method}"/>
		<a href="${path}" rel="nofollow" data-method="${method}"><jsp:doBody /></a>
		</form>
	</c:when>
	<c:otherwise>
		<a href="${path}"><jsp:doBody /></a>
	</c:otherwise>
</c:choose>
<%-- 
/**
 *	Constructor with path and id used at the same time 
 * @param: path  : url that anchor path leads to 
 * @param: id: user's id 
 * @return: this form accept url with :id in path, then replace :id to url  
 * 
/ 
<t:link_to path="${}" id="${}"> 
 	LINK BODY GOES HERE
</t:link_to>

/**
 * Constructor with path only to be used in jsp
 * @param: path (required) -> String that represents path to servlet
 * @return: a anchor link with context path added
 *
/

<t:link_to path="${}">
	LINK BODY GOES HERE  
</t:link_to>
 
 
/**
 * Constructor with path and classBS only
 * @param: path -> String that represents path to servlet
 * @param: classBS
 * @return: a anchor link with context path added
 *
/

<t:link_to path="${}" classBS="">
	LINK BODY GOES HERE  
</t:link_to>
 
 /**
 * Constructor with path, classBS, method, id
 * @param: path (required) -> String that represents path to servlet. A context path will be added automatically, and if :id is presented with link
 * @param: classBS (required) -> String that represents bootstrap class
 * @param: method -> String that contains method that anchor tag is sending to
 * @param: id: user's id 
 * @return: an anchor link with context path added, :id is recognized if exists, class is inserted for styling, method is inserted for link to acts as form
 *
/
<t:link_to path="${}" classBS="${}" method="${}" id="${}">
	LINK BODY GOES HERE  
</t:link_to>
 
/**
 * Constructor with path, classBS, confirm, method
 * @param: path (required) -> String that represents path to servlet. A context path will be added automatically, and if :id is presented with link
 * @param: classBS (required) -> String that represents bootstrap class
 * @param: method -> String that contains method that anchor tag is sending to 
 * @return: an anchor link with context path added, :id is recognized if exists and method is added
 *
/
<t:link_to path="${}" classBS="${}"  method="${}">
	LINK BODY GOES HERE  
</t:link_to>
 
 
 /**
 * Constructor with path, classBS, confirm, id
 * @param: path (required) -> String that represents path to servlet. A context path will be added automatically, and if :id is presented with link
 * @param: classBS (required) -> String that represents bootstrap class
 * @param: confirm -> String that contains method that anchor tag is sending to 
 * @param: id: user's id 
 * @return: an anchor link with context path added, :id if exists is replaced with user:id inside url, and method delete, confirmation message should be added as well.
 *
/
<t:link_to path="${}" classBS="${}" id="${}" confirm="YOUR CONFIRM MESSAGE">
	LINK BODY GOES HERE  
</t:link_to>
 
  /**
 * Constructor with path, classBS, confirm
 * @param: path (required) -> String that represents path to servlet. A context path will be added automatically, and if :id is presented with link
 * @param: classBS (required) -> String that represents bootstrap class
 * @param: confirm -> String that contains method that anchor tag is sending to 
 * @return: an anchor link with context path added, and method delete, confirmation message should be added as well.
 *
/
<t:link_to path="${}" classBS="${}" confirm="YOUR CONFIRM MESSAGE">
	LINK BODY GOES HERE  
</t:link_to>
 --%>