package dao.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.resouce.ResourceFinder;
import model.data.Question;

public class QuizDao {
	// クイズの取得
	public static Question quizFind(int quizNumber) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Question quiz = new Question();

		try {
			conn = new ResourceFinder().getConnection();
			String sql = "SELECT * FROM quiz WHERE quiz_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, quizNumber);
			rs = stmt.executeQuery();
			rs.next();
			quiz.setQuizNumber(rs.getInt("quiz_id"));
			quiz.setQuestion(rs.getString("question"));

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
		return quiz;
	}
	
	// クイズ数の取得
	public static List<Integer> quizCount() throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i = 1;
		List<Integer> num = new ArrayList<>();

		try {
			conn = new ResourceFinder().getConnection();
			String sql = "SELECT * FROM quiz";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				num.add(i++);
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
		return num;
	}
}
