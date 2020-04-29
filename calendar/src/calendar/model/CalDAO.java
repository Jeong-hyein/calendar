package calendar.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection conn = null;

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
			String sql = "delect from calendar where snidate = ? and schedule = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute 실행
			pstmt.setString(1, caldo.getSnidate());
			pstmt.setString(2, caldo.getSchedule());
			pstmt.executeUpdate(); // 처리 건수, DML

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public void search(CalDO caldo) {
		try {
			// 1. connect(DB 연결)
			conn = DriverManager.getConnection(url, "hr", "hr"); // 예외처리를 위한 에러
			// 2. statement(SQL 구문 준비)
			String sql = "select * from caalendar where snidate = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute 실행
			pstmt.setString(1, caldo.getSnidate());
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

	public void selectDate(CalDO caldo) {
		try {
			// 1. connect(DB 연결)
			conn = DriverManager.getConnection(url, "hr", "hr"); // 예외처리를 위한 에러
			// 2. statement(SQL 구문 준비)
			String sql = "select snidate from caalendar where snidate = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute 실행
			pstmt.setString(1, caldo.getSnidate());
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

}
