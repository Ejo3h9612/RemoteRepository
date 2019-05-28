<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/eDM.css" type="text/css" />

<c:set var='debug' value='true' scope='application' />
<table class='menuOuter'>
	<tr height="48px">

		<td>
			<table class='menuInner'>
				<tr>
				<td class='menuData'>
						<div class='menu'>

							<a href="<c:url value='/index.jsp' />"> 首頁</a>


						</div>
					</td>
				
					<td class='menuData'>
						<div class='menu'>

							<a href="<c:url value='/Insertfile/register.jsp' />"> 新增 </a>

						</div>
					</td>
					<td class='menuData'>
						<div class='menu'>

							<a href="<c:url value='/Selectfile/Selectfile.jsp' />"> 查詢 </a>


						</div>
					</td>
					
				</tr>

			</table>
	<tr>
		<td>
			<hr style="color: #f00; background-color: #f00; height: 2px;">
		</td>
	</tr>
</table>