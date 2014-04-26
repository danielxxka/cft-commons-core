package cft.commons.core.helper.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 * @author daniel
 * 
 */
public class ApplicationContextHelper implements ApplicationContextAware {
	public static ApplicationContext appCtx;

	/** 
	 * IOC the ApplicationContext object   
	 * @param applicationContext ApplicationContext object. 
	 * @throws BeansException 
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appCtx = applicationContext;
	}

	/** 
	 * get spring bean from ApplicationContext
	 * @param beanName  
	 * @return bean object 
	 */
	public static Object getBean(String beanName) {
		return appCtx.getBean(beanName);
	}
}
