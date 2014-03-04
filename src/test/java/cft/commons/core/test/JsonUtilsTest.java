package cft.commons.core.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cft.commons.core.helper.jackson.JsonUtils;
import cft.commons.core.test.model.Member;

/**
 * @author daniel
 *
 */
public class JsonUtilsTest {

	@Test
	public void testToJSON() {

		Member member = new Member("Jimmy", "abced", null, 5000, true);
		Member member2 = new Member("Mike", "abced", new Date(), 5000, true);
		List<Member> list = new ArrayList<Member>();
		list.add(member);
		list.add(member2);

		String json = JsonUtils.toJSON(member);
		System.out.println(json);

		String json2 = JsonUtils.toJSON(list);
		System.out.println(json2);
	}

	@Test
	public void testFormString() {

		String json = "{\"name\":\"Jimmy\",\"password\":\"abced\",\"birthday\":\"2013-08-06 19:08:23\",\"salary\":5000,\"active\":true}";

		String list = "[{\"name\":\"Jimmy\",\"password\":\"abced\",\"birthday\":\"2013-08-28 16:42:07\",\"salary\":5000,\"active\":true},"
				+ "{\"name\":\"Mike\",\"password\":\"abced\",\"birthday\":\"2013-08-28 16:42:07\",\"salary\":5000,\"active\":true}]";

		Member member = JsonUtils.fromJSON(json, Member.class);
		System.out.println(member);

		List<Member> mList = JsonUtils.fromJSON(list, List.class);
		System.out.println(mList);
	}

}
