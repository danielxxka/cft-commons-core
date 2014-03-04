package cft.commons.core.cache;

public interface CacheKeyGenerator {

	String getCacheKey(String cacheKeyPrefix, String className, String methodName, Object[] arguments);

}
