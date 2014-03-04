package cft.commons.core.cache.ehcache;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import cft.commons.core.cache.CacheKeyGenerator;
import cft.commons.core.constant.Constants;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

/**
 * 使用Spring MethodInterceptor的AOP 形式嵌入Ehcache memory cache layer
 * 
 * @author daniel
 * 
 */
public class EhcacheCacheInterceptor implements MethodInterceptor {

	private static Logger logger = LoggerFactory.getLogger(EhcacheCacheInterceptor.class);
	private Cache cache;
	private String cacheKeyPrefix = "";
	private CacheKeyGenerator cacheKeyGenerator;

	/**   
	  * Retrieve the object from cache.   
	  * If no object in cache then invoke DAO to retrieve data.   
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		//Create cacheKey by className + methodName + arguments
		String classNameWithPackage = invocation.getThis().getClass().getName();
		String className = StringUtils.substringAfterLast(classNameWithPackage, ".");
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();

		final String cacheKey = cacheKeyGenerator.getCacheKey(cacheKeyPrefix, className, methodName, arguments);

		//Generate FunctionName and stopWatch for function enter log
		StringBuffer logBody = new StringBuffer();
		logBody.append(Constants.CACHE_LOG);

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		logBody.append(cacheKey);
		logger.debug(logBody + " ------> Start");

		//Trigger function business logic 
		Object result = null;
		Element element = null;

		synchronized (this) {

			element = cache.get(cacheKey);

			if (element != null) {

				stopWatch.stop();
				logger.debug(Constants.CACHE_LOG + "Get cached data by cacheKey:[{}] ", cacheKey);

			} else {

				logger.info(Constants.CACHE_LOG + "CACHE NOT EXIST,RETRIEVED DATA FROM DAO, cacheKey:[{}]", cacheKey);
				result = invocation.proceed();
				element = new Element(cacheKey, (Serializable) result);
				cache.put(element);

				stopWatch.stop();
				logger.info(Constants.CACHE_LOG + "PUT DATA INTO CACHE, cacheKey:[{}]", cacheKey);
			}
		}

		//Debug log for cache monitoring
		logger.debug(Constants.CACHE_LOG + "[{}].getMemoryStoreSize():{} ", cache.getName(), cache.getMemoryStoreSize());

		/**
		 * Warning:This cache.calculateInMemorySize() method can be very expensive to run.
		 */
		/*			logger.debug(MessageFormat.format(Constants.CACHE_LOG + "[{0}].calculateInMemorySize():{1} ",
							cache.getName(), cache.calculateInMemorySize()));*/

		//Generate Function end log
		logBody.append(" ------> End");
		logBody.append(" ,execution time: ");
		logBody.append(stopWatch.getTotalTimeMillis());
		logBody.append(" ms");
		logger.info(logBody.toString());

		return element.getObjectValue();
	}

	@PostConstruct
	public void checkCacheNotNull() throws Exception {
		Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public void setCacheKeyPrefix(String cacheKeyPrefix) {
		this.cacheKeyPrefix = cacheKeyPrefix;
	}

	public void setCacheKeyGenerator(CacheKeyGenerator cacheKeyGenerator) {
		this.cacheKeyGenerator = cacheKeyGenerator;
	}
	
}
