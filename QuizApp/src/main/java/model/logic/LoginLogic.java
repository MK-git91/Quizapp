package model.logic;

import dao.user.UserFindDao;
import model.data.User;

public class LoginLogic {
	
	public static boolean verifyLogin(String userName, String passWord) {
		boolean b = false;
		try {
			User user = UserFindDao.userFind(userName);
			if(userName.equals(user.getUserName()) && passWord.equals(user.getPassword())) {
				b = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return b;
	}
}
