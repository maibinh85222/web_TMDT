package com.abc.model;

public class TaiKhoan {
	int IDTK;
	String Username;
	String Password;
	int IDQuyen;
	
	
	public int getIDQuyen() {
		return IDQuyen;
	}
	public void setIDQuyen(int iDQuyen) {
		IDQuyen = iDQuyen;
	}
	
	public int getIDTK() {
		return IDTK;
	}
	public void setIDTK(int iDTK) {
		IDTK = iDTK;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
