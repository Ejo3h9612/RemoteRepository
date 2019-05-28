package Insertfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
// 本類別使用為標準的JDBC技術來存取資料庫。
public class MemberDaoImpl_Jdbc {

	private DataSource ds = null;

	public MemberDaoImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BookDataSQLver");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#建構子發生例外: " + ex.getMessage());
		}
	}

	public int saveDate(MidtermBean mb) {
		String sql = "INSERT INTO midterm_table VALUES(?,?,?,?,?,?,?,?)";
		int n = 0;
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setInt(1, mb.getSerialnumber());
			ps.setString(2, mb.getCounty());
			
			ps.setString(3, mb.getIndustry());
			ps.setString(4, mb.getIntermedia());
			ps.setString(5, mb.getDisaster());
			ps.setDate(6, new java.sql.Date(stringtodate(mb.getDate()).getTime()));
			ps.setInt(7, mb.getDead());
			ps.setInt(8, mb.getHurt());
			
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#saveMember()發生例外: " 
										+ ex.getMessage());
		}
		return n;
	}
	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	public boolean idExists(String id) {
		boolean exist = false;
		String sql = "SELECT * FROM midterm_table WHERE Serialnumber = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					exist = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#idExists()發生例外: " 
					+ ex.getMessage());
		}
		return exist;
	}

	private Date stringtodate(String data) throws ParseException {
		String[] tempArray = data.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer year = Integer.parseInt(tempArray[0]);
		
		Integer month = Integer.parseInt(tempArray[1]);
		Integer day = Integer.parseInt(tempArray[2]);
		String datemx = String.format("%d-%d-%d", year, month, day);
		Date date = sdf.parse(datemx);
		return date;
	}
	
}
