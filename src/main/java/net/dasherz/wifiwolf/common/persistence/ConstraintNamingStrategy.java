package net.dasherz.wifiwolf.common.persistence;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.internal.util.StringHelper;

public class ConstraintNamingStrategy extends ImprovedNamingStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String TABLE_PREFIX = "T_";

	@Override
	public String classToTableName(String className) {
		return addUnderscores(TABLE_PREFIX + StringHelper.unqualify(className));
	}

}
