<div class="panel panel-default">
  <div class="panel-heading"><h3>${title}</h3></div>
  <div class="panel-body">
	<p>
	  <strong>Repository URL:</strong>
	  <kbd>${get_repo_url repo}</kbd>
	</p>
	
	<ul class="list-group">
	<%= render 'repos/directory_browser', repo: repo, prepend: ["professor"], title: "Professor Files", edit: edit, return_url: return_url %>

	<%= render 'repos/directory_browser', repo: repo, prepend: ["grader"], title: "Grader Files", edit: edit, return_url: return_url %>

	<%= render 'repos/directory_browser', repo: repo, prepend: ["student"], title: "Student Files", edit: edit, return_url: return_url %>
	</ul>
  </div>
</div>
