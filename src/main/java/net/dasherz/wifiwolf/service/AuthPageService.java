package net.dasherz.wifiwolf.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.AuthPage;
import net.dasherz.wifiwolf.repository.AuthPageRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthPageService {

	@Inject
	AuthPageRepository authPageRepository;

	public AuthPage getAuthPage() {
		return authPageRepository.findOne(1L);
	}

	public void save(AuthPage authPage) {
		authPageRepository.save(authPage);
	}
}
