<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page import="no.knowit.rfid.TagListener" %>
<%@ page import="no.knowit.couchdb.KaffeHenting" %>
<%@ page import="java.text.SimpleDateFormat" %>
<META HTTP-EQUIV="refresh" CONTENT="1">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>blame it</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<style type="text/css">
			<!--
body {
	margin-left: 30px;
	margin-right: 30px;
	font-size: 35px;
}
-->
</style>
	</head>
	<body background="BG_ALL.jpg">
	<div style="clear:left; height:170px">
	<h1 style="float:left">
		<span style="background-color:#B22222; padding:10px 0 5px 0; color:white; font-family:times; font-size: 50px">&nbsp;&nbsp;blame&nbsp;it&nbsp;&nbsp;</span>
	</h1>
	<h3 style="float:right;">Totalt antall kopper: <%= TagListener.antallKopperTotalt() %></h3><br/>
	</div>
		<table cellpadding="5px" >
			<tr height="0px">
				<th align="left" width="20%"></th>
				<th align="left" width="50%"></th>
				<th width="10%"></th>
				<th width="20%"></th>
			</tr>
			<tr><td colspan="4"><hr/></td></tr>
			<%  SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				for (int i = 0; i < TagListener.getKaffeDrikkere().size(); i++) {
				KaffeHenting kh =  TagListener.getKaffeDrikkere().get(i);
			%>
			<tr>
				<td><img height="90" width="100" src=" <%= kh.getPerson().getPhoto()%>" /></td>
				<td style="font-weight:bold;"><%= kh.getPerson().getName()%> </td>
				<td align="center"><%= format.format(kh.getSisteTidspunkt()) %></td>
				<td align="center"><%= kh.getAntallKaffe() %> kopp<% if (kh.getAntallKaffe() > 1) out.print("er"); %>  </td>
			</tr>
			<% } %>
		</table>
	</body>
</html>




