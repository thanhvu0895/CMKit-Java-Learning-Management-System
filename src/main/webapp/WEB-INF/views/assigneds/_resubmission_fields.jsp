<script>
	function showOrHideResubmissions()
	{
	  if(document.getElementById('resubmissionSelect').value!='never_resubmit')
	  {
	    document.getElementById("resub1").style.display='inline';
		showOrHideResubmissionsCount();
	  }
	  else
	  {
		document.getElementById("resub1").style.display='none';
		document.getElementById("resub2").style.display='none';
	  }
	}
	
	function showOrHideResubmissionsCount()
	{
	  if(document.getElementById('limitResubs').checked)
	    document.getElementById("resub2").style.display='inline';
	  else
		document.getElementById("resub2").style.display='none';
	}
</script>

<div class="form-group">
	<label for="assigned_Allow students to:">Allow students to:</label>
	<select class="form-control" onchange="showOrHideResubmissions();" id="resubmissionSelect" name="assigned[allow_resubmissions]">
		<option value="never_resubmit">Never resubmit</option>
		<option value="resubmit_before_deadline">Resubmit before deadline</option>
		<option value="resubmit_before_graded">Resubmit before graded</option>
		<option value="resubmit_even_after_graded">Resubmit even after graded</option>
	</select>
	<c:if test="${assignment.getAssignmentType() == 'student_repo'}">
		<label for="assigned_Note that &quot;resubmitting&quot; a repository assignment actually means &quot;restarting&quot; the assignment with a new repository!">Note that "resubmitting" a repository assignment actually means "restarting" the assignment with a new repository!</label>
	</c:if>
</div>

<div class="form-group" id="resub1">
  <input name="assigned[limit_resubmissions]" type="hidden" value="0">
  <input onchange="showOrHideResubmissionsCount()" id="limitResubs" type="checkbox" value="1" name="assigned[limit_resubmissions]">
  <label for="assigned_Limit Resubmission Count">Limit resubmission count</label>
</div>

<br>

<div class="form-group" id="resub2">
	<label for="assigned_Resubmission Limit (excluding first submission)">Resubmission limit (excluding first submission)</label>
	<input step="1" class="form-control" type="number" name="assigned[resubmission_limit]" id="assigned_resubmission_limit">
</div>

<script>
	showOrHideResubmissionsCount();
	showOrHideResubmissions();
</script>