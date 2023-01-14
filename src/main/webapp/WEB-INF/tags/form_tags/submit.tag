<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="ISO-8859-1"%>
<%@ attribute name="classBS" required="true"%>
<%@ attribute name="submitValue" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="submit" name="commit" value="${submitValue}" class="${classBS}" data-disable-with="${submitValue}" />
<%--
**
 *	Constructor with submitValue and classBS used at the same time 
 * @param: submitValue  : String that represents submit message send to payload 
 * @param: classBS: Class to receive bootstrap 
 * @return: This forms returns a submit button with 
 * 			payload variable 'commit' carrying same value 
 *			with submit_value    
 * 
/ 
<f:submit submitValue="${}" classBS="${}"></f:submit>
--%>