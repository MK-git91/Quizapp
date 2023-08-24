package servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.quiz.AnswerDao;
import dao.quiz.QuizDao;
import dao.quiz.ResultDao;
import model.data.Answer;
import model.data.Question;
import model.logic.CorrJudg;
import model.logic.Count;

/**
 * Servlet implementation class QuizCon
 */
@WebServlet("/QuizCon")
public class QuizCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		try {

			// クイズ数を取得して、シャッフルして格納する
			List<Integer> quizNum =Count.quizCount();
			Collections.shuffle(quizNum);
			session.setAttribute("quizNum", quizNum);
			
			int resultNum = ResultDao.resultNumber();
			session.setAttribute("resultNum", resultNum);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// クイズ画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/quiz.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		List<Integer> quizNum = (List<Integer>) session.getAttribute("quizNum"); // 出題番号ランダムBOXの取得
		int resultNum = (int) session.getAttribute("resultNum"); // リザルト番号の取得
		String button = request.getParameter("button"); // 回答結果の取得
		String submit = request.getParameter("submit"); // 
		System.out.println(button);
		try {
			
			// 正誤判定
			if (button != null) {
				String decision = CorrJudg.Corrjudgment(button);
				request.setAttribute("decision", decision);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/answer.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
			// 回答時の動作
//			if (button != null) {
//				Result res = new Result();
//				// ユーザーIDの取得セット
//				//			User user = (User) session.getAttribute("loginUser");
//				//			res.setUserNumber(user.getUserNumber());
//				res.setUserNumber(1);
//				// resultIDの取得セット
//				res.setResultNumber(resultNum);
//				// クイズ番号の取得セット
//				res.setQuizNumber(quizNum.get(0));
//				// 回答の取得セット
//				res.setAnsNumber(button);
//				// 回答の正誤を取得セット
//				res.setCorrect(AnswerDao.correctFind(quizNum.get(0), button));
//				// クイズ回答を記録
//				ResultDao.resultInsert(res);
//
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/answer.jsp");
//				dispatcher.forward(request, response);
//				return;
//			}

			// クイズ取得に使った出題番号ランダムBOXの数値は削除、配列が空になればクイズ終了
			if (submit != null) {
				quizNum.remove(0);
				session.setAttribute("quizNum", quizNum);
			}

			// 出題番号ランダムBOXが無くなれば結果画面に遷移
			if (quizNum.isEmpty()) {
				response.sendRedirect("ResultCon");
				return;
			}

			// クイズの取得
			Question quiz = new Question();
			quiz = QuizDao.quizFind(quizNum.get(0));
			session.setAttribute("quiz", quiz);

			// 回答選択の取得＆シャッフル
			List<Answer> ansList = AnswerDao.ansFind(quiz.getQuizNumber());
			Collections.shuffle(ansList);
			session.setAttribute("ansList", ansList);

			// 回答画面への遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/answer.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
