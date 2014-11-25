package net.dasherz.wifiwolf.service;

import java.util.Date;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.repository.UserRepository;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Inject
	private UserRepository userDao;

	// 获取所有用户列表
	// public Page<User> getAllUser(PageInfo page) {
	// return userDao.findAll(page);
	// }

	// 通过编号查找用户
	public User getUser(Long userId) {
		return userDao.findOne(userId);
	}

	// 通过用户名查找用户
	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	// 增加用户
	public void createUser(User user) {

		// 生成用户密码和加密
		user.setCreateTime(new Date());
		userDao.saveAndFlush(user);
	}

	public boolean isExist(String username) {
		User u = this.findUserByUsername(username);
		return u != null;
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return user.getUsername();
	}
}
