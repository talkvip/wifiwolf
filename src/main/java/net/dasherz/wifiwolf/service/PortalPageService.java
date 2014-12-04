package net.dasherz.wifiwolf.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.PortalPage;
import net.dasherz.wifiwolf.repository.PortalPageRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class PortalPageService {

	@Inject
	PortalPageRepository portalPageRepository;

	public PortalPage getPortalPage() {
		return portalPageRepository.findOne(1L);
	}

	public void save(PortalPage portalPage) {
		portalPageRepository.save(portalPage);
	}
}
