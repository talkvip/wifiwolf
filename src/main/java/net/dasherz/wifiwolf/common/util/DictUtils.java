package net.dasherz.wifiwolf.common.util;

import java.util.List;
import java.util.Map;

import net.dasherz.wifiwolf.domain.Dict;
import net.dasherz.wifiwolf.repository.DictRepository;

import org.springframework.data.domain.Sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DictUtils {
	public static final String CACHE_DICT_MAP = "dictMap";
	private static DictRepository dictRepository;

	public static void setDictRepository(DictRepository dictRepository) {
		DictUtils.dictRepository = dictRepository;
	}

	public static String getName(String groupName, String code,
			String defaultValue) {
		Dict dict = dictRepository.findByGroupNameAndCode(groupName, code);
		if (dict != null) {
			return dict.getName();
		}
		return defaultValue;
	}

	public static String getCode(String groupName, String name,
			String defaultValue) {
		Dict dict = dictRepository.findByGroupNameAndName(groupName, name);
		if (dict != null) {
			return dict.getName();
		}
		return defaultValue;
	}

	public static List<Dict> getDictList(String groupName) {
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils
				.get(CACHE_DICT_MAP);
		if (dictMap == null) {
			dictMap = Maps.newHashMap();
			for (Dict dict : dictRepository.findAll(new Sort("orderNum"))) {
				List<Dict> dictList = dictMap.get(dict.getGroupName());
				if (dictList != null) {
					dictList.add(dict);
				} else {
					dictMap.put(dict.getGroupName(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}

		List<Dict> dictList = dictMap.get(groupName);
		if (dictList == null) {
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
}
