package com.abc.api;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.abc.dao.*;
import com.abc.model.*;

@RestController
public class BepAPI {
	@GetMapping("/doanhso")
	public ArrayList<DoanhSo> getDoanhSo() {
		return new DAO().getDoanhSo();
	}
	
	@GetMapping("/sanpham")
	public ArrayList<SanPham> getSanPham() {
		return new DAO().getSanPham();
	}

	@GetMapping("/sanphamngaunhien")
	public ArrayList<SanPham> getSanPhamngaunhien() {
		return new DAO().getSanPhamngaunhien();
	}

	@GetMapping("/sanphammoinhat")
	public ArrayList<SanPham> getSanPhammoinhat() {
		return new DAO().getSanPhammoinhat();
	}

	@GetMapping("/sanphamview")
	public ArrayList<SanPham> getSanPhamview() {
		return new DAO().getSanPhamview();
	}

	@GetMapping("/sanphamsale")
	public ArrayList<SanPham> getSanPhamsale() {
		return new DAO().getSanPhamsale();
	}

	@GetMapping("/dssanpham/{IDLoai}")
	public ArrayList<SanPham> getSanPhamtheoLoai(@PathVariable("IDLoai") int IDLoai) {
		return new DAO().getSanPhamtheoLoai(IDLoai);
	}

	@PostMapping("/sanpham")
	public boolean insertSanPham(@Valid @RequestBody SanPham sp) {
		return new DAO().insertSanPham(sp);
	}

	@DeleteMapping("/sanpham/{IDSP}")
	public boolean deleteSanPham(@PathVariable("IDSP") int IDSP) {
		return new DAO().deleteSanPham(IDSP);
	}

	@PutMapping("/sanpham/{IDSP}")
	public boolean updateSanPham(@Valid @RequestBody SanPham sp, @PathVariable("IDSP") int IDSP) {
		return new DAO().updateSanPham(sp, IDSP);
	}

	@GetMapping("/sanpham/{IDSP}")
	public SanPham getSPByID(@PathVariable("IDSP") int IDSP) {
		return new DAO().getSPByID(IDSP);
	}

	// ===================== LOAI ================================
	@GetMapping("/loai")
	public ArrayList<Loai> getLoai() {
		return new DAO().getLoai();
	}

	@PostMapping("/loai")
	public boolean insertLoai(@Valid @RequestBody Loai l) {
		return new DAO().insertLoai(l);
	}

	@DeleteMapping("/loai/{IDLoai}")
	public boolean deleteLoai(@PathVariable("IDLoai") int IDLoai) {
		return new DAO().deleteLoai(IDLoai);
	}

	@PutMapping("/loai/{IDLoai}")
	public boolean updateLoai(@Valid @RequestBody Loai l, @PathVariable("IDLoai") int IDLoai) {
		return new DAO().updateLoai(l, IDLoai);
	}

	@GetMapping("/loai/{IDLoai}")
	public Loai getLoaiById(@PathVariable("IDLoai") int IDLoai) {
		return new DAO().getLoaiById(IDLoai);
	}

	// ----------------NHAN VIEN --------------------------------
	@GetMapping("/nhanvien")
	public ArrayList<NhanVien> getNhanVien() {
		return new DAO().getNhanVien();
	}

	@PostMapping("/nhanvien")
	public boolean insertNV(@Valid @RequestBody NhanVien nv) {
		return new DAO().insertNhanVien(nv);
	}

	@DeleteMapping("/nhanvien/{IDNV}")
	public boolean deleteNV(@PathVariable("IDNV") int IDNV) {
		return new DAO().deleteNhanVien(IDNV);
	}

	@PutMapping("/nhanvien/{IDNV}")
	public boolean updateNV(@Valid @RequestBody NhanVien nv, @PathVariable("IDNV") int IDNV) {
		return new DAO().updateNhanVien(nv, IDNV);
	}

	@PutMapping("/tknhanvien/{IDNV}")
	public boolean updateTKNV(@Valid @RequestBody String Username, @PathVariable("IDNV") int IDNV) {
		return new DAO().updateTKNhanVien(Username, IDNV);
	}

	@GetMapping("/nhanvien/{IDNV}")
	public NhanVien getNvById(@PathVariable("IDNV") int IDNV) {
		return new DAO().getNvById(IDNV);
	}

