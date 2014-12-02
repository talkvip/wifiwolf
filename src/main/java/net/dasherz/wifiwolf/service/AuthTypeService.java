package net.dasherz.wifiwolf.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.common.util.DictUtils;
import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.repository.AuthTypeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthTypeService {

	private static final String DICT_GROUP_AUTHTYPE = "auth_type";
	@Inject
	private AuthTypeRepository authTypeRepository;

	public List<AuthType> findAll() {
		List<AuthType> authTypes = authTypeRepository.findAll();
		for (AuthType authType : authTypes) {
			authType.setDescription(DictUtils.getName(DICT_GROUP_AUTHTYPE,
					authType.getAuthType(), ""));
		}
		return authTypes;
	}

	// 通过页码查找用户
	public Page<AuthType> getPageAuthTypes(int pageNum, int pageSize) {
		return authTypeRepository
				.findAll(new PageRequest(pageNum - 1, pageSize));
	}

	public AuthType getAuthType(Long id) {
		return authTypeRepository.findOne(id);
	}

	public void save(AuthType authType) {
		authTypeRepository.save(authType);
	}
}
