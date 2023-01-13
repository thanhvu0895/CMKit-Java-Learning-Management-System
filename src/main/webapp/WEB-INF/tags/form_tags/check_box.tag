<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="model" required="false"%>
<%@ attribute name="check_box" required="false"%>
<%@ attribute name="value" required="required"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${not empty model}">
		<c:choose>
			<c:when test="${value}">
				<input name="${model}[${check_box}]" type="hidden" value="0" /> <%-- SENDING TO database --%>
				<input type="checkbox" value="1" checked="checked" name="${model}[${check_box}]" id="${model}_${check_box}" />
			</c:when>
			<c:otherwise>	
				<input name="${model}[${check_box}]" type="hidden" value="0" />
				<input type="checkbox" value="1" name="${model}[${check_box}]" id="${model}_${check_box}" />
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
			<c:when test="${value}">
				<input name="${check_box}" type="hidden" value="0" />
				<input type="checkbox" value="1" checked="checked" name="${check_box}" id="${check_box}" />
			</c:when>
			<c:otherwise>	
				<input name="${check_box}" type="hidden" value="0" />
				<input type="checkbox" value="1" name="${check_box}" id="${check_box}" />
			</c:otherwise>
	</c:otherwise>
</c:choose>

