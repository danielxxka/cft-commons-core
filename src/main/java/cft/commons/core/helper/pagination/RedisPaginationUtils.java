package cft.commons.core.helper.pagination;

/**
 * 用于Redis List 分页
 * 
 * @author daniel
 * 
 */
public class RedisPaginationUtils {

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
		int startIndex = (pageNo - 1) * pageSize;
		return startIndex;
	}
	
	public static int getEndIndex(int pageSize, int pageNo) {
		int endIndex = pageNo * pageSize -1;;
		return endIndex;
	}

}
