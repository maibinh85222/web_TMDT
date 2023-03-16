package com.abc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import com.abc.model.*;

public class DAO {
	Connection con = null;

	public DAO() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databasename=TBBEP2;username=sa;password=123456");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<DoanhSo> getDoanhSo() {
		ArrayList<DoanhSo> list = new ArrayList<DoanhSo>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MONTH(DonHang.NgayGiaoDich),SUM(TongTien) FROM DonHang GROUP BY MONTH(DonHang.NgayGiaoDich) Order by MONTH(DonHang.NgayGiaoDich) ASC");
			while (rs.next()) {
				DoanhSo ds = new DoanhSo();
				ds.setThang(rs.getInt(1));
				ds.setTien(rs.getInt(2));
				list.add(ds);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public SanPham getSPByID(int IDSP) {
		SanPham sp = new SanPham();
		try {
			PreparedStatement ps = con.prepareStatement(
					"UPDATE SanPham SET SoLuotXem =SoLuotXem+1 WHERE IDSP=? select * from SanPham  WHERE IDSP=?");
			ps.setInt(1, IDSP);
			ps.setInt(2, IDSP);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sp.setIDSP(rs.getInt(1));
				sp.setTenSP(rs.getString(2));
				sp.setGia(rs.getInt(3));
				sp.setGiaKM(rs.getInt(4));
				sp.setIDLoai(rs.getInt(5));
				sp.setBaoHanh(rs.getString(6));
				sp.setMoTa(rs.getString(7));
				sp.setSL(rs.getInt(8));
				sp.setURLHinh(rs.getString(9));
				sp.setSoLuotXem(rs.getInt(10));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sp;
	}

	public ArrayList<SanPham> getSanPham() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM SanPham");
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setIDSP(rs.getInt(1));
				sp.setTenSP(rs.getString(2));
				sp.setGia(rs.getInt(3));
				sp.setGiaKM(rs.getInt(4));
				sp.setIDLoai(rs.getInt(5));
				sp.setBaoHanh(rs.getString(6));
				sp.setMoTa(rs.getString(7));
				sp.setSL(rs.getInt(8));
				sp.setURLHinh(rs.getString(9));
				sp.setSoLuotXem(rs.getInt(10));
				list.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SanPham> getSanPhammoinhat() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM SanPham order by IDSP DESC");
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setIDSP(rs.getInt(1));
				sp.setTenSP(rs.getString(2));
				sp.setGia(rs.getInt(3));
				sp.setGiaKM(rs.getInt(4));
				sp.setIDLoai(rs.getInt(5));
				sp.setBaoHanh(rs.getString(6));
				sp.setMoTa(rs.getString(7));
				sp.setSL(rs.getInt(8));
				sp.setURLHinh(rs.getString(9));
				sp.setSoLuotXem(rs.getInt(10));
				list.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SanPham> getSanPhamview() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM SanPham order by SoLuotXem DESC");
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setIDSP(rs.getInt(1));
				sp.setTenSP(rs.getString(2));
				sp.setGia(rs.getInt(3));
				sp.setGiaKM(rs.getInt(4));
				sp.setIDLoai(rs.getInt(5));
				sp.setBaoHanh(rs.getString(6));
				sp.setMoTa(rs.getString(7));
				sp.setSL(rs.getInt(8));
				sp.setURLHinh(rs.getString(9));
				sp.setSoLuotXem(rs.getInt(10));
				list.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SanPham> getSanPhamsale() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM SanPham where GiaKM!=0");
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setIDSP(rs.getInt(1));
				sp.setTenSP(rs.getString(2));
				sp.setGia(rs.getInt(3));
				sp.setGiaKM(rs.getInt(4));
				sp.setIDLoai(rs.getInt(5));
				sp.setBaoHanh(rs.getString(6));
				sp.setMoTa(rs.getString(7));
				sp.setSL(rs.getInt(8));
				sp.setURLHinh(rs.getString(9));
				sp.setSoLuotXem(rs.getInt(10));
				list.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SanPham> getSanPhamngaunhien() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT TOP 4 * FROM SanPham ORDER BY NEWID()");
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setIDSP(rs.getInt(1));
				sp.setTenSP(rs.getString(2));
				sp.setGia(rs.getInt(3));
				sp.setGiaKM(rs.getInt(4));
				sp.setIDLoai(rs.getInt(5));
				sp.setBaoHanh(rs.getString(6));
				sp.setMoTa(rs.getString(7));
				sp.setSL(rs.getInt(8));
				sp.setURLHinh(rs.getString(9));
				sp.setSoLuotXem(rs.getInt(10));
				list.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<SanPham> getSanPhamtheoLoai(int IDLoai) {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM SanPham WHERE IDLoai =?");
			ps.setInt(1, IDLoai);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setIDSP(rs.getInt(1));
				sp.setTenSP(rs.getString(2));
				sp.setGia(rs.getInt(3));
				sp.setGiaKM(rs.getInt(4));
				sp.setIDLoai(rs.getInt(5));
				sp.setBaoHanh(rs.getString(6));
				sp.setMoTa(rs.getString(7));
				sp.setSL(rs.getInt(8));
				sp.setURLHinh(rs.getString(9));
				sp.setSoLuotXem(rs.getInt(10));
				list.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteSanPham(int IDSP) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM SanPham WHERE IDSP = ?");
			ps.setInt(1, IDSP);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertSanPham(SanPham sp) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO SanPham (TenSP,Gia,GiaKM, IDLoai, BaoHanh, MoTa, SL, URLHinh) VALUES (?,?,?,?,?,?,?,?)");

			ps.setString(1, sp.getTenSP());
			ps.setInt(2, sp.getGia());
			ps.setInt(3, sp.getGiaKM());
			ps.setInt(4, sp.getIDLoai());
			ps.setString(5, sp.getBaoHanh());
			ps.setString(6, sp.getMoTa());
			ps.setInt(7, sp.getSL());
			ps.setString(8, sp.getURLHinh());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSanPham(SanPham sp, int IDSP) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"UPDATE SanPham SET TenSP = ?,Gia = ?,GiaKM = ?,IDLoai = ?,BaoHanh =?,MoTa = ?,SL = ?,URLHinh = ?,SoLuotXem = ? WHERE IDSP = ?");

			ps.setString(1, sp.getTenSP());
			ps.setInt(2, sp.getGia());
			ps.setInt(3, sp.getGiaKM());
			ps.setInt(4, sp.getIDLoai());
			ps.setString(5, sp.getBaoHanh());
			ps.setString(6, sp.getMoTa());
			ps.setInt(7, sp.getSL());
			ps.setString(8, sp.getURLHinh());
			ps.setInt(9, sp.getSoLuotXem());
			ps.setInt(10, IDSP);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// ---------------------------------NHAN
	// VIEN--------------------------------------------------------
	public NhanVien getNvById(int IDNV) {
		NhanVien nv = new NhanVien();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien  WHERE IDNV = ?");
			ps.setInt(1, IDNV);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nv.setIDNV(rs.getInt(1));
				nv.setHoTen(rs.getString(2));
				nv.setNgaySinh(rs.getDate(3));
				nv.setCCCD(rs.getString(4));
				nv.setGioiTinh(rs.getString(5));
				nv.setDiaChi(rs.getString(6));
				nv.setUsername(rs.getString(7));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}

	public NhanVien getNvByIdtk(String Username) {
		NhanVien nv = new NhanVien();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE Username = ?");
			ps.setString(1, Username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nv.setIDNV(rs.getInt(1));
				nv.setHoTen(rs.getString(2));
				nv.setNgaySinh(rs.getDate(3));
				nv.setCCCD(rs.getString(4));
				nv.setGioiTinh(rs.getString(5));
				nv.setDiaChi(rs.getString(6));
				nv.setUsername(rs.getString(7));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}

	public ArrayList<NhanVien> getNhanVien() {
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM NhanVien");
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setIDNV(rs.getInt(1));
				nv.setHoTen(rs.getString(2));
				nv.setNgaySinh(rs.getDate(3));
				nv.setCCCD(rs.getString(4));
				nv.setGioiTinh(rs.getString(5));
				nv.setDiaChi(rs.getString(6));
				nv.setUsername(rs.getString(7));
				list.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteNhanVien(int IDNV) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM NHANVIEN WHERE IDNV = ?");
			ps.setInt(1, IDNV);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertNhanVien(NhanVien nv) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO NHANVIEN VALUES (?,?,?,?,?,?)");
			ps.setString(1, nv.getHoTen());
			ps.setDate(2, nv.getNgaySinh());
			ps.setString(3, nv.getCCCD());
			ps.setString(4, nv.getGioiTinh());
			ps.setString(5, nv.getDiaChi());
			ps.setString(6, nv.getUsername());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateNhanVien(NhanVien nv, int IDNV) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"UPDATE NHANVIEN SET HoTen = ?,NgaySinh = ?,SDT = ?,GioiTinh = ?, DiaChi=? WHERE IDNV = ?");

			ps.setString(1, nv.getHoTen());
			ps.setDate(2, nv.getNgaySinh());
			ps.setString(3, nv.getCCCD());
			ps.setString(4, nv.getGioiTinh());
			ps.setString(5, nv.getDiaChi());
			ps.setInt(6, IDNV);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean updateTKNhanVien(String Username, int IDNV) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE NHANVIEN SET Username =? WHERE IDNV = ?");
			ps.setString(1, Username);
			ps.setInt(2, IDNV);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// ---------------------------------KHACH
	// HANG--------------------------------------------------------
	public KhachHang getKhachHang_IDKH() {
		KhachHang kh = new KhachHang();
		try {
			PreparedStatement ps = con.prepareStatement("select TOP 1 * from KhachHang ORDER BY IDKH DESC");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				kh.setIDKH(rs.getInt(1));
				kh.setHoTen(rs.getString(2));
				kh.setSDT(rs.getString(3));
				kh.setGioiTinh(rs.getString(4));
				kh.setNgaySinh(rs.getDate(5));
				kh.setDiaChi(rs.getString(6));
				kh.setEmail(rs.getString(7));
				kh.setUsername(rs.getString(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}
	public KhachHang getKHById(int IDKH) {
		KhachHang kh = new KhachHang();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM KhachHang WHERE IDKH = ?");
			ps.setInt(1, IDKH);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				kh.setIDKH(rs.getInt(1));
				kh.setHoTen(rs.getString(2));
				kh.setSDT(rs.getString(3));
				kh.setGioiTinh(rs.getString(4));
				kh.setNgaySinh(rs.getDate(5));
				kh.setDiaChi(rs.getString(6));
				kh.setEmail(rs.getString(7));
				kh.setUsername(rs.getString(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}

	public KhachHang getKHByUsername(String Username) {
		KhachHang kh = new KhachHang();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM KhachHang WHERE Username = ?");
			ps.setString(1, Username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				kh.setIDKH(rs.getInt(1));
				kh.setHoTen(rs.getString(2));
				kh.setSDT(rs.getString(3));
				kh.setGioiTinh(rs.getString(4));
				kh.setNgaySinh(rs.getDate(5));
				kh.setDiaChi(rs.getString(6));
				kh.setEmail(rs.getString(7));
				kh.setUsername(rs.getString(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}

	public ArrayList<KhachHang> getKhachHang() {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM KhachHang ");
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setIDKH(rs.getInt(1));
				kh.setHoTen(rs.getString(2));
				kh.setSDT(rs.getString(3));
				kh.setGioiTinh(rs.getString(4));
				kh.setNgaySinh(rs.getDate(5));
				kh.setDiaChi(rs.getString(6));
				kh.setEmail(rs.getString(7));
				kh.setUsername(rs.getString(8));
				list.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteKhachHang(int IDKH) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM KhachHang WHERE IDKH = ?");
			ps.setInt(1, IDKH);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertKhachHang(KhachHang kh) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO KhachHang VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, kh.getHoTen());
			ps.setString(2, kh.getSDT());
			ps.setString(3, kh.getGioiTinh());
			ps.setDate(4, kh.getNgaySinh());
			ps.setString(5, kh.getDiaChi());
			ps.setString(6, kh.getEmail());
			ps.setString(7, kh.getUsername());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean dkiKhachHang(KhachHang kh) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO KhachHang (Email, Username) VALUES (?,?)");
			ps.setString(1, kh.getEmail());
			ps.setString(2, kh.getUsername());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean contructorKhachHang(KhachHang kh) {
		try {
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO KhachHang (HoTen, SDT, DiaChi, Username) VALUES (?,?,?,?)");
			ps.setString(1, kh.getHoTen());
			ps.setString(2, kh.getSDT());
			ps.setString(3, kh.getDiaChi());
			ps.setString(4, kh.getUsername());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateKhachHang(KhachHang kh, int IDKH) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"UPDATE KhachHang SET HoTen=?,SDT=?, GioiTinh =?, NgaySinh=?, DiaChi=?,Email = ?, Username=? WHERE IDKH = ?");

			ps.setString(1, kh.getHoTen());
			ps.setString(2, kh.getSDT());
			ps.setString(3, kh.getGioiTinh());
			ps.setDate(4, kh.getNgaySinh());
			ps.setString(5, kh.getDiaChi());
			ps.setString(6, kh.getEmail());
			ps.setString(7, kh.getUsername());
			ps.setInt(8, IDKH);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean updateTKKhachhang(String Username, int IDKH) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE KhachHang SET Username =? WHERE IDKH = ?");
			ps.setString(1, Username);
			ps.setInt(2, IDKH);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// --------------------------------- LOAI SAN PHAM
	// --------------------------------------------------------
	public Loai getLoaiById(int IDLoai) {
		Loai l = new Loai();
		try {
			PreparedStatement ps = con.prepareStatement("select * from Loai where IDLoai = ?");
			ps.setInt(1, IDLoai);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.setIDLoai(rs.getInt(1));
				l.setTenLoai(rs.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return l;
	}

	public ArrayList<Loai> getLoai() {
		ArrayList<Loai> list = new ArrayList<Loai>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Loai");
			while (rs.next()) {
				Loai l = new Loai();
				l.setIDLoai(rs.getInt(1));
				l.setTenLoai(rs.getString(2));
				list.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteLoai(int IDLoai) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Loai WHERE IDLoai = ?");
			ps.setInt(1, IDLoai);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertLoai(Loai l) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO Loai VALUES (?)");
			ps.setString(1, l.getTenLoai());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateLoai(Loai l, int IDLoai) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE Loai SET TenLoai = ? WHERE IDLoai = ?");
			ps.setString(1, l.getTenLoai());
			ps.setInt(2, IDLoai);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// ================================= GIO HANG
	// ========================================

	public ArrayList<GioHang> getGioHang() {
		ArrayList<GioHang> ghang = new ArrayList<GioHang>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT gh.Username,gh.IDSP, sp.TenSP, sp.URLHinh, gh.SL, sp.GiaKM, sp.SL FROM GioHang gh, SanPham sp WHERE gh.IDSP = sp.IDSP ");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GioHang gh = new GioHang();
				gh.setUsername(rs.getString(1));
				gh.setIDSP(rs.getInt(2));
				gh.setTenSP(rs.getString(3));
				gh.setURLHinh(rs.getString(4));
				gh.setSL(rs.getInt(5));
				gh.setGia(rs.getInt(6));
				gh.setSLton(rs.getInt(7));
				ghang.add(gh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ghang;
	}

	public ArrayList<GioHang> getGioHangU(String Username) {
		ArrayList<GioHang> ghang = new ArrayList<GioHang>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT gh.Username,gh.IDSP, sp.TenSP,sp.URLHinh, gh.SL, sp.GiaKM, sp.SL FROM GioHang gh, SanPham sp WHERE gh.IDSP = sp.IDSP AND Username=?");
			ps.setString(1, Username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GioHang gh = new GioHang();
				gh.setUsername(rs.getString(1));
				gh.setIDSP(rs.getInt(2));
				gh.setTenSP(rs.getString(3));
				gh.setURLHinh(rs.getString(4));
				gh.setSL(rs.getInt(5));
				gh.setGia(rs.getInt(6));
				gh.setSLton(rs.getInt(7));
				ghang.add(gh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ghang;
	}

	public boolean insertgiohang(GioHang gh) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO GioHang VALUES (?,?,?)");

			ps.setString(1, gh.getUsername());
			ps.setInt(2, gh.getIDSP());
			ps.setInt(3, gh.getSL());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteGioHang(String Username, int IDSP) {
		String sql = "DELETE FROM GioHang WHERE Username = ? AND IDSP =?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Username);
			ps.setInt(2, IDSP);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteGioHangcuaKH(String Username) {
		String sql = "DELETE FROM GioHang WHERE Username = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Username);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean updategiohang(GioHang gh, String Username, int IDSP) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE GioHang SET SL = ? WHERE Username = ? AND IDSP =?");
			ps.setInt(1, gh.getSL());
			ps.setString(2, Username);
			ps.setInt(3, IDSP);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// ===================================== DON
	// HANG===========================================
	public DonHang getDHByID(int IDDH) {
		DonHang dh = new DonHang();
		try {
			PreparedStatement ps = con.prepareStatement(
					"select dh.IDDH, dh.IDKH, kh.HoTen, dh.NgayGiaoDich, dh.TongTien, dh.HoTenNguoiNhan, dh.DiaChi, dh.SDT, dh.GhiChu, dh.PhuongThucTT, dh.TrangThai, IDNV from DonHang dh, KhachHang kh where dh.IDKH =kh.IDKH AND IDDH = ?");
			ps.setInt(1, IDDH);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dh.setIDDH(rs.getInt(1));
				dh.setIDKH(rs.getInt(2));
				dh.setHoTenKH(rs.getString(3));
				dh.setNgayGiaoDich(rs.getDate(4));
				dh.setTongTien(rs.getInt(5));
				dh.setHoTenNguoiNhan(rs.getString(6));
				dh.setDiaChi(rs.getString(7));
				dh.setSDT(rs.getString(8));
				dh.setGhiChu(rs.getString(9));
				dh.setPhuongThucTT(rs.getInt(10));
				dh.setTrangThai(rs.getInt(11));
				dh.setIDNV(rs.getInt(12));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dh;
	}

	public DonHang getDonHang_IDDH() {
		DonHang dh = new DonHang();
		try {
			PreparedStatement ps = con.prepareStatement("select TOP 1 * from DonHang ORDER BY IDDH DESC");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dh.setIDDH(rs.getInt(1));
				dh.setIDKH(rs.getInt(2));
				dh.setNgayGiaoDich(rs.getDate(3));
				dh.setTongTien(rs.getInt(4));
				dh.setHoTenNguoiNhan(rs.getString(5));
				dh.setDiaChi(rs.getString(6));
				dh.setSDT(rs.getString(7));
				dh.setGhiChu(rs.getString(8));
				dh.setPhuongThucTT(rs.getInt(9));
				dh.setTrangThai(rs.getInt(10));
				dh.setIDNV(rs.getInt(11));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dh;
	}

	public boolean insertchitietDH(ChiTietDH ct) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO ChiTietDH VALUES (?,?,?,?)");
			ps.setInt(1, ct.getIDDH());
			ps.setInt(2, ct.getIDSP());
			ps.setInt(3, ct.getSL());
			ps.setInt(4, ct.getGia());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<ChiTietDH> getdhById(int IDDH) {
		ArrayList<ChiTietDH> dh = new ArrayList<ChiTietDH>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT ct.IDDH, ct.IDSP, sp.TenSP, ct.SL, ct.Gia FROM ChiTietDH ct, SanPham sp WHERE ct.IDSP = sp.IDSP AND IDDH=? ");
			ps.setInt(1, IDDH);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ChiTietDH ct = new ChiTietDH();
				ct.setIDDH(rs.getInt(1));
				ct.setIDSP(rs.getInt(2));
				ct.setTenSP(rs.getString(3));
				ct.setSL(rs.getInt(4));
				ct.setGia(rs.getInt(5));
				dh.add(ct);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dh;
	}

	public ArrayList<DonHang> getDonHang() {
		ArrayList<DonHang> list = new ArrayList<DonHang>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select dh.IDDH, dh.IDKH, kh.HoTen, NgayGiaoDich, dh.TongTien, dh.HoTenNguoiNhan, dh.DiaChi, dh.SDT, GhiChu, PhuongThucTT,TrangThai, IDNV from DonHang dh, KhachHang kh where dh.IDKH =kh.IDKH ORDER BY NgayGiaoDich DESC");
			while (rs.next()) {
				DonHang dh = new DonHang();
				dh.setIDDH(rs.getInt(1));
				dh.setIDKH(rs.getInt(2));
				dh.setHoTenKH(rs.getString(3));
				dh.setNgayGiaoDich(rs.getDate(4));
				dh.setTongTien(rs.getInt(5));
				dh.setHoTenNguoiNhan(rs.getString(6));
				dh.setDiaChi(rs.getString(7));
				dh.setSDT(rs.getString(8));
				dh.setGhiChu(rs.getString(9));
				dh.setPhuongThucTT(rs.getInt(10));
				dh.setTrangThai(rs.getInt(11));
				dh.setIDNV(rs.getInt(12));
				list.add(dh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<DonHang> getDonHangIDKH(int IDKH) {
		ArrayList<DonHang> list = new ArrayList<DonHang>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"select dh.IDDH, dh.IDKH, kh.HoTen, dh.NgayGiaoDich, dh.TongTien, dh.HoTenNguoiNhan, dh.DiaChi, dh.SDT, dh.GhiChu, dh.PhuongThucTT, dh.TrangThai from DonHang dh, KhachHang kh where dh.IDKH =kh.IDKH AND dh.IDKH=? ORDER BY IDDH DESC");
			ps.setInt(1, IDKH);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DonHang dh = new DonHang();
				dh.setIDDH(rs.getInt(1));
				dh.setIDKH(rs.getInt(2));
				dh.setHoTenKH(rs.getString(3));
				dh.setNgayGiaoDich(rs.getDate(4));
				dh.setTongTien(rs.getInt(5));
				dh.setHoTenNguoiNhan(rs.getString(6));
				dh.setDiaChi(rs.getString(7));
				dh.setSDT(rs.getString(8));
				dh.setGhiChu(rs.getString(9));
				dh.setPhuongThucTT(rs.getInt(10));
				dh.setTrangThai(rs.getInt(11));
				list.add(dh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<DonHang> getDonHangIDNV(int IDNV) {
		ArrayList<DonHang> list = new ArrayList<DonHang>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"select dh.IDDH, dh.IDKH, kh.HoTen, dh.NgayGiaoDich, dh.TongTien, dh.HoTenNguoiNhan, dh.DiaChi, dh.SDT, dh.GhiChu, dh.PhuongThucTT, dh.TrangThai from DonHang dh, KhachHang kh where dh.IDKH =kh.IDKH AND dh.IDNV=? ORDER BY IDDH DESC");
			ps.setInt(1, IDNV);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DonHang dh = new DonHang();
				dh.setIDDH(rs.getInt(1));
				dh.setIDKH(rs.getInt(2));
				dh.setHoTenKH(rs.getString(3));
				dh.setNgayGiaoDich(rs.getDate(4));
				dh.setTongTien(rs.getInt(5));
				dh.setHoTenNguoiNhan(rs.getString(6));
				dh.setDiaChi(rs.getString(7));
				dh.setSDT(rs.getString(8));
				dh.setGhiChu(rs.getString(9));
				dh.setPhuongThucTT(rs.getInt(10));
				dh.setTrangThai(rs.getInt(11));
				list.add(dh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	
	public boolean insertdonhang(DonHang dh) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO DonHang (IDKH,TongTien, HoTenNguoiNhan,DiaChi, SDT, GhiChu, PhuongThucTT, TrangThai) VALUES (?,?,?,?,?,?,?,?)");
			ps.setInt(1, dh.getIDKH());
			ps.setInt(2, dh.getTongTien());
			ps.setString(3, dh.getHoTenNguoiNhan());
			ps.setString(4, dh.getDiaChi());
			ps.setString(5, dh.getSDT());
			ps.setString(6, dh.getGhiChu());
			ps.setInt(7, dh.getPhuongThucTT());
			ps.setInt(8, dh.getTrangThai());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertdonhangByNhanVien(DonHang dh) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO DonHang (IDKH,TongTien, HoTenNguoiNhan,DiaChi, SDT, GhiChu, PhuongThucTT, TrangThai, IDNV) VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, dh.getIDKH());
			ps.setInt(2, dh.getTongTien());
			ps.setString(3, dh.getHoTenNguoiNhan());
			ps.setString(4, dh.getDiaChi());
			ps.setString(5, dh.getSDT());
			ps.setString(6, dh.getGhiChu());
			ps.setInt(7, dh.getPhuongThucTT());
			ps.setInt(8, dh.getTrangThai());
			ps.setInt(9, dh.getIDNV());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteDonHang(int IDDH) {
		String sql = "DELETE FROM ChiTietDH WHERE IDDH = ? DELETE FROM DonHang WHERE IDDH = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, IDDH);
			ps.setInt(2, IDDH);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatedonhang(DonHang dh, int IDDH) {
		try {
			PreparedStatement ps = con
					.prepareStatement("UPDATE DonHang SET GhiChu=?,TrangThai = ?, IDNV=? WHERE IDDH = ?");
			// ps.setInt(1, dh.getTongTien());
			ps.setString(1, dh.getGhiChu());
			ps.setInt(2, dh.getTrangThai());
			ps.setInt(3, dh.getIDNV());
			ps.setInt(4, IDDH);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// ================================TAI KHOAN -
	// login=========================================
	
	public TaiKhoan getTKByUsername(String Username) {
		TaiKhoan tk = new TaiKhoan();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM TaiKhoan WHERE Username = ?");
			ps.setString(1, Username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tk.setIDTK(rs.getInt(1));
				tk.setUsername(rs.getString(2));
				tk.setPassword(rs.getString(3));
				tk.setIDQuyen(rs.getInt(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tk;
	}
	public ArrayList<TaiKhoan> getTaiKhoan() {
		ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TaiKhoan");
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan();
				tk.setIDTK(rs.getInt(1));
				tk.setUsername(rs.getString(2));
				tk.setPassword(rs.getString(3));
				tk.setIDQuyen(rs.getInt(4));
				list.add(tk);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public boolean addTaiKhoan(TaiKhoan tk) {
		try {
			PreparedStatement ps = con.prepareStatement("insert into TaiKhoan values (?,?,?)");
			ps.setString(1, tk.getUsername());
			ps.setString(2, tk.getPassword());
			ps.setInt(3, tk.getIDQuyen());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean deletetaikhoan(int IDTK) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM TaiKhoan WHERE IDTK = ?");
			ps.setInt(1, IDTK);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean deletetaikhoanU(String Username) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM TaiKhoan WHERE Username = ?");
			ps.setString(1, Username);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	// ============== QUYEN =============
	public Quyen getQuyenById(int IDQuyen) {
		Quyen l = new Quyen();
		try {
			PreparedStatement ps = con.prepareStatement("select * from Quyen where IDQuyen = ?");
			ps.setInt(1, IDQuyen);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.setIDQuyen(rs.getInt(1));
				l.setTenQuyen(rs.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return l;
	}

	public ArrayList<Quyen> getQuyen() {
		ArrayList<Quyen> list = new ArrayList<Quyen>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Quyen");
			while (rs.next()) {
				Quyen l = new Quyen();
				l.setIDQuyen(rs.getInt(1));
				l.setTenQuyen(rs.getString(2));
				list.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteQuyen(int IDQuyen) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Quyen WHERE IDQuyen = ?");
			ps.setInt(1, IDQuyen);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertQuyen(Quyen l) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO Quyen VALUES (?)");
			ps.setString(1, l.getTenQuyen());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateQuyen(Quyen l, int IDQuyen) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE Quyen SET TenQuyen = ? WHERE IDQuyen = ?");
			ps.setString(1, l.getTenQuyen());
			ps.setInt(2, IDQuyen);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// ======================== ĐÁNH GIÁ ================
	public ArrayList<DanhGia> getDanhGiaByIDSP(int IDSP) {
		ArrayList<DanhGia> list = new ArrayList<DanhGia>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT dg.IDKH, kh.HoTen, dg.IDSP, dg.NgayDG, dg.NoiDung, dg.Diem FROM DanhGia dg, KhachHang kh WHERE dg.IDKH = kh.IDKH AND dg.IDSP=?");
			ps.setInt(1, IDSP);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DanhGia dg = new DanhGia();
				dg.setIDKH(rs.getInt(1));
				dg.setTenKH(rs.getString(2));
				dg.setIDSP(rs.getInt(3));
				dg.setNgayDG(rs.getDate(4));
				dg.setNoiDung(rs.getString(5));
				dg.setDiem(rs.getInt(6));
				list.add(dg);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public boolean insertDanhGia(DanhGia dg) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO DanhGia VALUES (?,?,?,?,?)");
			ps.setInt(1, dg.getIDKH());
			ps.setInt(2, dg.getIDSP());
			ps.setDate(3, dg.getNgayDG());
			ps.setString(4, dg.getNoiDung());
			ps.setInt(5, dg.getDiem());
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteDanhGia(int IDKH, int IDSP) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM DanhGia WHERE IDKH = ? AND IDSP=?");
			ps.setInt(1, IDKH);
			ps.setInt(2, IDSP);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
