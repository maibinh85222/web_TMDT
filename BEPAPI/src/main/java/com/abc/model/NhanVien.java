package com.abc.model;

import java.sql.Date;

public class NhanVien {
	int IDNV;
	String HoTen;
	Date NgaySinh;
	String CCCD;
	String GioiTinh;
	String DiaChi;
	String Username;
	
	
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public int getIDNV() {
		return IDNV;
	}
	public void setIDNV(int iDNV) {
		IDNV = iDNV;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	
}
