package net.dasherz.wifiwolf.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * page related 工具类
 * 
 * @author sunheng
 */
public class PageInfo {
	private int totalPage;
	private int pageSize;
	private int currentPage;
	private List<Integer> pageList = new ArrayList<Integer>();

	public static int PAGE_SIZE = 10;
	public static int PAGE_LIST_SIZE = 10;

	public PageInfo(int pages, int currentPage) {
		this.currentPage = currentPage;

		int start = 1;
		if (currentPage >= PAGE_LIST_SIZE) {
			start = currentPage / PAGE_LIST_SIZE * PAGE_LIST_SIZE;
		}
		int num = start;
		while (!(num > pages || num > start + PAGE_LIST_SIZE)) {
			pageList.add(new Integer(num));
			++num;
		}
		this.totalPage = pages;
		this.pageSize = PAGE_SIZE;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
}
