package com.neuedu.maplestory.login;

import java.io.Serializable;
import java.util.Date;

public class User implements Comparable<User>,Serializable{
	/**
	 * 类的实例持久化保存，序列化就是保存，反序列化就是读取。
	 */
	private static final long serialVersionUID = -6756021611708623827L;
	/**
	 * 用户id
	 */
	public String id;
	/**
	 * 用户名
	 */
	public String username;
	/**
	 * 用户密码
	 */
	public String pwd;
	/**
	 * 用户的分
	 */
	public Integer grade;
	/**
	 * 用户注册时间
	 */
	public Date registerTime;
	/**
	 * 用户得到高分的时间
	 */
    public Date highestRecordTime;
	/**
	 * 用户的无参构造
	 */
    public User() {
		
	}
    /**
     * 用户的有参构造
     * @param id
     * @param username
     * @param pwd
     * @param grade
     * @param registerTime
     * @param highestRecordTime
     */
	public User(String id, String username, String pwd, Date registerTime, Date highestRecordTime, Integer grade) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;		
		this.registerTime = registerTime;
		this.highestRecordTime = highestRecordTime;
		this.grade = grade;
	}
    /**
     * 重写hashcode方法
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((highestRecordTime == null) ? 0 : highestRecordTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((registerTime == null) ? 0 : registerTime.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
   /**
    * 重写equals方法
    */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (highestRecordTime == null) {
			if (other.highestRecordTime != null)
				return false;
		} else if (!highestRecordTime.equals(other.highestRecordTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (registerTime == null) {
			if (other.registerTime != null)
				return false;
		} else if (!registerTime.equals(other.registerTime))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
    /**
     * 重写tostring方法
     */
	@Override
	public String toString() {
		return "User [用户id=" + id + ", 用户名=" + username + ", 用户密码=" + pwd + ", 用户分数=" + grade + ", 用户注册时间="
				+ registerTime + ", 用户得最高分的时间=" + highestRecordTime + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getHighestRecordTime() {
		return highestRecordTime;
	}

	public void setHighestRecordTime(Date highestRecordTime) {
		this.highestRecordTime = highestRecordTime;
	}
    /**
     * 对用户信息排序，根据用户得到的分数进行排序
     * 
     */
	@Override
	public int compareTo(User user) {
		if (user.grade>this.grade) {
			return 1;
		}else if (user.grade==this.grade) {
			return 0;
		}else {
			return -1;
		} 
		
	}

	
    
    
}
