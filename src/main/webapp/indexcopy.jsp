<%@page 
	language="java" pageEncoding="UTF-8"
	import="codingmentor.javabackend.k3.Utils.UrlUtils" 
%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layoutj pageTitle="Users">
<h1>User Management</h1>


<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#all_users"><strong>All Users<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="all_users" class="panel-collapse collapse">
	  <div class="panel-body">
		<table class="table table-hover" data-toggle="table" data-classes="table-no-bordered" data-sort-name="Email" data-sort-order="asc" data-search="true">
		  <thead>
			<tr>
			  <th data-sortable="true" data-field="Email">Email</th>
			  <th data-sortable="true">Admin</th>
			  <th data-sortable="true">Status</th>
			  <th data-sortable="true">Name</th>
			  <th></th><th></th>
			</tr>
		  </thead>

		  <tbody>
			  <tr>
				<td>thanhvu0895@gmail.com</td>
				<td>
					<span class="glyphicon glyphicon-wrench" title="Admin" aria-label="Admin"></span>
				</td>
				<td>
				</td>
				<td>
				  Thanh (Vu Thanh ) Vu
				</td>
				<td><a class="btn btn-primary" href="/users/1/edit_admin">Edit</a></td>
				<td><a data-confirm="Are you sure you want to delete this user?" class="btn btn-danger" rel="nofollow" data-method="delete" href="/users/1">Delete</a></td>
			  </tr>
			  <tr>
				<td>test01@gmail.com</td>
				<td>
					<span class="glyphicon glyphicon-wrench" title="Admin" aria-label="Admin"></span>
				</td>
				<td>
				</td>
				<td>
				  Thanh Vu
				</td>
				<td><a class="btn btn-primary" href="/users/2/edit_admin">Edit</a></td>
				<td><a data-confirm="Are you sure you want to delete this user?" class="btn btn-danger" rel="nofollow" data-method="delete" href="/users/2">Delete</a></td>
			  </tr>
			  <tr>
				<td>test@123</td>
				<td>
					<span class="glyphicon glyphicon-wrench" title="Admin" aria-label="Admin"></span>
				</td>
				<td>
				</td>
				<td>
				  John Doe
				</td>
				<td><a class="btn btn-primary" href="/users/3/edit_admin">Edit</a></td>
				<td><a data-confirm="Are you sure you want to delete this user?" class="btn btn-danger" rel="nofollow" data-method="delete" href="/users/3">Delete</a></td>
			  </tr>
		  </tbody>
		</table>
	  </div>
	</div>
  </div>
</div>

<br>

<button data-toggle="collapse" data-target="#new_user" class="btn btn-primary">Invite User</button>

<div id="new_user" class="collapse">
	<div class="panel panel-default">
	<div class="panel-body">
	<form action="/users/create" accept-charset="UTF-8" data-remote="true" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="authenticity_token" value="O6mKLxVXFlexPbTNzliVI/QiHRz1D/sJBH1t0ruDHBVb/2iUajNHAgO1HJ1I9nxNL7sq4xDhDvlmZxutKQc2dA==" />
	
		<div class="form-group">
			<label for="Email">Email</label>
			<input class="form-control" type="email" name="email" id="email" />
		</div>

		<div class="form-group">
		  <input name="admin" type="hidden" value="0" /><input type="checkbox" value="1" name="admin" id="admin" />
		  <label for="Admin">Admin</label>
		</div>
			
		
		<div class="actions">
			<input type="submit" name="commit" value="Invite" class="btn btn-success" data-disable-with="Invite" />
		</div>
</form>	</div>
	</div>
</div>


</t:layoutj>
