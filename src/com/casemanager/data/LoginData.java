package com.casemanager.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginData {

	private static String login_search  = "select * from user_login where userEmailId = ? and UserPassword = ?";
	public boolean login(String user, String password) throws SQLException {

		PreparedStatement searchPatientStmt = null;
		Connection con = getConnection();

		try {
			searchPatientStmt = con.prepareStatement(login_search);

			searchPatientStmt.setString(1, user);
			searchPatientStmt.setString(2, password);

			ResultSet rs = searchPatientStmt.executeQuery();

			System.out.println("user=" + user + " password="+password);
			if (rs.next()) {
				System.out.println("valid login");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (searchPatientStmt != null) {
				searchPatientStmt.close();
			}
			con.setAutoCommit(true);
		}

		return false;
	}

	private Connection getConnection() throws SQLException {
		DataUtil dbutil = new DataUtil();
		return dbutil.getConnection();
	}

}
