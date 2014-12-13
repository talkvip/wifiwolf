package net.dasherz.wifiwolf.repository;

import java.util.List;

import net.dasherz.wifiwolf.common.repository.BaseRepository;
import net.dasherz.wifiwolf.domain.Connection;

public interface ConnectionRepository extends BaseRepository<Connection, Long> {

	public List<Connection> findByTokenNodeIdAndStatus(Long id, Integer status);

	public Connection findByTokenRegisteredUserIdAndStatus(Long id,
			Integer status);

}
