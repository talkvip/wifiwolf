package net.dasherz.wifiwolf.repository;

import java.util.List;

import net.dasherz.wifiwolf.common.repository.BaseRepository;
import net.dasherz.wifiwolf.domain.PageTemplate;

public interface PageTemplateRepository extends
		BaseRepository<PageTemplate, Long> {
	public List<PageTemplate> findByTemplateType(Integer type);

}
