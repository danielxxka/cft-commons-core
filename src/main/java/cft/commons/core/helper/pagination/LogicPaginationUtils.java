package cft.commons.core.helper.pagination;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * 逻辑分页工具类
 * 
 * @author daniel
 * 
 */
public class LogicPaginationUtils {

	public static <T> List<T> getPage(List<T> fullList, int pageSize, int pageNo) {

		List<T> pageList = new ArrayList<T>();

		if (CollectionUtils.isNotEmpty(fullList)) {

			int startIndex = (pageNo - 1) * pageSize;
			if (startIndex > fullList.size()) {
				pageList = fullList;
			}
			int endIndex = startIndex + pageSize;
			if (endIndex >= fullList.size()) {
				endIndex = fullList.size();
			}

			pageList = fullList.subList(startIndex, endIndex);
		}

		return pageList;
	}
}