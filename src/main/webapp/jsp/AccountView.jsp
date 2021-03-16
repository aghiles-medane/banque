
<%@page import="com.octest.banque.controller.AccountCtl"%>
<%@page import="com.octest.banque.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.octest.banque.controller.CustomerCtl"%>
<%@page import="com.octest.banque.controller.UserRegistrationCtl"%>
<%@page import="com.octest.banque.util.ServletUtility"%>
<%@page import="com.octest.banque.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="http://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
</script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<br>
	<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">

		<li class="breadcrumb-item active" aria-current="page">Add Account</li>
	</ol>
	</nav>

	<divcol-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-8">
					<div class="form-area">
						<form role="form" action="<%=BSView.ACCOUNT_CTL%>"
							method="post">

							<jsp:useBean id="bean" class="com.octest.banque.bean.AccountBean"
								scope="request"></jsp:useBean>

							<input type="hidden" name="id" value="<%=bean.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=bean.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=bean.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

							<br style="clear: both">
							<h3 style="margin-bottom: 15px; text-align: left:;">Add Account</h3>
							<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
							</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
							</font></b>
							
							<div class="form-group">
								<input type="text" class="form-control" readonly="readonly" name="accNo"
									placeholder="Account No"
									value="<%=DataUtility.getStringData(bean.getAcc_No())%>">
								<font color="red"><%=ServletUtility.getErrorMessage("accNo", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control" name="balance"
									placeholder="Enter Account Balance"
									value="<%=(bean.getBalance()>0.0)?bean.getBalance():""%>">
								<font color="red"><%=ServletUtility.getErrorMessage("balance", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control" name="dfLimit"
									placeholder="Enter Over Draft Limit"
									value="<%=(bean.getOverDraftLimit()>0.0)?bean.getOverDraftLimit():""%>">
								<font color="red"><%=ServletUtility.getErrorMessage("dfLimit", request)%></font>
							</div>
							
							<% 
								HashMap<String,String> map=new HashMap<String,String>();
								map.put("Saving Account","Saving Account");
								map.put("Current Account","Current Account");
							%>
							
							<div class="form-group">
								<%=HTMLUtility.getList("accType",String.valueOf(bean.getAccType()), map) %>
								<font color="red"><%=ServletUtility.getErrorMessage("accType", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control" name="iRate"
									placeholder="Enter Intrest Rate"
									value="<%=(bean.getIntrestRate()>0.0)?bean.getIntrestRate():""%>">
								<font color="red"><%=ServletUtility.getErrorMessage("iRate", request)%></font>
							</div>
							
							

							<input type="submit" name="operation"
								class="btn btn-primary pull-right"
								value="<%=AccountCtl.OP_SAVE%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right"
								value="<%=AccountCtl.OP_RESET%>">
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>

		<%@ include file="Footer.jsp"%>
</body>
</html>