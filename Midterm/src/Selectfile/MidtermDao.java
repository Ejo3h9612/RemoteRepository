package Selectfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.List;
import java.util.Vector;

public class MidtermDao {

	DataSource ds = null;

	public MidtermDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/BookDataSQLver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_ID = "SELECT Serialnumber,County,Industry,Intermedia,Disaster,Date,Dead,Hurt FROM midterm_table WHERE Serialnumber = ?";

	public boolean check(String id) {
		boolean result = false;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setString(1, id);

			ResultSet rset = stmt.executeQuery();

			result=rset.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<MidtermBean> select(String id) {
		List<MidtermBean> result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setString(1, id);
			try (ResultSet rset = stmt.executeQuery();) {
				result = new Vector<>();
				while (rset.next()) {
					MidtermBean temp = new MidtermBean();

					temp.setSerialnumber(rset.getInt("Serialnumber"));
					temp.setCounty(rset.getString("County"));
					temp.setIndustry(rset.getString("Industry"));
					temp.setIntermedia(rset.getString("Intermedia"));
					temp.setDisaster(rset.getString("Disaster"));
					temp.setDate(rset.getString("Date"));
					temp.setDead(rset.getInt("Dead"));
					temp.setHurt(rset.getInt("Hurt"));
					result.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "Delete from midterm_table WHERE Serialnumber =?";

	public int delete(String memberId) {
		int result = 0;
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(DELETE);
		) {
			stmt.setString(1, memberId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}

}