package Selectfile;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Selectfile/idtomodify.do")
public class FiletomodifyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();

		// 準備存放錯誤訊息的 Map<String, String> 物件 : errorMsgMap
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		// 將 errorMsgMap 放入 request 置物櫃內，識別字串為 "ErrorMsgKey"
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		// 1. 讀取使用者輸入資料(<Input>標籤內的name屬性分別為 userId與pswd
		String serialnumber = request.getParameter("serialnumber2");

		// 2. 進行必要的資料轉換
		// 本程式無需轉換
		// 3. 檢查使用者輸入資料
		// 如果 userId 欄位為空白，放錯誤訊息"帳號欄必須輸入"到 errorMsgMap 之內
		// 對應的識別字串為 "AccountEmptyError"
		if (serialnumber == null || serialnumber.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "案件編號欄必須輸入");
		}

		// 如果 errorMsgMap 不是空的，表示有錯誤，交棒(forward)給/ch06_01/login.jsp，
		// 然後 return
		if (!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/Selectfile/Selectfile.jsp");
			rd.forward(request, response);
			return;
		}
		MidtermDao dao = new MidtermDao();
		boolean check = dao.check(serialnumber);

		if (!check) {
			errorMsgMap.put("FileError", "該編號不存在");

			RequestDispatcher rd = request.getRequestDispatcher("/Selectfile/Selectfile.jsp");
			rd.forward(request, response);
			return;
		}

		List<MidtermBean> bm = dao.select(serialnumber);

		context.setAttribute("contextMemberBean", bm);
		RequestDispatcher rd = request.getRequestDispatcher("/Selectfile/Modify.jsp");
		rd.forward(request, response);
		return;

	}
}
