package net.dasherz.wifiwolf.common.interceptor;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dasherz.wifiwolf.common.util.CommonUtil;
import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.domain.Log;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.repository.LogRepository;
import net.dasherz.wifiwolf.repository.UserRepository;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject
	private LogRepository logRepository;

	@Inject
	private UserRepository userRepository;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	@Transactional(readOnly = false)
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		String requestUri = request.getRequestURI();
		String uriPrefix = request.getContextPath() + Constants.PATH_MANAGE;

		if ((StringUtils.startsWith(requestUri, uriPrefix) && (StringUtils
				.containsIgnoreCase(requestUri, "save")
				|| StringUtils.containsIgnoreCase(requestUri, "delete") || StringUtils
					.containsIgnoreCase(requestUri, "update")))
				|| ex != null) {

			User user = getUser();
			if (user != null && user.getId() != null) {

				StringBuilder params = new StringBuilder();
				int index = 0;
				for (Object param : request.getParameterMap().keySet()) {
					params.append((index++ == 0 ? "" : "&") + param + "=");
					params.append(StringUtils.endsWithIgnoreCase(
							(String) param, "password") ? "" : request
							.getParameter((String) param));
				}

				Log log = new Log();
				log.setLogType(ex == null ? Log.TYPE_ACCESS
						: Log.TYPE_EXCEPTION);
				log.setCreateBy(user);
				log.setCreateTime(new Date());
				log.setRemoteAddr(CommonUtil.getRemoteAddr(request));
				log.setUserAgent(request.getHeader("user-agent"));
				log.setRequestUri(request.getRequestURI());
				log.setMethod(request.getMethod());
				log.setParams(params.toString());
				log.setException(ex != null ? ex.getMessage() : "");
				logRepository.save(log);

				logger.info("save log {type: {}, loginName: {}, uri: {}}, ",
						log.getLogType(), user.getUsername(),
						log.getRequestUri());

			}
		}

	}

	private User getUser() {
		Subject subject = SecurityUtils.getSubject();
		User user = userRepository.findByUsername(subject.getPrincipal()
				.toString());
		if (user != null) {
			return user;
		}
		return null;
	}

}
