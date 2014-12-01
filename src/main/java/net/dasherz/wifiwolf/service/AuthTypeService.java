package net.dasherz.wifiwolf.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.repository.AuthTypeRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthTypeService {

	@Inject
	private AuthTypeRepository authTypeRepository;

	public List<AuthType> findAll() {
		return authTypeRepository.findAll();
	}
}
