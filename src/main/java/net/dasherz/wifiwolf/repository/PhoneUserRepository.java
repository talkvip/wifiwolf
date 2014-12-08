package net.dasherz.wifiwolf.repository;

import java.util.List;

import net.dasherz.wifiwolf.common.repository.BaseRepository;
import net.dasherz.wifiwolf.domain.PhoneUser;

public interface PhoneUserRepository extends BaseRepository<PhoneUser, Long> {
	public PhoneUser findTop1ByPhoneNumAndStatusOrderByIdDesc(String phoneNum,
			Integer status);

	public List<PhoneUser> findByPhoneNumAndStatusOrderByIdDesc(
			String phoneNum, Integer status);

	public List<PhoneUser> findTop100ByOrderByIdDesc();
}
