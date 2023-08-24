package dao.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.resouce.ResourceFinder;
import model.data.Result;

public class ResultDao {
	// クイズ問題の結果を保存する
	public static void resultInsert(Result res) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = new ResourceFinder().getConnection();
			String sql = "INSERT INTO result VALUES(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, res.getUserNumber());
			stmt.setInt(2, res.getResultNumber());
			stmt.setInt(3, res.getQuizNumber());
			stmt.setString(4, res.getAnsNumber());
			stmt.setString(5, res.getCorrect());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				throw e;
			}
		}
	}

	// リザルト番号を調べ、+1の数値を返す。
	public static int resultNumber() throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int num = 0;

		try {
			conn = new ResourceFinder().getConnection();
			String sql = "SELECT * FROM result";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				num = rs.getInt("result_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				throw e;
			}
		}
		return num + 1;
	}

	// ユーザー毎のリザルトを取得する
	public static List<Result> resultFind(int userNumber) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Result> resList = new ArrayList<>();

		try {
			conn = new ResourceFinder().getConnection();
			String sql = "SELECT * FROM result WHERE user_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userNumber);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Result res = new Result();
				res.setUserNumber(rs.getInt("user_id"));
				res.setResultNumber(rs.getInt("result_id"));
				res.setQuizNumber(rs.getInt("quiz_id"));
				res.setAnsNumber(rs.getString("answer_id"));
				res.setCorrect(rs.getString("correct"));
				resList.add(res);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				throw e;
			}
		}
		return resList;
	}
}
