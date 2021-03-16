
<%@page import="com.octest.banque.bean.TransactionBean"%>
<%@page import="com.octest.banque.util.ServletUtility"%>
<%@page import="com.octest.banque.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Receipt</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Receipt</li>
	</ol>
	</nav>
	
	
<div col-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-5">
					<div class="form-area">
						<form role="form" action="#" method="post" >
							<br style="clear: both">
		
					<h3 style="margin-bottom: 15px; text-align: left: ;">Transaction Receipt</h3>
							
								<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
                	
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
					<% TransactionBean tBean=(TransactionBean)session.getAttribute("tbean"); %>		
							<hr>
							<div class="form-group">
								<label>Transaction Id : </label>
								<h6><%=tBean.getTrasactionId() %></h6>
							</div>
							
							<div class="form-group">
								<label>Transaction Type : </label>
								<h6><%=tBean.getTransactionType()%></h6>
							</div>
							
							<div class="form-group">
								<label>To Account : </label>
								<h6><%=tBean.getToAccountNo()%></h6>
							</div>		
							
							<div class="form-group">
								<label>Transaction Date : </label>
								<h6><%=tBean.getTrasactionDate() %></h6>
							</div>
							
							<div class="form-group">
								<label>Transaction Amount : </label>
								<h6><%=tBean.getTransactionAmount()%></h6>
							</div>
							
							<div class="form-group">
								<label>Description: </label>
								<h6><%=tBean.getDescription()%></h6>
							</div>
													
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>
		<div style="margin-top: 205px">
		<%@ include file="Footer.jsp"%>
		</div>

</body>
</html>