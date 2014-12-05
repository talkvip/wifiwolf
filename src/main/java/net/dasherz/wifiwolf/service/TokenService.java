package net.dasherz.wifiwolf.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.domain.Connection;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.domain.PhoneUser;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.repository.TokenRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TokenService {

	@Inject
	private TokenRepository tokenRepository;

	public List<Token> findAll() {
		return tokenRepository.findAll();
	}

	// 通过页码查找token
	public Page<Token> getPageAuthTypes(int pageNum, int pageSize) {
		return tokenRepository.findAll(new PageRequest(pageNum - 1, pageSize));
	}

	public Token getToken(Long id) {
		return tokenRepository.findOne(id);
	}

	public Token save(Token token) {
		return tokenRepository.save(token);
	}

	public Token createToken(AuthType authType, User registeredUser,
			PhoneUser phoneUser, Node node) {
		Token token = new Token();
		token.setToken(String.valueOf(System.currentTimeMillis()));
		token.setAuthType(authType);
		token.setRegisteredUser(registeredUser);
		token.setPhoneUser(phoneUser);
		token.setNode(node);
		token.setCreateTime(new Date());
		token.setStatus(1);
		return save(token);
	}

	public boolean validateToken(String token) {
		Token result = tokenRepository.findByToken(token);
		if (result == null || result.getStatus() != 1) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isUserOnline(String token) {
		Connection result = tokenRepository.findByToken(token).getConnection();
		if (result == null || result.getStatus() != 1) {
			return false;
		} else {
			return true;
		}
	}

	public Token findByToken(String token) {
		return tokenRepository.findByToken(token);
	}
}