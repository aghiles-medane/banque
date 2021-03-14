<%@page import="com.octest.banque.controller.LoginCtl"%>
<%@page import="com.octest.banque.controller.BSView"%>
<%@page import="com.octest.banque.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="<%=BSView.APP_CONTEXT%>/js/jquery-3.3.1.slim.min.js" ></script>
<script src="<%=BSView.APP_CONTEXT%>/js/popper.min.js" ></script>
<script src="<%=BSView.APP_CONTEXT%>/js/bootstrap.min.js" ></script>

</head>
<body>
   <!-- session pour les user pour permettre de retenir les information   -->
 <%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getLogin() ;
    } else {
        welcomeMsg += "Guest";
    }

%>

<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #e3f2fd;">
  <a class="navbar-brand" href="#">Banking System</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    
   	
</nav>
</body>
</html>