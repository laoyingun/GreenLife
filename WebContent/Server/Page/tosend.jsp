<%@page
	import="java.util.List,com.greenlife.model.*,com.greenlife.dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>待发货</title>
<link rel="stylesheet" type="text/css" href="../CSS/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../CSS/bootstrap.min1.css" />
<link rel="stylesheet" type="text/css"
	href="../CSS/bootstrap-responsiv.css" />
<link rel="stylesheet" type="text/css"
	href="../CSS/jquery.dataTables.min.css" />



<link rel="stylesheet" type="text/css" href="../CSS/jquery-confirm.css" />


<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/refund.js"></script>
<script src="../js/send.js"></script>
</head>

<body>
	<%
		if (request.getSession().getAttribute("login") == null) {// 用户没有登录
			response.sendRedirect("/Server/Page/login.jsp");
		} else {
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="row">
		<button type="button" onClick="location.href='togroup.jsp'"
			class="btn btn-primary">待成团</button>
		<button type="button" onClick="location.href='topay.jsp'"
			class="btn btn-info">待付款</button>
		<button type="button" onClick="location.href='tosend.jsp'"
			class="btn btn-success">待发货</button>

		<button type="button" onClick="location.href='toreceive.jsp'"
			class="btn btn-warning">待收货</button>
		<button type="button" onClick="location.href='finish.jsp'"
			class="btn btn-danger">已完成</button>
		<button type="button" onClick="location.href='refund.jsp'"
			class="btn btn-primary">已退款</button>
	</div>
	<div class="row">
		<!-- Main -->
		<div class="panel panel-default">
			<div class="panel-heading">
				订单 <span>&gt;</span> <big><strong>待发货</strong></big>
			</div>
			<!-- Content -->
			<div class="panel-body">

				<!-- Table -->
				<table aria-describedby="dataTables-example_info" role="grid"
					class="table table-striped table-bordered table-hover datatable"
					id="group">
					<thead>
						<tr>
							<th style="display: none">序号</th>
							<th>交易时间</th>
							<th>交易号</th>
							<th>微信昵称</th>
							<th>产品名</th>
							<th>数量</th>
							<th>收货人</th>
							<th>收货地址</th>
							<th>电话</th>
							<th></th>
							<th>发货</th>
							<th>取消订单</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<GoodsOrder> GoodsOrderByPerson = GoodsOrderDao.getGoodsOrderListByState(3);//团购订单待发货
								List<GoodsOrder> GoodsOrderByGroup = GoodsOrderDao.getGoodsOrderListByState(12);//个人订单待发货
								String type = "";
								GoodsOrderByGroup.addAll(GoodsOrderByPerson);
								for (int i = 0; i < GoodsOrderByGroup.size(); i++) {
									GoodsOrder oneGoodsOrder = GoodsOrderByGroup.get(i);//被遍历到的商品
									if (oneGoodsOrder.getOrderState() == 3) {
										type = "团购";
									} else
										type = "个人";
						%>
						<tr class="goods">

							<%
								//订单对应的用户信息和商品信息
										UserInfo user = UserInfoDao.getUserInfo(oneGoodsOrder.getWechatId());
										GoodsInfo g = GoodsInfoDao.getGoodsInfo(oneGoodsOrder.getGoodsId());
							%>
							<td style="display: none"><%=GoodsOrderByGroup.size() - i%></td>
							<td><%=oneGoodsOrder.getTradeTime()%></td>
							<td><%=oneGoodsOrder.getPrepayId()%></td>
							<td><%=user.getWechatName()%></td>
							<td><%=g.getGoodsName()%></td>
							<td><%=oneGoodsOrder.getGoodsNum()%></td>
							<td><%=oneGoodsOrder.getReceiverName()%></td>
							<td><%=oneGoodsOrder.getAddrDetail()%></td>
							<td><%=oneGoodsOrder.getPhoneNumber()%></td>
							<td><%=type%></td>
							<td><button id="<%=oneGoodsOrder.getOrderId()%>"
									class="btn btn-info btn-sm send">发货</button></td>
							<td><button class="btn btn-danger refund"
									id="<%=oneGoodsOrder.getOrderId()%>">取消并退款</button></td>
						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>
			</div>
			<!-- Table -->
		</div>
	</div>
	<jsp:include page="footer.html"></jsp:include>
	<script src="../js/head.js"></script>

	<script type="text/javascript" src="../js/jquery-confirm.js"></script>

	<script type="text/javascript" src="../js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="../js/datatable-zn-sort.js"></script>
</body>

</html>