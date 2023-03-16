package com.abc.model;

import java.sql.Date;

public class KhachHang {
	int IDKH;
	String HoTen;
	String SDT;
	String GioiTinh;
	Date NgaySinh;
	String DiaChi;
	String Email;
	String Username;
	
	
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getIDKH() {
		return IDKH;
	}
	public void setIDKH(int iDKH) {
		IDKH = iDKH;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	
}
