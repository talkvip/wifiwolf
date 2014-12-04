package net.dasherz.wifiwolf.repository;

import net.dasherz.wifiwolf.common.repository.BaseRepository;
import net.dasherz.wifiwolf.domain.AuthType;

public interface AuthTypeRepository extends BaseRepository<AuthType, Long> {
	AuthType findByStatus(Integer status);
}
