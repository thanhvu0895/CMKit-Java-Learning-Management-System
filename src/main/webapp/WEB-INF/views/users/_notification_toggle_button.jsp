<c:choose>
	<c:when test=${property==true}>
	  <t:link_to path="${UrlUtils.TOGGLE_ASSIGNED_NOTIFICATION_PATH}" classBS="btn btn-success"  method="post">ON <span class="glyphicon glyphicon-volume-up"></span></t:link_to>
	</c:when>
	<c:otherwise>
	  <t:link_to path="${UrlUtils.TOGGLE_ASSIGNED_NOTIFICATION_PATH}" classBS="btn btn-danger"  method="post">OFF <span class="glyphicon glyphicon-volume-off"></span></t:link_to>
	</c:otherwise>
</c:choose>
