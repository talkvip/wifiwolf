package net.dasherz.wifiwolf.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.Log;
import net.dasherz.wifiwolf.repository.LogRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LogService {

	@Inject
	private LogRepository logRepository;

	public Page<Log> getLogs(int pageNum, int pageSize, Log log) {
		Specification<Log> spec = buildSpecification(log);
		if (spec == null) {
			return logRepository
					.findAll(new PageRequest(pageNum - 1, pageSize));
		} else {
			return logRepository.findAll(spec, new PageRequest(pageNum - 1,
					pageSize, new Sort(Direction.DESC, "id")));
		}
	}

	private Specification<Log> buildSpecification(final Log log) {
		if (log != null) {
			return new Specification<Log>() {

				public Predicate toPredicate(Root<Log> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					/**
					 * 连接查询条件, 不定参数，可以连接0..N个查询条件
					 */
					List<Predicate> list = new ArrayList<Predicate>();
					if (log.getLogType() != null) {
						list.add(cb.equal(root.get("logType"), log.getLogType()));
					}
					if (log.getRequestUri() != null
							&& !log.getRequestUri().isEmpty()) {
						list.add(cb.like(root.get("requestUri")
								.as(String.class), "%" + log.getRequestUri()
								+ "%"));
					}

					if (list.isEmpty()) {
						return null;
					}

					Predicate[] p = new Predicate[list.size()];
					return cb.and(list.toArray(p));
				}
			};
		} else {
			return null;
		}
	}
}
