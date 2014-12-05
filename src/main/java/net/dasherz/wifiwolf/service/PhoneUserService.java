package net.dasherz.wifiwolf.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.PhoneUser;
import net.dasherz.wifiwolf.repository.PhoneUserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PhoneUserService {

	@Inject
	private PhoneUserRepository phoneUserRepository;

	public List<PhoneUser> findAll() {
		return phoneUserRepository.findAll();
	}

	// 通过页码查找token
	public Page<PhoneUser> getPageAuthTypes(int pageNum, int pageSize) {
		return phoneUserRepository.findAll(new PageRequest(pageNum - 1,
				pageSize));
	}

	public PhoneUser getToken(Long id) {
		return phoneUserRepository.findOne(id);
	}

	public PhoneUser findByPhoneNum(String phoneNum) {
		return phoneUserRepository.findByPhoneNum(phoneNum);
	}

	public PhoneUser save(PhoneUser phoneUser) {
		return phoneUserRepository.save(phoneUser);
	}

	public void sendPhoneMessage(String phoneNum) {
		PhoneUser phoneUser = new PhoneUser();
		phoneUser.setPhoneNum(phoneNum);
		phoneUser.setCreateTime(new Date());
		phoneUser.setVerifyCode(sendPhoneCode(phoneNum));
		phoneUser.setStatus(1);
		save(phoneUser);
	}

	private String sendPhoneCode(String phoneNum) {
		String code = createRandom(true, 4);
		// TODO: use 3rd-party method to send
		return code;
	}

	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
}
