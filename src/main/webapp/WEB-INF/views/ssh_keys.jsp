<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="en">
  <head>
    <title>Add SSH Keys</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ssh.css" />
    <link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
  crossorigin="anonymous"
/>
<script src="https://kit.fontawesome.com/2912a97a77.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script
  src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
  integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
  crossorigin="anonymous"
></script>
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
  integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
  crossorigin="anonymous"
></script>
  </head>
  <body>
     <nav class="navbar bg-light">
      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="logo">
          <a href="${pageContext.request.contextPath}/" class="navbar-brand">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="" />
           </a>
          </div>
          <a href="${pageContext.request.contextPath}/" class="navbar-brand">My Classes</a>
        </div>
        <div>
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">User</button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#">
                <span><i class="fa-solid fa-gear"></i></span>
                Account Settings
              </a>
              <a class="dropdown-item" href="#">
                <span><i class="fa-solid fa-bell"></i></span>
                Notification Settings
              </a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/ssh_keys">
                <span><i class="fa-solid fa-lock"></i></span>
                SSH Keys
              </a>
              <hr />
              <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">
                <span><i class="fa-solid fa-right-to-bracket"></i></span>
                Logout
              </a>
            </div>
          </div>
        </div>
      </div>
    </nav>

    <div class="container">
      <h1>Add Ssh Key</h1>

      <form action="" accept-charset="UTF-8" method="post">
        <input name="utf8" type="hidden" value="✓" /><input type="hidden" name="authenticity_token" value="d/dKj6aJ4e3kyXbthENGr8QiVqq/kX3imsn4cqw/dT2BLQKpftOxaHdykie20f9m9cZJyarCuFe973JvfMqKrQ==" />

        <div class="form-group">
          <label for="ssh_key_Label (to help you remember which computer the key is from)">Label (to help you remember which computer the key is from)</label>
          <input class="form-control" type="text" name="ssh_key[label]" id="ssh_key_label" />
        </div>

        <div class="form-group">
          <label for="ssh_key_Paste Key Here:">Paste key here:</label>
          <input class="form-control" type="text" name="ssh_key[key]" id="ssh_key_key" />
        </div>

        <div class="actions">
          <input type="submit" name="commit" value="Add Key" class="btn btn-success" data-disable-with="Add Key" />
        </div>
      </form>
    </div>
  </body>
</html>
