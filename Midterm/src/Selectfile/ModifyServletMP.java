package Selectfile;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/Selectfile/modify.do")
public class ModifyServletMP extends HttpServlet {
	private static final long serialVersionUID = 1L;


	//@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息

		String serialnumber = null;	
		String county =null;
		String industry =null;
		String intermedia =null;
		String disaster =null;
		String date =null;
		String dead=null;
		String hurt=null;
		
		Collection<Part> parts = request.getParts();
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
					if (fldName.equals("Serialnumber")) {
						serialnumber = value;
					} else if (fldName.equals("County")) {
						county = value;
					} else if (fldName.equals("Industry")) {
						industry = value;
					} else if (fldName.equals("Intermedia")) {
						intermedia = value;
					} else if (fldName.equals("Disaster")) {
						disaster = value;
					} else if (fldName.equals("Date")) {
						date = value;
					}else if (fldName.equals("Dead")) {
						dead = value;
					}else if (fldName.equals("Hurt")) {
						hurt = value;
					}
				
			}
			// 2. 進行必要的資料轉換
			// (無)
			// 3. 檢查使用者輸入資料
			if (serialnumber == null || serialnumber.trim().length() == 0) {
				errorMsg.put("errorSerialnumber", "編號欄必須輸入");
			}
			if (county == null || county.trim().length() == 0) {
				errorMsg.put("errorCounty", "縣市欄必須輸入");
			}
			if (industry == null || industry.trim().length() == 0) {
				errorMsg.put("errorIndustry", "職業類別欄必須輸入");
			}

			if (intermedia == null || intermedia.trim().length() == 0) {
				errorMsg.put("errorIntermedia", "受傷原因欄必須輸入");
			}
			if (disaster == null || disaster.trim().length() == 0) {
				errorMsg.put("errorDisaster", "受傷類別欄必須輸入");
			}
			if (date == null || date.trim().length() == 0) {
				errorMsg.put("errorDate", "日期欄必須輸入");
			}
			if (dead == null || dead.trim().length() == 0) {
				errorMsg.put("errorDead", "死亡數欄必須輸入");
			}
			if (hurt == null || hurt.trim().length() == 0) {
				errorMsg.put("errorHurt", "受傷數欄必須輸入");
			}

		} else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
	
		// 如果有錯誤
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("Modify.jsp");
			rd.forward(request, response);
			return;
		}
		try {
			// 4. 產生MemberDao物件，以便進行Business Logic運算
			// MemberDaoImpl_Jdbc類別的功能：
			// 1.檢查帳號是否已經存在，已存在的帳號不能使用，回傳相關訊息通知使用者修改
			// 2.若無問題，儲存會員的資料
			ModifyDaoImpl_Jdbc service = new ModifyDaoImpl_Jdbc();
			
				MidtermBean mem = new MidtermBean( Integer.parseInt(serialnumber),county,industry,intermedia,disaster,date, Integer.parseInt(dead), Integer.parseInt(hurt));
				
				int n = service.modifyDate(mem);
				if (n == 1) {
					
					response.sendRedirect("Modifysuccessfully.jsp");
					return;
				} else {
					errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
				}
			
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("Modify.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Modify.jsp");
			rd.forward(request, response);
		}
	}
}