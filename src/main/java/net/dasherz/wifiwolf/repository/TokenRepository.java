package net.dasherz.wifiwolf.repository;

import net.dasherz.wifiwolf.common.repository.BaseRepository;
import net.dasherz.wifiwolf.domain.Token;

public interface TokenRepository extends BaseRepository<Token, Long> {
	Token findByToken(String token);
}
