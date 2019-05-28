<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/fragment/top.jsp" />
<meta charset="UTF-8">
<title>查詢</title>
<style>
form {
	margin: 0 auto;
	width: 400px;
}
</style>

</head>
<body>

<!-- <form Action="login.do" method="POST"> -->
<form Action="<c:url value='selectfile.do' />" method="POST">
    <table>
         <tr><th colspan='2'>
            <h1>查詢案件</h1> 
         </th><th></th></tr>
         <tr>
             <td width='180' align="RIGHT">輸入案件編號：</td>
             <td width='180' align="LEFT">
             	<input	type="text" name="serialnumber" size="10" >
             </td>
             <td width='120'><small>
             	<font color='red' size="-1">${ErrorMsgKey.AccountEmptyError}</font></small>
             </td>
         </tr>      
         <tr>
             <td align="CENTER" colspan='3'>
             	<font color='red' size="-1">${ErrorMsgKey.FileError}&nbsp;</font>
             </td>
         </tr>
        <tr>
            <td colspan="2" align="center">      
            	<input type="submit" value="查詢"> </td>
            </tr>
         </Table>        
</form>
</body>

</html>
