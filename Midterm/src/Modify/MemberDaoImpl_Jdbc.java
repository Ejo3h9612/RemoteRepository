package Modify;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public int modifyDate(MidtermBean mb) {
		String sql = "update midterm_table set County=?,Industry=?,Intermedia=?,Disaster=?,Date=?,Dead=?,Hurt=? where Serialnumber=?";
		int n = 0;
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			
			ps.setString(1, mb.getCounty());
			
			ps.setString(2, mb.getIndustry());
			ps.setString(3, mb.getIntermedia());
			ps.setString(4, mb.getDisaster());
			ps.setDate(5, new java.sql.Date(stringtodate(mb.getDate()).getTime()));
			ps.setInt(6, mb.getDead());
			ps.setInt(7, mb.getHurt());
			ps.setInt(8, mb.getSerialnumber());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#saveMember()發生例外: " 
										+ ex.getMessage());
		}
		return n;
	}

	private Date stringtodate(String data) throws ParseException {
		String[] tempArray = data.split("/");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer year = Integer.parseInt(tempArray[0]);
		
		Integer month = Integer.parseInt(tempArray[1]);
		Integer day = Integer.parseInt(tempArray[2]);
		String datemx = String.format("%d-%d-%d", year, month, day);
		Date date = sdf.parse(datemx);
		return date;
	}
	
}
