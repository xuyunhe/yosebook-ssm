<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/order/desc.css'/>">
  </head>
  
<body>
	<div class="divOrder">
		<span>订单号：${order.oid }
        <span class="orderTime"></span>
				
<!-- 
				(准备发货)
				(等待确认)
				(交易成功)
				(已取消)
 -->
		　 下单时间：<span class="orderTime">${order.ordertime }</span>
		</span>
	</div>
	<div class="divRow">
		<div class="divContent">
			<dl>
				<dt>收货人信息</dt>
				<dd>${order.address }</dd>
			</dl>
		</div>
		<div class="divContent">
			<dl>
				<dt>商品清单</dt>
				<dd>
					<table cellpadding="0" cellspacing="0">
						<tr style="border-radius: 3px;">
							<th class="tt">商品名称</th>
							<th class="tt" align="left">单价</th>
							<th class="tt" align="left">数量</th>
							<th class="tt" align="left">小计</th>
						</tr>
                  <c:forEach items="${order.orderItemList }" var="orderItem">
						<tr style="padding-top: 20px; padding-bottom: 20px;">
							<td class="td" width="400px">
								<div class="bookname">
								  <img align="middle" width="70" src="<c:url value='/${orderItem.book.image_b }'/>"/>
								  ${orderItem.book.bname }
								</div>
							</td>
							<td class="td" >
								<span>&yen;${orderItem.book.currPrice }</span>
							</td>
							<td class="td">
								<span>${orderItem.quantity }</span>
							</td>
							<td class="td">
								<span>&yen;${orderItem.subtotal }</span>
							</td>			
						</tr>
                 </c:forEach>
																			
					</table>
				</dd>
			</dl>
		</div>
		<div class="divBtn">
			<span class="spanTotal">合　　计：</span>
			<span class="price_t">&yen;${order.total }</span><br/>
<c:if test="${order.status eq 2 and btn eq 'deliver'}">
	<a id="deliver" href="/yosebook-ssm/admin/order/deliver.do?oid=${order.oid }">发　　货</a>
</c:if>
<c:if test="${order.status eq 1 and btn eq 'cancel'}">
	<a id="cancel" href="/yosebook-ssm/admin/order/cancel.do?oid=${order.oid }">取　　消</a>
</c:if>
		</div>
	</div>
</body>
</html>

