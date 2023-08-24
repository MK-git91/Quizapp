package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.resouce.ResourceFinder;
import model.data.User;

// ユーザー名、パスワード、IDを取得するためのクラス
public class UserFindDao {
	
	// 一致したユーザー名、パスワード、IDを取得してオブジェクトを返す
	public static User userFind(String userName) throws Exception{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = new User();
		

		try {
			conn = new ResourceFinder().getConnection();
			String sql = "SELECT * FROM user WHERE username = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			rs =  stmt.executeQuery();
			rs.next();
			user.setUserNumber(rs.getInt("user_id"));
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			
			
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {

			try {
				
				if (rs != null) rs.close();
			    if (stmt != null) stmt.close();
			    if (conn != null) conn.close();

			} catch (Exception e) {
				throw e;
			}
		}
		return user;
	}
}
