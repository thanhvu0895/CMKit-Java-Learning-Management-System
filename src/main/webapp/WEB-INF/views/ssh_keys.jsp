<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
  <head>
    <title>Add SSH Keys</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ssh.css" />
    <%@include file="components/css-js.jsp"%>
  </head>
  <body>
    <nav class="navbar bg-light">
      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="" />
          </div>
          <a href="student_home.html" class="navbar-brand">My Classes</a>
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
              <a class="dropdown-item" href="#">
                <span><i class="fa-solid fa-lock"></i></span>
                SSH Keys
              </a>
              <hr />
              <button class="dropdown-item" href="#">
                <span><i class="fa-solid fa-right-to-bracket"></i></span>
                Logout
              </button>
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
