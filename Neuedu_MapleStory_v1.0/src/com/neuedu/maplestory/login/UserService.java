package com.neuedu.maplestory.login;

import java.util.Date;
import java.util.List;



public class UserService implements ImplUserService {

	private ImplUserDao dao = new UserDao();
	@Override
	public boolean insertUser(User u) {
		List<User> list = dao.readAll();
		/**
		 * 判断之前是否有该用户名
		 */
		for (User user : list) {
			if (user.getUsername().equals(u.getUsername())) {
				return false;
			}
		}
		u.setId(dao.getNewID());
		u.setRegisterTime(new Date());
		u.setHighestRecordTime(new Date());
		u.setGrade(0);
		list.add(u);
		return dao.writeAll(list);
	}

	@Override
	public boolean updatePassword(String nickName, String password) {
		List<User> list = dao.readAll();
		for (User u : list) {
			if (u.getUsername().equals(nickName)) {
				u.setPwd(password);
			    break;
			}
		}
		return dao.writeAll(list);
	}

	@Override
	public List<User> selectAll() {
		return dao.readAll();
	}

	@Override
	public boolean updateScore(String nickName, int score) {
		List<User> list = dao.readAll();
		for (User u : list) {
			if (u.getUsername().equals(nickName)) {
				u.setGrade(score);
				u.setHighestRecordTime(new Date());
				break;
			}
		}
		return dao.writeAll(list);
	}

	@Override
	public User selectUser(String nickName, String password) {
		User loginUser = null;
		List<User> list = dao.readAll();
		for (User user : list) {
			if (user.getUsername().equals(nickName) 
					&& user.getPwd().equals(password)) {
				loginUser = user;
				break;
			}
		}
		return loginUser;
	}

	@Override
	public User selectUserByScore(int score) {
		User u = null;
		List<User> list = dao.readAll();
		for (User user : list) {
			if (user.getGrade() == score) {
				u = user;
				break;
			}
		}
		return u;
	}


}
