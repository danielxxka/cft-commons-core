package cft.commons.core.helper.pagination;

public class MySQLPaginationUtils {

	public static int getTotalPage(int totalRecords, int pageSize) {
		int totalPage = 0;

		if (totalRecords % pageSize == 0) {
			totalPage = totalRecords / pageSize;
		} else {
			totalPage = totalRecords / pageSize + 1;
		}

		return totalPage;

	}

	public static int getStartIndex(int pageSize, int pageNo) {
		int startIndex = 0;

		startIndex = pageSize * pageNo - pageSize;

		return startIndex;
	}

}
