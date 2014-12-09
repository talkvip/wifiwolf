package net.dasherz.wifiwolf.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.common.util.DateUtil;
import net.dasherz.wifiwolf.common.util.RandomUtil;
import net.dasherz.wifiwolf.common.util.SentSMSResult;
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

	public static final int DEFAULT_PHONE_CODE_SIZE = 4;

	public List<PhoneUser> findAll() {
		return phoneUserRepository.findAll();
	}

	public List<PhoneUser> findLatest100() {
		return phoneUserRepository.findTop100ByOrderByIdDesc();
	}

	public Page<PhoneUser> getPageAuthTypes(int pageNum, int pageSize) {
		return phoneUserRepository.findAll(new PageRequest(pageNum - 1,
				pageSize));
	}

	public PhoneUser getToken(Long id) {
		return phoneUserRepository.findOne(id);
	}

	public PhoneUser findByPhoneNum(String phoneNum) {
		return phoneUserRepository.findTop1ByPhoneNumAndStatusOrderByIdDesc(
				phoneNum, Constants.STATUS_PHONE_USER_NOT_USED);
	}

	public PhoneUser save(PhoneUser phoneUser) {
		return phoneUserRepository.save(phoneUser);
	}

	public PhoneUser savePhoneNumber(String phoneNumber) {
		PhoneUser phoneUser = new PhoneUser();
		phoneUser.setCreateTime(new Date());
		phoneUser.setPhoneNum(phoneNumber);
		phoneUser.setStatus(Constants.STATUS_PHONE_USER_NOT_USED);
		return phoneUserRepository.save(phoneUser);
	}

	public SentSMSResult sendPhoneMessage(String phoneNum) {

		PhoneUser phoneUser = this.findByPhoneNum(phoneNum);
		if (phoneUser != null) {
			long minutes = DateUtil.getMinutesPasted(phoneUser.getCreateTime());
			// in case the user request the verify code too often
			if (minutes < 1) {
				return SentSMSResult.ERROR_REQUEST_LESS_ONE_MIN;
			}
		}

		// add SMS count restriction for use, set a max count 10 for every day
		List<PhoneUser> latestCodes = phoneUserRepository
				.findTop10ByPhoneNumOrderByIdDesc(phoneNum);
		if (latestCodes.size() == 10) {
			long days = DateUtil.getDaysPasted(latestCodes.get(9)
					.getCreateTime());
			if (days <= 1) {
				return SentSMSResult.ERROR_REQUEST_EXCEED_MAX;
			}
		}

		// clear previous verify code for this phone
		List<PhoneUser> existingCode = phoneUserRepository
				.findByPhoneNumAndStatusOrderByIdDesc(phoneNum,
						Constants.STATUS_PHONE_USER_NOT_USED);
		for (int i = 0; i < existingCode.size(); i++) {
			existingCode.get(i).setStatus(Constants.STATUS_PHONE_USER_USED);
		}
		phoneUserRepository.save(existingCode);
		// generate new verify code
		phoneUser = new PhoneUser();
		phoneUser.setPhoneNum(phoneNum);
		phoneUser.setCreateTime(new Date());
		String verifyCode = RandomUtil.createRandom(true,
				DEFAULT_PHONE_CODE_SIZE);
		SentSMSResult result = sendPhoneVerifyCode(phoneNum, verifyCode);
		if (!result.equals(SentSMSResult.SUCCESS)) {
			return result;
		}
		phoneUser.setVerifyCode(verifyCode);
		phoneUser.setStatus(Constants.STATUS_PHONE_USER_NOT_USED);
		save(phoneUser);
		return SentSMSResult.SUCCESS;
	}

	private SentSMSResult sendPhoneVerifyCode(String phoneNum, String code) {
		// TODO: use 3rd-party method to send
		return SentSMSResult.SUCCESS;
	}

	public void verifiedForPhoneNumber(PhoneUser phoneUser) {
		phoneUser.setVerifyTime(new Date());
		phoneUser.setStatus(Constants.STATUS_PHONE_USER_USED);
		phoneUserRepository.save(phoneUser);

	}

}
