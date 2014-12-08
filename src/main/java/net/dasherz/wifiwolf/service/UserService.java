package net.dasherz.wifiwolf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.common.shiro.Digests;
import net.dasherz.wifiwolf.common.shiro.Encodes;
import net.dasherz.wifiwolf.common.shiro.ShiroDbRealm.ShiroUser;
import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.common.util.ValidationCode;
import net.dasherz.wifiwolf.domain.PhoneUser;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.repository.UserRepository;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	@Inject
	private UserRepository userDao;

	@Inject
	private PhoneUserService phoneUserService;

	// 增加用户
	public void createUser(User user) {

		user.setAccountStatus(1);
		user.setWifiStatus(1);
		user.setUserType(2);
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
	public Page<User> getPageUsers(int pageNum, int pageSize) {

		return userDao.findAll(buildSpecification(null), new PageRequest(
				pageNum - 1, pageSize));
	}

	// 带动态条件分页查找用户
	public Page<User> searchUsers(int pageNum, int pageSize, User user) {
		return userDao.findAll(buildSpecification(user), new PageRequest(
				pageNum - 1, pageSize));
	}

	// 查找所有用户
	public List<User> getAllUser() {
		return userDao.findAll(buildSpecification(null));
	}

	// 通过用户名查找用户
	public User findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	// 通过手机号查找用户
	public User findUserByPhone(String phoneNum) {
		return userDao.findByPhone(phoneNum);
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
		u.setAccountStatus(0);
		userDao.saveAndFlush(u);
	}

	public void updateUser(User user) {
		userDao.saveAndFlush(user);
	}

	public boolean resetPassword(String username) {
		User userInDb = userDao.findByUsername(username);
		if (userInDb == null) {
			userInDb = userDao.findByPhone(username);
			if (userInDb == null) {
				return false;
			}
		}

		String newPlainPassword = PhoneUserService.createRandom(false, 6);
		userInDb.setPassword(entryptPassword(newPlainPassword));
		// TODO: sent the new plainPassword to the user phone.
		userDao.saveAndFlush(userInDb);
		return true;
	}

	public void changePassword(User user) {
		// 更新密码
		if (user.getPassword() != null) {
			user.setPassword(entryptPassword(user.getPassword()));
		}
		userDao.saveAndFlush(user);
	}

	public boolean registerUser(String userPassword, String phoneNum,
			String phoneCode, String authType) {
		if (authType == null || authType.isEmpty()) {
			// TODO: return a code to inform the user select an authType.
			return false;
		}

		if (authType.equalsIgnoreCase("PHONE")) {
			return registerByPhoneNum(phoneNum);
		} else if (authType.equalsIgnoreCase("PHONE_SMS")) {
			return registerByPhoneNumAndSMS(phoneNum, phoneCode);
		} else if (authType.equalsIgnoreCase("PHONE_PASSWORD")) {
			return registerByUserPassword(phoneNum, userPassword);
		} else if (authType.equalsIgnoreCase("PHONE_PASSWORD_SMS")) {
			return registerByPhoneNum_Password_SMS(phoneNum, phoneCode,
					userPassword);
		} else {
			// TODO: return a code to indicate successful.
			return true;
		}
	}

	private boolean registerByPhoneNum(String phoneNum) {
		boolean isCorrectNum = validatePhoneNum(phoneNum);
		if (isCorrectNum == false) {
			return false;
		}

		// TODO: return a code to indicate successful.
		User user = userDao.findByPhone(phoneNum);
		if (user == null) {
			user = new User();
			user.setUsername(PhoneUserService.createRandom(false, 6));
			user.setPassword(PhoneUserService.createRandom(true, 6));
			user.setPhone(phoneNum);
			createUser(user);
			return true;
		} else {
			return false;
		}
	}

	private boolean registerByPhoneNumAndSMS(String phoneNum, String phoneCode) {
		boolean isCorrectNum = validatePhoneNum(phoneNum);
		if (isCorrectNum == false) {
			return false;
		}

		User user = userDao.findByPhone(phoneNum);
		if (user != null) {
			return false;
		}

		PhoneUser userInDb = phoneUserService.findByPhoneNum(phoneNum);
		if (userInDb == null) {
			return false;
		}
		if (!phoneCode.equalsIgnoreCase(userInDb.getVerifyCode())) {
			return false;
		}

		user = new User();
		user.setUsername(PhoneUserService.createRandom(false, 6));
		user.setPassword(PhoneUserService.createRandom(false, 6));
		user.setPhone(phoneNum);
		user.setIsPhoneVerified(1);
		createUser(user);
		return true;
	}

	private boolean registerByUserPassword(String phoneNum, String userPassword) {
		boolean isCorrectNum = validatePhoneNum(phoneNum);
		if (isCorrectNum == false) {
			return false;
		}

		User user = userDao.findByPhone(phoneNum);
		if (user != null) {
			return false;
		}

		user = new User();
		user.setUsername(PhoneUserService.createRandom(false, 6));
		user.setPassword(userPassword);
		user.setPhone(phoneNum);
		createUser(user);
		return true;
	}

	private boolean registerByPhoneNum_Password_SMS(String phoneNum,
			String phoneCode, String userPassword) {
		boolean isCorrectNum = validatePhoneNum(phoneNum);
		if (isCorrectNum == false) {
			return false;
		}

		User user = userDao.findByPhone(phoneNum);
		if (user != null) {
			return false;
		}

		PhoneUser phoneUserInDb = phoneUserService.findByPhoneNum(phoneNum);
		if (phoneUserInDb == null) {
			return false;
		}

		if (!phoneCode.equalsIgnoreCase(phoneUserInDb.getVerifyCode())) {
			return false;
		}

		user = new User();
		user.setUsername(PhoneUserService.createRandom(false, 6));
		user.setPassword(userPassword);
		user.setPhone(phoneNum);
		user.setIsPhoneVerified(1);
		createUser(user);
		return true;
	}

	public ValidationCode validateUser(String userName, String userPassword,
			String phoneNum, String phoneCode, String authType) {
		if (authType == null || authType.isEmpty()) {
			return ValidationCode.ERROR_SYSTEM_AUTH_TYPE;
		}

		if (authType.equalsIgnoreCase("PHONE")) {
			return validateByPhoneNum(phoneNum);
		} else if (authType.equalsIgnoreCase("PHONE_SMS")) {
			return validateByPhoneNumAndSMS(phoneNum, phoneCode);
		} else if (authType.equalsIgnoreCase("PHONE_PASSWORD")) {
			return validateByUserPassword(userName, phoneNum, userPassword);
		} else if (authType.equalsIgnoreCase("PHONE_PASSWORD_SMS")) {
			return validateByPhoneNum_Password_SMS(phoneNum, phoneCode,
					userPassword);
		} else {
			return ValidationCode.VALID;
		}
	}

	private ValidationCode validateByPhoneNum(String phoneNum) {
		boolean validPhone = Pattern.matches("^1\\d{10}$", phoneNum);
		if (!validPhone) {
			return ValidationCode.ERROR_PHONE_NUMBER_FORMAT;
		}
		return ValidationCode.VALID;
	}

	private ValidationCode validateByPhoneNumAndSMS(String phoneNum,
			String phoneCode) {
		PhoneUser userInDb = phoneUserService.findByPhoneNum(phoneNum);
		if (userInDb == null) {
			return ValidationCode.ERROR_VERIFY_CODE_NOT_EXIST;
		}
		if (!phoneCode.equalsIgnoreCase(userInDb.getVerifyCode())) {
			return ValidationCode.ERROR_VERIFY_CODE_WRONG;
		}
		return ValidationCode.VALID;
	}

	private ValidationCode validateByUserPassword(String userName,
			String phoneNum, String userPassword) {
		User userInDb = findUserByUsername(userName);
		if (userInDb == null) {
			userInDb = findUserByPhone(phoneNum);
			if (userInDb == null) {
				return ValidationCode.ERROR_ID_PASSWORD;
			}
			if (userInDb.getWifiStatus().equals(
					Constants.STATUS_USER_WIFI_ENABLED)) {
				return ValidationCode.ERROR_WIFI_DISABLED;
			}
		}

		if (!validatePassword(userPassword, userInDb.getPassword())) {
			return ValidationCode.ERROR_ID_PASSWORD;
		}
		return ValidationCode.VALID;
	}

	private ValidationCode validateByPhoneNum_Password_SMS(String phoneNum,
			String phoneCode, String userPassword) {
		PhoneUser phoneUserInDb = phoneUserService.findByPhoneNum(phoneNum);
		User userInDb = this.findUserByPhone(phoneNum);
		if (userInDb == null) {
			return ValidationCode.ERROR_ID_PASSWORD;
		}
		if (userInDb.getWifiStatus().equals(Constants.STATUS_USER_WIFI_ENABLED)) {
			return ValidationCode.ERROR_WIFI_DISABLED;
		}

		if (!validatePassword(userPassword, userInDb.getPassword())) {
			return ValidationCode.ERROR_ID_PASSWORD;
		}

		if (!phoneCode.equalsIgnoreCase(phoneUserInDb.getVerifyCode())) {
			return ValidationCode.ERROR_VERIFY_CODE_WRONG;
		}
		return ValidationCode.VALID;
	}

	private boolean validatePhoneNum(String phoneNum) {
		if (phoneNum.length() != 11) {
			return false;
		}
		if (!isNumeric(phoneNum)) {
			return false;
		}
		return true;
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

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	private Specification<User> buildSpecification(final User user) {
		if (user != null) {
			return new Specification<User>() {

				public Predicate toPredicate(Root<User> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					/**
					 * 连接查询条件, 不定参数，可以连接0..N个查询条件
					 */
					List<Predicate> list = new ArrayList<Predicate>();
					if (user.getUsername() != null
							&& !user.getUsername().isEmpty()) {
						list.add(cb.equal(root.get("username"),
								user.getUsername()));
					}
					if (user.getPhone() != null && !user.getPhone().isEmpty()) {
						list.add(cb.equal(root.get("phone"), user.getPhone()));
					}
					if (user.getEmail() != null && !user.getEmail().isEmpty()) {
						list.add(cb.equal(root.get("email"), user.getEmail()));
					}
					list.add(cb.equal(root.get("accountStatus"), 1));

					Predicate[] p = new Predicate[list.size()];
					return cb.and(list.toArray(p));
				}
			};
		} else {
			return new Specification<User>() {

				public Predicate toPredicate(Root<User> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					Path<String> accountStatus = root.get("accountStatus");
					/**
					 * 连接查询条件, 不定参数，可以连接0..N个查询条件
					 */
					query.where(cb.equal(accountStatus, 1)); // 这里可以设置任意条查询条件

					return null;
				}
			};
		}
	}
}
