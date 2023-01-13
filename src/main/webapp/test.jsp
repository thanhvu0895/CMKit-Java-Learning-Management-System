<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags" %>

<t:form_with url="users/">
<div class="form-group">
	<label for="user_First Name:">First name:</label>
	<f:text_field model="user" text_field="first_name" classBS="form-control"></f:text_field>
</div>


<div class="actions">
	<f:submit submitValue="Save" classBS="btn btn-success"></f:submit>
</div>
</t:form_with>