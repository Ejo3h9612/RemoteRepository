<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<title>修改</title>
<script language="javascript" type="text/javascript">
	function initInput() {

		var variable = document.getElementById("serialnumberid").textContent;
		document.getElementById("serialnumber").value = variable;
		document.getElementById("serialnumber2").value = variable;
	}
</script>
<style>
<!--
body {
	background-attachment: fixed;
	background-color: #EBFFEB;
	background-repeat: no-repeat;
	background-position: 20px 50px;
}
.myBorder {
	color:#FFFF99;
	border: thin dotted #FFFFFF;
}
h1 {
	font-family: "標楷體", "新細明體", sans-serif;
	font-size: 24px;
}
.formBkgnd {
	color: #FFFFFF;
	background-color: #666666;
}
label {
	float:left;
	width:8em;
	font-weight:bold;
	color:#000000;
	margin-top:10px;
	margin-bottom:2px;
	margin-right:10px;
	text-align: right;
}

br {
	clear:both;
}
.fieldWidth {
    margin-top:10px;
	margin-bottom: 2px;
	width: 200px;
	background:#F6E497;
	font-size:1.1em;
}
/* 設定字體大小 */
.fontSize {
	font-size:1.1em;
}

#main {
    position:relative;
	left:70px;
	width:600px;
	height:543px;	
	top: 0px;
	z-index:2;
	font-size:0.9em; 
}
/* 主要內容的區塊 */
#content {
  width: 700px ;
  margin-left: auto ;
  margin-right: auto ;
}
/* 設定傳送鈕的樣式 */
#submit {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#FFFFFF;
	margin-right:1.5em;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#A9A9A9;
}
/* 設定取消鈕的樣式 */
#cancel {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#ffffff;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#a9a9a9;
}

#errorMsg {
    position:relative;
    top:0px; 
    left:0px;    
	color:#FF0000;
	font-size:0.8em;
}
-->
</style>

</head>
<body onLoad="initInput()">
<c:choose>
		<c:when test="${empty contextMemberBean}">
   			目前尚未有任何資料<br>
		</c:when>
		<c:otherwise>
		
  <div id="content"> 
  <Table style="background-color: #E7CDFF; cellspacing:0; border:2px solid black; " >
     <TR height="60" >
         <TD>
         <TABLE style="cellspacing:1; " >
         <TR>
             <TD width="770" colspan='3' align="center" >
                 <Font color="#006600" size='5' face="標楷體">${AppName}</Font>
             </TD>
         </TR>
         <TR>
             <TD width="270" ></TD>
             <TD width="230"  align="center">
                 <Font color="#006600" size='4' face="標楷體">職業災禍登記修改</Font>
             </TD>
            
         <!-- 此區塊顯示程式執行後的訊息 -->
             <TD width="270" align="left"><font size="-1" color="#FF0000">
                 ${MsgMap.InsertNG}${MsgMap.errorSaveData}</font>
             </TD>
         </TR>
         <TR>          
          <TD width="770" colspan='3'  align="center">
                 <Font color="red" size='2' face="標楷體">${MsgMap.passwordError}</Font>
             </TD>
          </TR>                    
         </TABLE>
       
     <TR><TD colspan="3">
     <c:forEach var="aBean" items="${contextMemberBean}">
  <form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='modify.do' />"  id="register.do" >
      <label class="fontSize" >編號：</label>
      <input type="text" name="Serialnumber" value="${aBean.serialnumber}" readonly="readonly"  class="fieldWidth" style="width: 200px;">

      <font color="red" size="-1">${MsgMap.errorSerialnumber}${MsgMap.errorIDDup}</font>
      <br/>
      <label class="fontSize" >縣市：</label>
      <input type="text" name="County" value="${aBean.county}" class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorCounty}</font>      
      <br/>
      
      <label class="fontSize" >職業類別：</label>
      <input type="text" name="Industry" value="${aBean.industry}"   class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorIndustry}</font>            
      <br/>
      
      <label class="fontSize" >受傷原因：</label>
      <input type="text" name="Intermedia" value="${aBean.intermedia}"  class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorIntermedia}</font>
      <br/>
      
      <label class="fontSize" >受傷類別：</label>
      <input type="text"  name="Disaster" value="${aBean.disaster}"    class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorDisaster}</font>
      <br/>
      
      <label class="fontSize" >日期：</label>
          <input type="text"  name="Date" value="${aBean.date}"   class="fieldWidth" style="width: 200px;">
          <span>(yyyy-MM-dd)</span>
          <font color="red" size="-1">${MsgMap.errorDate}</font>
      <br/>
      
     <label class="fontSize" >死亡數：</label>
      <input type="text"  name="Dead" value="${aBean.dead}"    class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorDead}</font>
      <br/>
      
      <label class="fontSize" >受傷數：</label>
      <input type="text"  name="Hurt" value="${aBean.hurt}"    class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorHurt}</font>
      <br/>
      
      
      <div id="btnArea" align="center">
         <input type="submit" name="submit" id="submit" value="儲存"/>
         <input type="reset" name="cancel" id="cancel" value="重填">
      </div>
      <br/>
</form>
</c:forEach>
</TD>
</TR>
</Table>
</div>
</c:otherwise>
	</c:choose>
</body>
</html>