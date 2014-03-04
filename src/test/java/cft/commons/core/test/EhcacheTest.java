package cft.commons.core.test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;

public class EhcacheTest {
	
	@Test
	public void test(){
		
		CacheManager manager = CacheManager.create();  
		Cache cache = new Cache("test", 1, true, false, 5, 2);  
		manager.addCache(cache); 

		//加入元素
		    Element element = new Element("key1", "value1");  
		    cache.put(element);  

		//取得元素
		Element newElement = cache.get("key1");  
		
		
		System.out.println(newElement.getObjectValue().toString());
	}

}
