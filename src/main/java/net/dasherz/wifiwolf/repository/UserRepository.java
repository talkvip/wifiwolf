package net.dasherz.wifiwolf.repository;

import net.dasherz.wifiwolf.common.repository.BaseRepository;
import net.dasherz.wifiwolf.domain.User;

import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BaseRepository<User, Long> {
	@Query("select user from User user where user.username = ?1 and user.accountStatus = 1")
	User findByUsername(String username);

	@Query("select user from User user where user.username = ?1 and user.accountStatus = 1")
	User findByPhone(String phone);
}
