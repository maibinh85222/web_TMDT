package com.abc.model;

import java.sql.Date;

public class DanhGia {
	int IDKH;
	String TenKH;
	int IDSP;
	Date NgayDG;
	String NoiDung;
	int Diem;
	public int getIDKH() {
		return IDKH;
	}
	public void setIDKH(int iDKH) {
		IDKH = iDKH;
	} 
	public String getTenKH() {
		return TenKH;
	}
	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}
	public int getIDSP() {
		return IDSP;
	}
	public void setIDSP(int iDSP) {
		IDSP = iDSP;
	}
	public Date getNgayDG() {
		return NgayDG;
	}
	public void setNgayDG(Date ngayDG) {
		NgayDG = ngayDG;
	}
	public String getNoiDung() {
		return NoiDung;
	}
	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	public int getDiem() {
		return Diem;
	}
	public void setDiem(int diem) {
		Diem = diem;
	}
	
	
}
