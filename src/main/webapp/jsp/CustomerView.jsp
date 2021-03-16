
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

		<li class="breadcrumb-item active" aria-current="page">Customer
			Form</li>
	</ol>
	</nav>

	<divcol-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-8">
					<div class="form-area">
						<form role="form" action="<%=BSView.CUSTOMER_CTL%>"
							method="post">

							<jsp:useBean id="bean" class="com.octest.banque.bean.CustomerBean"
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
							<h3 style="margin-bottom: 15px; text-align: left:;">Customer Form</h3>
							<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
							</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
							</font></b>
							<%if(bean.getAcc_No()>0){ %>
							<div class="form-group">
								<input type="text" class="form-control" readonly="readonly" name="accNo"
									placeholder="Account No"
									value="<%=DataUtility.getStringData(bean.getAcc_No())%>">
								<font color="red"><%=ServletUtility.getErrorMessage("accNo", request)%></font>
							</div>
							<%} %>
							<div class="form-group">
								<input type="text" class="form-control" name="name"
									placeholder="Name"
									value="<%=DataUtility.getStringData(bean.getName())%>">
								<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control" name="email"
									placeholder="Email Id"
									value="<%=DataUtility.getStringData(bean.getEmailId())%>">
								<font color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control" name="mobile"
									placeholder="Enter Mobile No"
									value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
								<font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
							</div>
							
							<div class="form-group">
									<textarea rows="4" cols="5" name="address" class="form-control" placeholder="Enter Address" ><%=DataUtility.getStringData(bean.getAddress())%></textarea>
								<font color="red"><%=ServletUtility.getErrorMessage("address", request)%></font>
							</div>

							<input type="submit" name="operation"
								class="btn btn-primary pull-right"
								value="<%=CustomerCtl.OP_SAVE%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right"
								value="<%=CustomerCtl.OP_RESET%>">
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