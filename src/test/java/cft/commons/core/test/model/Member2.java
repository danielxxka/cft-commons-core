package cft.commons.core.test.model;

import java.io.Serializable;
import java.util.Date;

import cft.commons.core.helper.xstream.XStreamDateTimeConverter;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author daniel
 *
 */

@XStreamAlias("member")
public class Member2 implements Serializable {

	private static final long serialVersionUID = 9032712657850306330L;

	@XStreamAlias("name")
	private String name;

	@XStreamAlias("password")
	private String password;

	@XStreamConverter(value =  XStreamDateTimeConverter.class, strings={"yyyy-MM-dd'T'HH:mm:ss"})
	@XStreamAlias("birthday")
	private Date birthday;

	@XStreamAlias("salary")
	private Integer salary;

	@XStreamAlias("active")
	private Boolean active;

	public Member2() {
		super();
	}

	public Member2(String name, String password, Date birthday, Integer salary, Boolean active) {
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
