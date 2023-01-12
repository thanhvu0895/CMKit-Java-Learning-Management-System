<%@page 
	language="java" pageEncoding="UTF-8" 
	import="codingmentor.javabackend.k3.Utils.UrlUtils" 
%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layoutj pageTitle="Create Account">

<h1> Kit Account Creation </h1>

Welcome to Kit! To create your account, please fill in the following and click "Submit".

<form action="/users/4/accept_invite" accept-charset="UTF-8" data-remote="true" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="authenticity_token" value="yBLprUHA28malE/GsWm9zy+PoYAbTpZ0JzwLR/B7p0t3BrPXWnrt7W27/eWNYg+FBNENMV086hcAd2qDUhnZ3g==" />

  <div class="form-group">
    <input value="c50de68e788cd390c6514d1404f74a78a2bcc36745c509123b1f743d0e82186f" class="form-control" type="hidden" name="user[token]" id="user_token" />
  </div>
  
  <div class="form-group">
    <label for="user_First Name (as registered)">First name (as registered)</label>
    <input class="form-control" type="text" name="user[first_name]" id="user_first_name" />
  </div>
  
  <div class="form-group">
    <label for="user_Last Name">Last name</label>
    <input class="form-control" type="text" name="user[last_name]" id="user_last_name" />
  </div>
  
  <div class="form-group">
    <label for="user_Preferred Name/Nickname (optional- for those who do not go by their full first name)">Preferred name/nickname (optional- for those who do not go by their full first name)</label>
    <input class="form-control" type="text" name="user[preferred_name]" id="user_preferred_name" />
  </div>
  
  <div class="form-group">
    <label for="user_Password">Password</label>
    <input class="form-control" type="password" name="user[password]" id="user_password" />
  </div>
  
  <div class="form-group">
    <label for="user_Confirm Password">Confirm password</label>
    <input class="form-control" type="password" name="user[password_confirmation]" id="user_password_confirmation" />
  </div>
  
  <input type="submit" name="commit" value="Create Account" class="btn btn-success" data-disable-with="Create Account" />
</form>
</t:layoutj>