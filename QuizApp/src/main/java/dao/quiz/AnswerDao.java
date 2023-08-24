package dao.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.resouce.ResourceFinder;
import model.data.Answer;

public class AnswerDao {
	// クイズ番号を引数にとり、そのクイズの回答を取得する
	public static List<Answer> ansFind(int quizNumber) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Answer> ansList = new ArrayList<>();

		try {
			conn = new ResourceFinder().getConnection();
			String sql = "SELECT * FROM answer WHERE quiz_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, quizNumber);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Answer ans = new Answer();
				ans.setAnsNumber(rs.getString("answer_id"));
				ans.setAnswer(rs.getString("answer"));
				ans.setCorrect(rs.getString("correct"));
				ansList.add(ans);
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
		return ansList;
	}
	
	// クイズ番号と回答を引数に渡し、"正解"か"不正解"を返すメソッド
	public static String correctFind(int quizNumber, String answer) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String correct = "不正解";
		
		
		try {
			conn = new ResourceFinder().getConnection();
			String sql = "SELECT * FROM answer WHERE quiz_id = ? AND answer_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, quizNumber);
			stmt.setString(2, answer);
			rs = stmt.executeQuery();
			rs.next();
			String judg = rs.getString("correct");
			if (judg.equals("true")) {
				correct = "正解";
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
		return correct;
	}
}
