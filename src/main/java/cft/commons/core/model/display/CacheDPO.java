package cft.commons.core.model.display;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author daniel
 *
 */
public class CacheDPO {

	private String name;
	private Integer numberOfElements;
	private Long valueSize;
	private Long liveSeconds;
	private String remark;

	private Integer startIndex;
	private Integer endIndex;
	private Integer rows; //pageSize
	private Integer page; //pageNumber

	private String sort; //sortColumn
	private String order; //sortOrder

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Long getValueSize() {
		return valueSize;
	}

	public void setValueSize(Long valueSize) {
		this.valueSize = valueSize;
	}

	public Long getLiveSeconds() {
		return liveSeconds;
	}

	public void setLiveSeconds(Long liveSeconds) {
		this.liveSeconds = liveSeconds;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE, true, true);
	}
}
