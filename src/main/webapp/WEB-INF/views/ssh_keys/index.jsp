<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>

<t:layoutj pageTitle="Log In">
	<h1>SSH Keys</h1>
		<p> 
		 	SSH keys are used to authenticate your connection when working with git repositories. Once you enter your public key here, you will be able to use that command line to run git commands that connect to the Kit system.<br>
			A link to a help page will go here once we have added help pages. In the meantime, there are plenty of tutorials on generating SSH keys online. GitHub in particular has excellent tutorials for this because it does almost exactly what Kit does with SSH keys.
		</p>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>Key</th>
				<th></th>
			</tr>
 		</thead>
		<tbody>
			<tr>
				<td>Week 1 Key
				<td><a data-confirm="Are you sure?" class="btn btn-danger" rel="nofollow" data-method="delete" href="/ssh_keys/112">Remove</a></td>
			</tr>
			<tr>
				<td>Lenovo Laptop
				<td><a data-confirm="Are you sure?" class="btn btn-danger" rel="nofollow" data-method="delete" href="/ssh_keys/384">Remove</a></td>
			</tr>
			<tr>
				<td>Lenovo
				<td><a data-confirm="Are you sure?" class="btn btn-danger" rel="nofollow" data-method="delete" href="/ssh_keys/410">Remove</a></td>
			</tr>
		</tbody>
	</table>
<br>
<a class="btn btn-primary" href="/ssh_keys/new">Add SSH Key</a>
</t:layoutj>