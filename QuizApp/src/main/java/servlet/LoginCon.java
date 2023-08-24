package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.user.UserFindDao;
import model.data.User;
import model.logic.message.ErrorMessage;

/**
 * Servlet implementation class LoginCon
 */
@WebServlet("/LoginCon")
public class LoginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.sendRedirect("/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			String userName = request.getParameter("username").strip();
			String passWord = request.getParameter("password").strip();
			List<String> eMessage = new ArrayList<>();
			List<String> removeList = new ArrayList<>(Arrays.asList("空"));

			// 空白だった場合のエラー処理
			eMessage.add(ErrorMessage.getErrMessIfEmpty(userName, "ユーザー名"));
			eMessage.add(ErrorMessage.getErrMessIfEmpty(passWord, "パスワード"));
			
			eMessage.removeAll(removeList); // エラーが入ってないlistを消す
			if (!(eMessage.size() == 0)) {
				request.setAttribute("eMessage", eMessage);
				doGet(request, response);
				return;
			}

			eMessage.add(ErrorMessage.getErrMessverifyLogin(userName, passWord));
			
			eMessage.removeAll(removeList); // エラーが入ってないlistを消す
			if (eMessage.size() == 0) {
				User user = UserFindDao.userFind(userName);
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);
				
				response.sendRedirect("QuizCon");
			} else {
				request.setAttribute("eMessage", eMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
