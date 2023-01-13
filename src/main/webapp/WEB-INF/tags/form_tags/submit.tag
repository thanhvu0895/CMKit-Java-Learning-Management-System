<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="ISO-8859-1"%>
<%@ attribute name="classBS" required="true"%>
<%@ attribute name="submitValue" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="submit" name="commit" value="${submitValue}" class="${classBS}" data-disable-with="${submitValue}" />
<%-- 
<f:submit submitValue="${}" classBS="${}"></f:submit>
--%>