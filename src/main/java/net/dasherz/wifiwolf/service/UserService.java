package net.dasherz.wifiwolf.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.dasherz.wifiwolf.common.shiro.Digests;
import net.dasherz.wifiwolf.common.shiro.Encodes;
import net.dasherz.wifiwolf.common.shiro.ShiroDbRealm.ShiroUser;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.repository.UserRepository;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static int PAGE_SIZE = 10;

	private Specification<User> spec = new Specification<User>() {

		public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
				CriteriaBuilder cb) {
			Path<String> accountStatus = root.get("accountStatus");
			/**
			 * 连接查询条件, 不定参数，可以连接0..N个查询条件
			 */
			query.where(cb.equal(accountStatus, 1)); // 这里可以设置任意条查询条件

			return null;
		}
	};

	@Inject
	private UserRepository userDao;

	// 增加用户
	public void createUser(User user) {

		// 生成用户密码和加密
		user.setCreateTime(new Date());
		user.setPassword(entryptPassword(user.getPassword()));
		userDao.saveAndFlush(user);
	}

	// 增加用户
	public int getUserCount() {
		return userDao.getUserCount();
	}

	// 通过编号查找用户
	public User getUser(Long userId) {
		return userDao.findByUserId(userId);
	}

	// 通过页码查找用户
	public Page<User> getPageUsers(int pageNum) {
		return userDao.findAll(spec, new PageRequest(pageNum - 1, PAGE_SIZE));
	}

	// 查找所有用户
	public List<User> getAllUser() {
		return userDao.findAll(spec);
	}

	// 通过用户名查找用户
	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public boolean isExist(String username) {
		User u = this.findUserByUsername(username);
		return u != null;
	}

	/**
	 * 判断是否超级管理员.
	 */
	public boolean isSupervisor(Integer userType) {
		return userType == 1;
	}

	/**
	 * 判断是否超级管理员.
	 */
	public boolean isSupervisorForCurrentUser() {
		return SecurityUtils.getSubject().hasRole("admin");
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	public String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user == null) {
			return "";
		}
		return user.toString();
	}

	public void remove(Long id) {
		User u = getUser(id);
		u.setAccountStatus(1);
		userDao.saveAndFlush(u);
	}

	public void updateUser(User user) {
		userDao.saveAndFlush(user);
	}

	public void changePassword(User user) {
		// 更新密码
		if (user.getPassword() != null) {
			user.setPassword(entryptPassword(user.getPassword()));
		}
		userDao.saveAndFlush(user);
	}

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
				HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0, 16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
				HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)
				+ Encodes.encodeHex(hashPassword));
	}
}
