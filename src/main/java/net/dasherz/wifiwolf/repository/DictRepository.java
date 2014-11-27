package net.dasherz.wifiwolf.repository;

import net.dasherz.wifiwolf.common.repository.BaseRepository;
import net.dasherz.wifiwolf.domain.Dict;

public interface DictRepository extends BaseRepository<Dict, Long> {
	public Dict findByGroupNameAndCode(String groupName, String code);

	public Dict findByGroupNameAndName(String groupName, String name);

}
