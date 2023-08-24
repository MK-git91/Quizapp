package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.quiz.ResultDao;
import model.data.Result;
import model.data.User;

@WebServlet("/ResultCon")
public class ResultCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		
		try {
			
			User user = (User) session.getAttribute("loginUser");
			// リザルト結果
//			List<Result> resList = ResultDao.resultFind(user.getUserNumber());
			List<Result> resList = ResultDao.resultFind(1);
			session.setAttribute("resList", resList);
			
			// リザルトレコード数
			int resultNum = ResultDao.resultNumber() - 1;
			session.setAttribute("resultNum", resultNum);
			
			
		} catch(Exception e) {
			
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}


}