	@GetMapping("/nhanvientk/{Username}")
	public NhanVien getNvByIdtk(@PathVariable("Username") String Username) {
		return new DAO().getNvByIdtk(Username);
	}

	// ----------------KHACH HANG --------------------------------
	@GetMapping("/khachhang")
	public ArrayList<KhachHang> getKhachHang() {
		return new DAO().getKhachHang();
	}

	@PostMapping("/khachhang")
	public boolean insertKhachHang(@Valid @RequestBody KhachHang kh) {
		return new DAO().insertKhachHang(kh);
	}
	
	@PostMapping("/newkhachhang")
	public boolean contructorKhachHang(@Valid @RequestBody KhachHang kh) {
		return new DAO().contructorKhachHang(kh);
	}
	
	@PostMapping("/dkikhachhang")
	public boolean dkiKhachHang(@Valid @RequestBody KhachHang kh) {
		return new DAO().dkiKhachHang(kh);
	}

	@DeleteMapping("/khachhang/{IDKH}")
	public boolean deleteKhachHang(@PathVariable("IDKH") int IDKH) {
		return new DAO().deleteKhachHang(IDKH);
	}

	@PutMapping("/khachhang/{IDKH}")
	public boolean updateKhachHang(@Valid @RequestBody KhachHang kh, @PathVariable("IDKH") int IDKH) {
		return new DAO().updateKhachHang(kh, IDKH);
	}

	@PutMapping("/tkkhachhang/{IDKH}")
	public boolean updateTKKH(@Valid @RequestBody String Username, @PathVariable("IDKH") int IDKH) {
		return new DAO().updateTKKhachhang(Username, IDKH);
	}

	@GetMapping("/khachhang/{IDKH}")
	public KhachHang getKHById(@PathVariable("IDKH") int IDKH) {
		return new DAO().getKHById(IDKH);
	}

	@GetMapping("/khachhangtk/{Username}")
	public KhachHang getKHByUsername(@PathVariable("Username") String Username) {
		return new DAO().getKHByUsername(Username);
	}
	
	@GetMapping("/getKhachHang_IDKH")
	public KhachHang getKhachHang_IDKH() {
		return new DAO().getKhachHang_IDKH();
	}

	// ======================= GIO HANG ===========================
	@GetMapping("/giohang")
	public ArrayList<GioHang> getGioHang() {
		return new DAO().getGioHang();
	}

	@PostMapping("/giohang")
	public boolean insertgiohang(@Valid @RequestBody GioHang gh) {
		return new DAO().insertgiohang(gh);
	}

	@DeleteMapping("/xoagiohang/{Username}/{IDSP}")
	public boolean deleteGioHang(@PathVariable("Username") String Username, @PathVariable("IDSP") int IDSP) {
		return new DAO().deleteGioHang(Username, IDSP);
	}
	
	@DeleteMapping("/xoagiohang/{Username}")
	public boolean deleteGioHangcuaKH(@PathVariable("Username") String Username) {
		return new DAO().deleteGioHangcuaKH(Username);
	}

	@PutMapping("/giohang/{Username}/{IDSP}")
	public boolean updategh(@Valid @RequestBody GioHang gh, @PathVariable("Username") String Username,
			@PathVariable("IDSP") int IDSP) {
		return new DAO().updategiohang(gh, Username, IDSP);
	}

	@GetMapping("/giohang/{Username}")
	public ArrayList<GioHang> getGHByID(@PathVariable("Username") String Username) {
		return new DAO().getGioHangU(Username);
	}

	// --------------------------DON HANG------------------------------------------
	@GetMapping("/donhang")
	public ArrayList<DonHang> getDonHang() {
		return new DAO().getDonHang();
	}

	@PostMapping("/donhang")
	public boolean insertdonhang(@Valid @RequestBody DonHang dh) {
		return new DAO().insertdonhang(dh);
	}
	
	@PostMapping("/donhangbynhanvien")
	public boolean insertdonhangByNhanVien(@Valid @RequestBody DonHang dh) {
		return new DAO().insertdonhangByNhanVien(dh);
	}

	@DeleteMapping("/donhang/{IDDH}")
	public boolean deleteDonHang(@PathVariable("IDDH") int IDDH) {
		return new DAO().deleteDonHang(IDDH);
	}

