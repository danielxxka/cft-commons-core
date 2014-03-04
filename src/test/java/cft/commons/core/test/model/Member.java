package cft.commons.core.test.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import cft.commons.core.helper.jackson.JsonDateTimeDeserializer;
import cft.commons.core.helper.jackson.JsonDateTimeSerializer;
import cft.commons.core.helper.xstream.XStreamDateTimeConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author daniel
 *
 */

@JsonPropertyOrder(alphabetic= false)
@XStreamAlias("member")
public class Member implements Serializable {

	private static final long serialVersionUID = 9032712657850306330L;

	@XStreamAlias("name")
	private String name;

	@XStreamAlias("password")
	private String password;

	@JsonSerialize(using= JsonDateTimeSerializer.class,include=JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using= JsonDateTimeDeserializer.class)
	@XStreamConverter(value =  XStreamDateTimeConverter.class, strings={"yyyy-MM-dd'T'HH:mm:ss"})
	@XStreamAlias("birthday")
	private Date birthday;

	@XStreamAlias("salary")
	private Integer salary;

	@XStreamAlias("active")
	private Boolean active;

	public Member() {
		super();
	}

	public Member(String name, String password, Date birthday, Integer salary, Boolean active) {
		super();
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.salary = salary;
		this.active = active;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
	}

}
