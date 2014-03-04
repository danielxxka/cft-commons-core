package cft.commons.core.test;

import java.util.Date;

import cft.commons.core.helper.dozer.BeanMapper;
import cft.commons.core.test.model.Member;
import cft.commons.core.test.model.Member2;

import org.junit.Test;

/**
 * @author daniel
 *
 */
public class BeanMapperTest {
	
	@Test
	public void testBeanCopy(){
		
		Member src = new Member("Jimmy", "abced", new Date(), 5000, true);
		Member2 dist = new Member2();
	
		BeanMapper.copy(src, dist);
		System.out.println("dist = " + dist);
	}
	
	
	@Test
	public void testBeanMap(){
		
		Member src = new Member("Jimmy", "abced", new Date(), 5000, true);
		Member2 dist = BeanMapper.map(src, Member2.class);
		
		System.out.println("dist = " + dist);
	}

}
