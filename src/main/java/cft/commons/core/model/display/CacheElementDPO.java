package cft.commons.core.model.display;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author daniel
 *
 */
public class CacheElementDPO {

	private String cacheKey;
	private String creationTime;
	private String expirationTime;
	private String lastAccessTime;
	private Integer liveSeconds;
	private Integer remainSeconds;
	private Long valueSize;
	private Long hitCount;

	private Integer startIndex;
	private Integer endIndex;
	private Integer rows; //pageSize
	private Integer page; //pageNumber

	private String sort; //sortColumn
	private String order; //sortOrder

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(String lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Integer getLiveSeconds() {
		return liveSeconds;
	}

	public void setLiveSeconds(Integer liveSeconds) {
		this.liveSeconds = liveSeconds;
	}

	public Integer getRemainSeconds() {
		return remainSeconds;
	}

	public void setRemainSeconds(Integer remainSeconds) {
		this.remainSeconds = remainSeconds;
	}

	public Long getValueSize() {
		return valueSize;
	}

	public void setValueSize(Long valueSize) {
		this.valueSize = valueSize;
	}

	public Long getHitCount() {
		return hitCount;
	}

	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
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
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE, true, true);
	}
}