	@PutMapping("/donhang/{IDDH}")
	public boolean updatedh(@Valid @RequestBody DonHang dh, @PathVariable("IDDH") int IDDH) {
		return new DAO().updatedonhang(dh, IDDH);
	}

	@GetMapping("/donhang/{IDDH}")
	public DonHang getDHByID(@PathVariable("IDDH") int IDDH) {
		return new DAO().getDHByID(IDDH);
	}
	
	@GetMapping("/getDonHang_IDDH")
	public DonHang getDonHang_IDDH() {
		return new DAO().getDonHang_IDDH();
	}

	@PostMapping("/themchitietdh")
	public boolean insertchitietDH(@Valid @RequestBody ChiTietDH ct) {
		return new DAO().insertchitietDH(ct);
	}
	
	@GetMapping("/chitietdh/{IDDH}")
	public ArrayList<ChiTietDH> getdhById(@PathVariable("IDDH") int IDDH) {
		return new DAO().getdhById(IDDH);
	}

	@GetMapping("/donhangkh/{IDKH}")
	public ArrayList<DonHang> getDonHangIDKH(@PathVariable("IDKH") int IDKH) {
		return new DAO().getDonHangIDKH(IDKH);
	}
	
	@GetMapping("/donhangnv/{IDNV}")
	public ArrayList<DonHang> getDonHangIDNV(@PathVariable("IDNV") int IDNV) {
		return new DAO().getDonHangIDNV(IDNV);
	}

	// ================= ĐÁNH GIÁ ======================
	@GetMapping("/danhgia/{IDSP}")
	public ArrayList<DanhGia> getDanhGiaByIDSP(@PathVariable("IDSP") int IDSP) {
		return new DAO().getDanhGiaByIDSP(IDSP);
	}

	@PostMapping("/danhgia")
	public boolean insertDanhGia(@Valid @RequestBody DanhGia dg) {
		return new DAO().insertDanhGia(dg);
	}

	@DeleteMapping("/danhgia/{IDKH}/{IDSP}")
	public boolean deleteDanhGia(@PathVariable("IDKH") int IDKH, @PathVariable("IDSP") int IDSP) {
		return new DAO().deleteDanhGia(IDKH, IDSP);
	}

	// ----------------------Login----------------------------------------------
	
	@GetMapping("/taikhoan/{Username}")
	public TaiKhoan getTKByUsername(@PathVariable("Username") String Username) {
		return new DAO().getTKByUsername(Username);
	}
	@GetMapping("/taikhoan")
	public ArrayList<TaiKhoan> getTaiKhoan() {
		return new DAO().getTaiKhoan();
	}

	@PostMapping("/dangki")
	public boolean addTaiKhoan(@Valid @RequestBody TaiKhoan tk) {
		return new DAO().addTaiKhoan(tk);
	}

	@DeleteMapping("/taikhoan/{IDTK}")
	public boolean deletetk(@PathVariable("IDTK") int IDTK) {
		return new DAO().deletetaikhoan(IDTK);
	}

	@DeleteMapping("/taikhoanxu/{Username}")
	public boolean deletetku(@PathVariable("Username") String Username) {
		return new DAO().deletetaikhoanU(Username);
	}

	// ===================== Quyen ================================
	@GetMapping("/quyen")
	public ArrayList<Quyen> getQuyen() {
		return new DAO().getQuyen();
	}

	@PostMapping("/quyen")
	public boolean insertQuyen(@Valid @RequestBody Quyen l) {
		return new DAO().insertQuyen(l);
	}

	@DeleteMapping("/quyen/{IDQuyen}")
	public boolean deleteQuyen(@PathVariable("IDQuyen") int IDQuyen) {
		return new DAO().deleteQuyen(IDQuyen);
	}

	@PutMapping("/quyen/{IDQuyen}")
	public boolean updateQuyen(@Valid @RequestBody Quyen l, @PathVariable("IDQuyen") int IDQuyen) {
		return new DAO().updateQuyen(l, IDQuyen);
	}

	@GetMapping("/quyen/{IDQuyen}")
	public Quyen getQuyenById(@PathVariable("IDQuyen") int IDQuyen) {
		return new DAO().getQuyenById(IDQuyen);
	}
}
