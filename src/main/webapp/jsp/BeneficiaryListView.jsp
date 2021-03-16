<%@page import="com.octest.banque.model.BeneficiaryModel"%>
<%@page import="com.octest.banque.bean.BeneficiaryBean"%>
<%@page import="com.octest.banque.controller.BeneficiaryListCtl"%>
<%@page import="com.octest.banque.bean.AccountBean"%>
<%@page import="com.octest.banque.controller.AccountListCtl"%>
<%@page import="com.octest.banque.model.CustomerModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.octest.banque.bean.CustomerBean"%>
<%@page import="com.octest.banque.controller.CustomerListCtl"%>
<%@page import="com.octest.banque.controller.CustomerCtl"%>
<%@page import="com.octest.banque.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beneficiary List</title>
<script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>
<%@ include file="Header.jsp"%>
<br>
 	<nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Beneficiary Account List</li>
	</ol>
	</nav>
<form action="<%=BSView.BENEFICIARY_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">
							<h3 style="margin-bottom: 15px; text-align: left;">Beneficiary Account List</h3>
							<div class="form-row">
							<div class="form-group col-lg-4">
								<input type="text" class="form-control"  name="accNo"
									placeholder="Beneficiary Account No" value="<%=ServletUtility.getParameter("accNo", request)%>" > 
							</div>
        					
							<div class="form-group col-lg-4">
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=BeneficiaryListCtl.OP_SEARCH%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=BeneficiaryListCtl.OP_RESET%>">
							</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	<center>
		<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
	</center>
		
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">S.No</th>
				<th scope="col">Account No</th>
				<th scope="col">Name</th>
				<th scope="col">Bank Name</th>
				<th scope="col">IFSC Code</th>
				<th scope="col">Pay Amount</th>
 				<th scope="col">Edit</th>
			</tr>
		</thead>
		<tbody>
				<%
				
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int size = ServletUtility.getSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					BeneficiaryBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<BeneficiaryBean> it = list.iterator();
					while (it.hasNext()) {
						bean = it.next();
				%>
			<tr>
				
				<td><%=index++%></td>
				<td><%=bean.getAcc_No()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getBankName()%></td>
				<td><%=bean.getIFSCCode()%></td>
				<td>
					<a class="btn btn-primary pull-right"  href="TransactionCtl?acN=<%=bean.getAcc_No()%>">Pay</a>
				</td>
				<td>
					<a class="btn btn-primary pull-right"  href="BeneficiaryCtl?id=<%=bean.getId()%>">Edit</a>
				</td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<hr>
	<table width="99%" style=" bottom: 45px">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=BeneficiaryListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
						
					
					<td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=BeneficiaryListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || size==pageNo*pageSize) ? "disabled" : ""%>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
<br>
	<%@ include file="Footer.jsp"%>
</body>
</html>