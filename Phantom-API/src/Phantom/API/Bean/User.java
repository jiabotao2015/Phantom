package Phantom.API.Bean;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 6646617326708919111L;

	private int userId;
	
	private String userName;
	
	private String passWord;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + "]";
	}
	
	

}
