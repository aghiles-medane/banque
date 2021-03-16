<%@page import="com.octest.banque.controller.BeneficiaryCtl"%>
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
<title>Transaction</title>
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

		<li class="breadcrumb-item active" aria-current="page">Transaction</li>
	</ol>
	</nav>

	<divcol-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-8">
					<div class="form-area">
						<form role="form" action="<%=BSView.ADMIN_TRANSACTION_CTL%>"
							method="post">

							<jsp:useBean id="bean" class="com.octest.banque.bean.TransactionBean"
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
							<h3 style="margin-bottom: 15px; text-align: left:;">Make Transaction</h3>
							<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
							</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
							</font></b>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="accNo"
									placeholder="Account No"
									value="<%=(bean.getFromAccountNo()>0)?bean.getFromAccountNo():""%>">
								<font color="red"><%=ServletUtility.getErrorMessage("accNo", request)%></font>
							</div>
							
							<% 
								HashMap<String,String> map=new HashMap<String,String>();
								map.put("Credit","Credit");
								map.put("Debit","Debit");
							%>
							
							<div class="form-group">
								<%=HTMLUtility.getList("tType",String.valueOf(bean.getTransactionType()), map)%>
								<font color="red"><%=ServletUtility.getErrorMessage("tType", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="tAmount"
									placeholder="Amount"
									value="<%=(bean.getTransactionAmount()>0.0)?bean.getTransactionAmount():""%>">
								<font color="red"><%=ServletUtility.getErrorMessage("tAmount", request)%></font>
							</div>
							
							<div class="form-group">
									<textarea rows="4" cols="5" name="description" class="form-control" placeholder="Enter Description" ><%=DataUtility.getStringData(bean.getDescription())%></textarea>
								<font color="red"><%=ServletUtility.getErrorMessage("description", request)%></font>
							</div>
							
							<input type="submit" name="operation"
								class="btn btn-primary pull-right"
								value="<%=BeneficiaryCtl.OP_SAVE%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right"
								value="<%=BeneficiaryCtl.OP_RESET%>">
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