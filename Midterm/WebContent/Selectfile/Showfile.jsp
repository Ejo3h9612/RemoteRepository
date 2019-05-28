<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <jsp:useBean id="memberService" class="ch05_09.MemberService" /> --%>
<c:set var="subTitle" value="查詢會員資料(Lab07_02)" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <c:if test="${empty LoginOK}">  --%>
<%--    <c:set var="target" value="${pageContext.request.servletPath}" scope="session" /> --%>
<%--    <c:redirect url="/ch06_01/login.jsp" /> --%>
<%-- </c:if> 	 --%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/fragment/top.jsp" />
<meta charset="UTF-8">
<title>查詢</title>
<script language="javascript" type="text/javascript">
	function initInput() {

		var variable = document.getElementById("serialnumberid").textContent;
		document.getElementById("serialnumber").value = variable;
		document.getElementById("serialnumber2").value = variable;
	}
</script>
</head>
<body onLoad="initInput()">

	<c:choose>
		<c:when test="${empty contextMemberBean}">
   			目前尚未有任何資料<br>
		</c:when>
		<c:otherwise>
                               會員資料如下：<br>
			<table border='1'>
				<tr>
					<th width='120'>編號</th>
					<th width='120'>縣市</th>
					<th width='140'>職業類別</th>
					<th width='360'>受傷原因</th>
					<th width='130'>受傷類別</th>
					<th width='150'>日期</th>
					<th width='130'>死亡數</th>
					<th width='130'>受傷數</th>
				</tr>
				<c:forEach var="aBean" items="${contextMemberBean}">
					<tr>
						<td id="serialnumberid">${aBean.serialnumber}</td>
						<td>${aBean.county}</td>
						<td>${aBean.industry}</td>
						<td>${aBean.intermedia}</td>
						<td>${aBean.disaster}</td>
						<td>${aBean.date}</td>
						<td>${aBean.dead}</td>
						<td>${aBean.hurt}</td>
					</tr>
				</c:forEach>
			</table>

		</c:otherwise>
	</c:choose>
	<br>

	<div style="width:1800px;">
		<div style="float:left;">
			<form Action="<c:url value='Deletefile.do' />" method="POST">
				<table>
					<tr>
						<td width='180' align="LEFT"><input type="hidden"
							name="serialnumber" id="serialnumber" size="10"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="刪除"></td>
					</tr>

				</Table>
			</form>
		</div>
		<div style="float:left;">
			<form Action="<c:url value='idtomodify.do'/>" method="POST">
				<table>
					<tr>
						<td width='180' align="LEFT"><input type="hidden"
							name="serialnumber2" id="serialnumber2" size="10"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="修改"></td>
					</tr>

				</Table>
			</form>
		</div>
	</div>

</body>
</html>