package calendar.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CalDAO {
	String url = "jdbc:oracle:thin:@192.168.0.35:1521:xe";
	Connection conn = null;
	DateFormat dateFormat = new SimpleDateFormat ("yyyy.MM.dd");

	
	public void insert(CalDO caldo) {
		try {
			// 1. connect(DB 연결)
			conn = DriverManager.getConnection(url, "hr", "hr"); // 예외처리를 위한 에러
			// 2. statement(SQL 구문 준비)
			String sql = "insert into calendar (snidate, schedule, memo) values (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute 실행
			pstmt.setString(1, caldo.getSnidate());
			pstmt.setString(2, caldo.getSchedule());
			pstmt.setString(3, caldo.getMemo());

			pstmt.executeUpdate(); // 처리 건수, DML

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	// 삭제
	public void delete(CalDO caldo) {
		try {
			// 1. connect(DB 연결)
			conn = DriverManager.getConnection(url, "hr", "hr"); // 예외처리를 위한 에러
			// 2. statement(SQL 구문 준비)
			String sql = "delete from calendar where snidate = ? and schedule = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute 실행
			pstmt.setString(1, caldo.getSnidate().toString());
			pstmt.setString(2, caldo.getSchedule());
			pstmt.executeUpdate(); // 처리 건수, DML

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// 5. close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public CalDO search(CalDO caldo) {
		try {
			// 1. connect(DB 연결)
			conn = DriverManager.getConnection(url, "hr", "hr"); // 예외처리를 위한 에러
			// 2. statement(SQL 구문 준비)
			String sql = "select * from calendar where snidate = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute 실행
			pstmt.setString(1, caldo.getSnidate().toString());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { 
				caldo.setSnidate(rs.getString("snidate"));
				caldo.setSchedule(rs.getString("schedule"));
				caldo.setMemo(rs.getString("memo"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return caldo;
	}

	public CalDO selectDate(CalDO caldo) {
		try {
			// 1. connect(DB 연결)
			conn = DriverManager.getConnection(url, "hr", "hr"); // 예외처리를 위한 에러
			// 2. statement(SQL 구문 준비)
			String sql = "select snidate from caalendar where snidate = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute 실행
			pstmt.setString(1, caldo.getSnidate().toString());
			pstmt.executeUpdate(); // 처리 건수, DML

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return caldo;
	}

}
