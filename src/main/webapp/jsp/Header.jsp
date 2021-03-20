
<%@page import="com.octest.banque.controller.LoginCtl"%>
<%@page import="com.octest.banque.controller.BSView"%>
<%@page import="com.octest.banque.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- permet d'utilisez toutes les fonctionnalités ou classes  de bootstrap -->
<link rel="stylesheet" href="<%=BSView.APP_CONTEXT%>/css/bootstrap.min.css" >
<script src="<%=BSView.APP_CONTEXT%>/js/jquery-3.3.1.slim.min.js" ></script>
<script src="<%=BSView.APP_CONTEXT%>/js/popper.min.js" ></script>
<script src="<%=BSView.APP_CONTEXT%>/js/bootstrap.min.js" ></script>

</head>
<body>
<!-- creation d'une session http pour le visiteur -->
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
  <a class="navbar-brand" href="#">Welcome</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    
   		<li class="nav-item active">
        <a class="nav-link" href="<%=BSView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
      	</li>
      	<%if(userLoggedIn){%>
      	<%if(userBean.getRoleId()==1){ %>
      
      		
      <li class="nav-item">
        <a class="nav-link" href="<%=BSView.CUSTOMER_LIST_CTL%>">Customer List</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="<%=BSView.ACCOUNT_LIST_CTL%>">Account List</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=BSView. MY_PROFILE_CTL %>">MyProfile</a>
      </li>
      
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Transaction
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BSView.ADMIN_TRANSACTION_CTL%>">Make Transaction</a>
          <a class="dropdown-item" href="<%=BSView.TRANSACTION_LIST_CTL%>">Consult History</a>
        </div>
      </li>
      
     
      
      <%}else if(userBean.getRoleId()==2){%>
       <li class="nav-item">
        
       <a class="nav-link" href="<%=BSView.CUSTOMER_CTL%>">Customer Form</a>
         
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Add Beneficiary Account
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BSView.BENEFICIARY_CTL%>">Add Beneficiary</a>
          <a class="dropdown-item" href="<%=BSView.BENEFICIARY_LIST_CTL%>">Beneficiary List</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=BSView.TRANSACTION_LIST_CTL%>">Consult History</a>
         
      </li>
      <li class="nav-item">
      <a class="nav-link" href="<%=BSView. MY_PROFILE_CTL %>">MyProfile</a>
            </li>
      <%} %>
      <%} %>
    </ul>
    <form class="form-inline my-2 my-lg-0">
     <ul class="navbar-nav mr-auto">
     <%if (userLoggedIn){%>
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BSView.CHANGE_PASSWORD_CTL%>">Change Password</a>
          <a class="dropdown-item" href="<%=BSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
        </div>
      </li>
      <%}else { %>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=BSView.LOGIN_CTL%>">SignIn</a>
          <a class="dropdown-item" href="<%=BSView.USER_REGISTRATION_CTL%>">SignUp</a>
          
        </div>
      </li>
      <%} %>
     </ul>
      
    </form>
  </div>
</nav>
</body>
</html>