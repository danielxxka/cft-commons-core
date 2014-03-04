package cft.commons.core.cache;

import org.apache.commons.lang.StringUtils;

/**
 * @author daniel
 *
 */
public class SimpleCacheKeyGeneratorImpl implements CacheKeyGenerator {

	/** 
	 * Create cache key should be an unique key in Cache instance.
	 * cache key includes:ClassName,MethodName,arguments.
	 */
	@Override
	public String getCacheKey(String cacheKeyPrefix, String className, String methodName, Object[] arguments) {

		StringBuffer strBuff = new StringBuffer();
		strBuff.append(cacheKeyPrefix).append(className).append(".").append(methodName);

		if ((arguments != null) && (arguments.length != 0)) {
			for (Object argument : arguments) {

				if (argument != null) {
					String argStr = formatArgument(argument);

					if (StringUtils.isNotBlank(argStr)) {
						strBuff.append(".").append(argStr);
					}
				}
			}
		}

		return strBuff.toString();
	}

	public String formatArgument(Object argument) {

		//argument format to String logic here
		String argStr = argument.toString();

		return argStr;
	}

}
