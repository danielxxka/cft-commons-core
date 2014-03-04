package cft.commons.core.cache.ehcache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import cft.commons.core.constant.Constants;
import cft.commons.core.model.display.CacheDPO;
import cft.commons.core.model.display.CacheElementDPO;

/**
 * @author daniel
 *
 */
public class EhcacheMonitoringManager {

	private CacheManager cacheManager;

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	private static final Logger logger = Logger.getLogger(EhcacheMonitoringManager.class);

	/*
	 * Get the cache instance by cacheName from cacheManager.
	 */
	public Cache getCache(String cacheName) {
		Cache cache = null;
		if (StringUtils.isNotBlank(cacheName)) {

			cache = cacheManager.getCache(cacheName);
		}
		return cache;
	}

	/*
	 * Get All Cache instances from cacheManager for cache monitoring list
	 */
	public List<CacheDPO> getCacheRegionList() throws IllegalStateException, CacheException {

		String[] cacheNameArr = cacheManager.getCacheNames();
		List<String> cacheNameList = new ArrayList<String>();

		if (ArrayUtils.isNotEmpty(cacheNameArr)) {
			cacheNameList = new ArrayList<String>(Arrays.asList(cacheNameArr));
			if (logger.isDebugEnabled()) {
				logger.debug(Constants.CACHE_LOG + "Get Cache Names: " + cacheNameList.toString());
			}
		}

		List<CacheDPO> cacheDisplayList = new ArrayList<CacheDPO>();
		for (String cacheName : cacheNameList) {
			Cache cache = (Cache) cacheManager.getCache(cacheName);

			CacheDPO cacheDPO = new CacheDPO();
			if (cache != null) {
				cacheDPO.setName(cache.getName());
				cacheDPO.setNumberOfElements(cache.getSize());
				cacheDPO.setLiveSeconds(cache.getCacheConfiguration().getTimeToLiveSeconds());
				//cacheDPO.setStatus(cache.getStatus().toString());

				/**
				 * Warning:This method can be very expensive to run.
				 */
				cacheDPO.setValueSize((cache.calculateInMemorySize()));

			}
			cacheDisplayList.add(cacheDPO);
		}

		return cacheDisplayList;
	}

	/*
	 * Flush cache instance by the cacheName.
	 */
	public int flushCacheRegion(String cacheName) {

		int flag = 0;

		if (StringUtils.isNotBlank(cacheName)) {
			Cache cache = getCache(cacheName);
			if (cache != null && cache.getSize() > 0) {

				int cacheSize = cache.getSize();
				cache.flush();
				flag = cacheSize;
				logger.info(Constants.CACHE_LOG + cacheName + " has been flushed.");
			}
		}

		return flag;
	}

	/*
	 *Get all elements by cacheName. 
	 */
	@SuppressWarnings({ "unchecked" })
	public List<CacheElementDPO> getCacheElementList(String cacheName) {
		List<String> cacheKeys = new ArrayList<String>();
		Cache cache = getCache(cacheName);
		if (cache != null) {
			cacheKeys = cache.getKeys();
		}

		List<CacheElementDPO> elementList = new ArrayList<CacheElementDPO>();
		for (String cacheKey : cacheKeys) {

			Element element = cache.getQuiet(cacheKey);

			CacheElementDPO ceDisplay = new CacheElementDPO();

			if (element != null) {
				ceDisplay.setCacheKey(cacheKey);
				ceDisplay.setHitCount(element.getHitCount());
				ceDisplay.setLiveSeconds(element.getTimeToLive());

				Date creationtime = new Date(element.getCreationTime());
				Date expirationTime = new Date(element.getCreationTime() + ceDisplay.getLiveSeconds() * 1000);

				ceDisplay.setCreationTime(DateFormatUtils.format(creationtime, Constants.C_DATETIME_PATTERN_DEFAULT));
				ceDisplay.setExpirationTime(DateFormatUtils.format(expirationTime, Constants.C_DATETIME_PATTERN_DEFAULT));

				Long diffTime = (expirationTime.getTime() - new Date().getTime());
				ceDisplay.setRemainSeconds(diffTime.intValue() / 1000);

				ceDisplay.setValueSize(element.getSerializedSize());

				elementList.add(ceDisplay);

			}
		}
		return elementList;
	}

	/*
	 * Flush cache instance by the cacheName.
	 */
	public void clearCache(String cacheName) {

		if (StringUtils.isNotBlank(cacheName)) {
			Cache cache = getCache(cacheName);
			if (cache != null) {
				cache.flush();
				logger.info(Constants.CACHE_LOG + cacheName + " has been flushed.");
			}
		}
	}

	/*
	 * Flush all cache instance from cacheManager.
	 */
	public void clearAllCache() {

		if (cacheManager != null) {
			cacheManager.clearAll();
		}
		logger.info(Constants.CACHE_LOG + " All Cache has been flushed.");
	}

	/*
	 * Remove element from cache by cacheKey.
	 */
	public int removeCacheElement(String cacheName, String cacheKey) {

		int flag = 0;
		Cache cache = getCache(cacheName);
		if (cache != null) {
			cache.remove(cacheKey);
			flag = 1;
		}

		return flag;
	}

	/*
	 * Check Cache Name is exists
	 */
	public boolean cacheExists(String cacheName) {
		return cacheManager.cacheExists(cacheName);
	}

	/*
	 * Update cache time by cache name
	 */
	public void updateCacheTime(String cacheName, Long cacheTime) throws Exception {
		Cache cache = getCache(cacheName);
		cache.getCacheConfiguration().setTimeToLiveSeconds(cacheTime);
	}

	public void addElement(String cacheName, String cacheKey, Object object) {

		Element element = new Element(cacheKey, (Serializable) object);
		Cache cache = getCache(cacheName);

		if (cache != null) {
			cache.put(element);
			logger.info(Constants.CACHE_LOG + "Put element into cache. cacheKey = " + cacheKey);
		} else {
			logger.info(Constants.CACHE_LOG + " Can not found cahe:" + cacheName);
		}
	}

}
