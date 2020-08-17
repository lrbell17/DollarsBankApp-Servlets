package handler;

import dao.AccountDao;
import model.LoginBean;

public class LoginHandler {

		public String validate(LoginBean lb) {
			
			// Check if lb matches up w/ row in DB
			AccountDao d = new AccountDao();
			boolean match = d.userMatch(lb.getUname(), lb.getPass());
			
			if (match) {
				return "success";
			}
			else {
				return "error";
			}
		}
		
		
}
