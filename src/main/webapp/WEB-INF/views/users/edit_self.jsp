<%@page 
	language="java" pageEncoding="UTF-8" 
	import="codingmentor.javabackend.k3.Utils.UrlUtils" 
%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags" %>

<t:layoutj pageTitle="Settings">
	<t:form_with url="${UrlUtils.USER_EDIT_SELF_PATH}" id="${current_user.id}" method="update_preferred_name"> 
	  <div class="form-group">
	  	<f:label model="user" label="Preferred name / nickname (optional):"></f:label>
	  	<f:text_field model="user" value="${current_user.preferred_name}" text_field="preferred_name" classBS="form-control"></f:text_field>
	  </div>
	  <div class="actions">
	  	<f:submit submitValue="Save" classBS="btn btn-success"></f:submit>
	  </div>
	</t:form_with><br><br><br>
	<t:link_to text="Change Password" path="${UrlUtils.CHANGE_PASSWORD_PATH}" classBS="btn btn-primary" ></t:link_to>
</t:layoutj>