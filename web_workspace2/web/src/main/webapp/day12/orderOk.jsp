<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ono = request.getParameter("orderno");
	String price = request.getParameter("amount");
	String cardConfirmno = request.getParameter("cardConfirmno");
	String uniqueid = request.getParameter("uniqueid");
	
	if (ono != null) {
		// int orderNo = Integer.parseInt(ono);
		int price2 =  Integer.parseInt(price);
		// PaymentDAO pdao = new PaymentDAO();
		// pdao.addOne(orderNo, price2, cardConfirmno, uniqueid);
	}
	
	String msg = request.getParameter("msg");
	out.println(msg);
	
	
%>