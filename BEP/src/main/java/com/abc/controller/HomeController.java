package com.abc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.abc.controller.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abc.model.*;



@Controller
public class HomeController extends GetList {
	public Loai[] listl = getListLoai();

	@RequestMapping("/dssanpham/{IDLoai}")
	public String getsanphamtheoloai(HttpServletRequest request, @PathVariable("IDLoai") int IDLoai, Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDLoai", String.valueOf(IDLoai));
		ResponseEntity<SanPham[]> list = rt.getForEntity("http://localhost:8080/dssanpham/{IDLoai}", SanPham[].class,
				params);
		SanPham[] listsp = list.getBody();
		model.addAttribute("listsp", listsp);
		if (listsp.length == 0)
			listsp = null;
		for (Loai k : listl) {
			if (k.getIDLoai() == IDLoai) {
				request.getSession().setAttribute("loaichon", k);
				return "sanpham";
			}
		}
		return "sanpham";
	}

	@PostMapping("luachon")
	public String luachon(luachon l, Model model) {
		RestTemplate rt = new RestTemplate();
		if (l.getLc() == 1) {
			rt = new RestTemplate();
			ResponseEntity<SanPham[]> list1 = rt.getForEntity("http://localhost:8080/sanphammoinhat", SanPham[].class);
			SanPham[] lists = list1.getBody();
			model.addAttribute("listsp", lists);
		} else if (l.getLc() == 2) {
			rt = new RestTemplate();
			ResponseEntity<SanPham[]> list1 = rt.getForEntity("http://localhost:8080/sanphamview", SanPham[].class);
			SanPham[] lists = list1.getBody();
			model.addAttribute("listsp", lists);
		} else if (l.getLc() == 3) {
			rt = new RestTemplate();
			ResponseEntity<SanPham[]> list1 = rt.getForEntity("http://localhost:8080/sanphamsale", SanPham[].class);
			SanPham[] lists = list1.getBody();
			model.addAttribute("listsp", lists);
		} else
			return "redirect:sanpham";

		return "sanpham";
	}

	@RequestMapping("/sanpham/{IDSP}")
	public String getidsanpham(HttpServletRequest request, @PathVariable("IDSP") int IDSP, Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDSP", String.valueOf(IDSP));
		SanPham sp = rt.getForObject("http://localhost:8080/sanpham/{IDSP}", SanPham.class, params);
		model.addAttribute("sp", sp);
		SoLuong slm = new SoLuong(); // so luong mua
		slm.setSlmua(1);
		slm.setSL(sp.getSL());
		slm.setIDSP(IDSP);
		model.addAttribute("slm", slm);
		model.addAttribute("IDSP", sp.getIDSP());

		ResponseEntity<SanPham[]> listspn = rt.getForEntity("http://localhost:8080/sanphamngaunhien", SanPham[].class);
		SanPham[] lisspnn = listspn.getBody();
		model.addAttribute("lisspnn", lisspnn); // LẤY DS SP NGẪU NHIÊN (4)

		rt = new RestTemplate();
		params = new HashMap<String, String>();
		params.put("IDSP", String.valueOf(IDSP));
		ResponseEntity<DanhGia[]> listd = rt.getForEntity("http://localhost:8080/danhgia/{IDSP}", DanhGia[].class,
				params);
		DanhGia listdg[] = listd.getBody();
		int so = listdg.length;
		model.addAttribute("sodg", so);
		model.addAttribute("listdg", listdg);
		HttpSession adsession = request.getSession();
		adsession.getAttribute("login");
		if (adsession.getAttribute("login") != null) {
			adsession.removeAttribute("danhgia");
			KhachHang khachhang = getKHbyLogin(request);
			DonHang[] listDH = getListDHbyIDKH(khachhang.getIDKH());
			for (int i = 0; i < listDH.length; i++) {
				rt = new RestTemplate();
				params = new HashMap<String, String>();
				params.put("IDDH", String.valueOf(listDH[i].getIDDH()));
				ResponseEntity<ChiTietDH[]> listct = rt.getForEntity("http://localhost:8080/chitietdh/{IDDH}",
						ChiTietDH[].class, params);
				ChiTietDH listCT[] = listct.getBody();
				for (int k = 0; k < listCT.length; k++) {
					if (listCT[k].getIDSP() == IDSP) {
						DanhGia d = new DanhGia();
						for (int j = 0; j < listdg.length; j++) {
							if (listdg[j].getIDKH() == khachhang.getIDKH()) {
								d = listdg[j];
								request.getSession().setAttribute("khdg", khachhang);
								request.getSession().setAttribute("danhgia", d);
								return "chitietsanpham";
							}
						}
						request.getSession().setAttribute("khdg", khachhang);
						request.getSession().setAttribute("danhgia", d);
						return "chitietsanpham";
					}
				}
			}
		}
		return "chitietsanpham";
	}

	@RequestMapping("/themdanhgia")
	public String themdanhgia(Model model, HttpServletRequest request, DanhGia dg) {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		dg.setNgayDG(date);
		RestTemplate rt = new RestTemplate();
		rt.delete("http://localhost:8080/danhgia/" + dg.getIDKH() + "/" + dg.getIDSP());
		kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/danhgia", dg, String.class));
		model.addAttribute("mess_dg", "Cảm ơn bạn đã ý kiến!");
		return "redirect:/sanpham/" + dg.getIDSP();
	}

	// =================================================
	@RequestMapping("/")
	public String index() {
		return "trangchu";
	}

	@RequestMapping("trangchu")
	public String trangchu() {
		return "trangchu";
	}

	@RequestMapping("/view")
	public String view() {
		return "view";
	}

	@RequestMapping("/gioithieu")
	public String getgioithieu(Model model) {
		return "gioithieu";
	}

	@RequestMapping("/sanpham")
	public String getsanpham(Model model) {
		return "sanpham";
	}

	@RequestMapping("/dangnhap")
	public String getdangnhap(Model model) {
		return "dangnhap";
	}

	@PostMapping("/dangnhap")
	public String dangnhap(String Username, String Password, HttpServletRequest request, Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<TaiKhoan[]> list = rt.getForEntity("http://localhost:8080/taikhoan", TaiKhoan[].class);
		TaiKhoan users[] = list.getBody();
		for (TaiKhoan u : users) {
			if (u.getUsername().equalsIgnoreCase(Username)) {
//				if(u.getIDQuyen()==2) {
				int quyen=0;
				quyen=u.getIDQuyen();
				if (u.getPassword().equalsIgnoreCase(Password) && quyen==2) {
					request.getSession().setAttribute("login", u);
					updateGioHang(request);
					return "trangchu";
				}
//				else if(u.getPassword().equalsIgnoreCase(Password) && quyen==1) {
//					return "http://localhost:8080/admin/login";
//				}
				model.addAttribute("mess_dn", "Mật khẩu chưa đúng! Mời nhập lại!");
				return "dangnhap";
//			}
//				else {
//					return "http://localhost:8080/admin/login";
//				}
			}
		}
		model.addAttribute("mess_dn", "Tên người dùng chưa đúng! Mời nhập lại!");
		return "dangnhap";

	}

	@RequestMapping("/dangxuat")
	public String dangxuat(HttpSession session) {
		session.removeAttribute("login");
		session.removeAttribute("kemail");
		session.removeAttribute("loaichon");
		session.removeAttribute("sogh");
		session.removeAttribute("tongtien");
		session.removeAttribute("danhgia");
		session.removeAttribute("listgh");
		session.removeAttribute("khdg");
		return "dangnhap";
	}

	// ===========================================================

	@PostMapping("/thongtinkhachhang")
	public String addthongtinkhachhang(HttpServletRequest request, KhachHang kh, Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<KhachHang[]> list = rt.getForEntity("http://localhost:8080/khachhang", KhachHang[].class);
		KhachHang[] listkh = list.getBody();
		for (KhachHang k : listkh) {
			if (kh.getEmail().equalsIgnoreCase(k.getEmail())) {
				model.addAttribute("mess_ttkh", "Email này đã có người dùng! Mời nhập lại!");
				return "redirect:thongtinkhachhang";
			}
			if (kh.getSDT().equalsIgnoreCase(k.getSDT())) {
				model.addAttribute("mess_ttkh", "Số điện thoại này đã có người dùng! Mời nhập lại!");
				return "redirect:thongtinkhachhang";
			}
		}

		rt = new RestTemplate();
		kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/khachhang", kh, String.class));

		return "redirect:thongtinkhachhang";
	}

	@RequestMapping("/thongtinkhachhang")
	public String dangkithongtin(HttpServletRequest request, Model model) {
		RestTemplate rt = new RestTemplate();
		HttpSession adsession = request.getSession();
		adsession.getAttribute("login");
		if (adsession.getAttribute("login") == null) {
			return "trangchu";
		}
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");

		if (tk.getIDQuyen() == 1) {
			ResponseEntity<NhanVien[]> list = rt.getForEntity("http://localhost:8080/nhanvien", NhanVien[].class);
			NhanVien[] listkh = list.getBody();
			for (NhanVien k : listkh) {
				if (tk.getUsername().equalsIgnoreCase(k.getUsername())) {
					model.addAttribute("dskh", k);
					request.getSession().setAttribute("kemail", "admin");
					break;
				}
			}
		} else {
			ResponseEntity<KhachHang[]> list = rt.getForEntity("http://localhost:8080/khachhang", KhachHang[].class);
			KhachHang[] listkh = list.getBody();
			for (KhachHang k : listkh) {
				if (tk.getUsername().equalsIgnoreCase(k.getUsername())) {
					model.addAttribute("dskh", k);
					request.getSession().setAttribute("kemail", k.getEmail()); // MĐ: LẤY EMAIL CỦA KHÁCH HÀNG
					break;
				}
			}
		}
		return "thongtinkhachhang";
	}

	@RequestMapping("/dangkikhachhang")
	public String dangkikhachhang() {

		return "dangkikhachhang";
	}

	@Autowired
	private EmailSenderService service;
	public void triggerMail(String Email, String sub, String content) {

		service.sendSimpleEmail(Email,
				sub,
				content
				);
	}
	@PostMapping("/dangkikhachhang")
	public String dangkikhachhang(HttpServletRequest request, TaiKhoan tk, String Email, Model model) {

		RestTemplate rt = new RestTemplate();
		ResponseEntity<TaiKhoan[]> list = rt.getForEntity("http://localhost:8080/taikhoan", TaiKhoan[].class);
		TaiKhoan users[] = list.getBody();
		for (TaiKhoan u : users) {
			if (u.getUsername().equalsIgnoreCase(tk.getUsername())) {
				model.addAttribute("mess_dkkh", "Tên người dùng này đã có! Mời nhập lại!");
				return "dangkikhachhang";
			}
		}
		KhachHang[] listkh = getListKhachHang();
		for (KhachHang k : listkh) {
			if (k.getEmail().equalsIgnoreCase(Email)) {
				model.addAttribute("mess_dkkh", "Email này đã đăng kí! Mời nhập lại hoặc Quên mật khẩu!");
				return "dangkikhachhang";
			}
		}
		KhachHang k = new KhachHang();
		k.setEmail(Email);
		k.setUsername(tk.getUsername());
		String sub = "XÁC NHẬN ĐĂNG KÍ TÀI KHOẢN";
		String content = "Xin chào " + tk.getUsername() + " !"
				+ "\nChúc mừng bạn đã đăng kí tài khoản thành công.";
		triggerMail(Email, content, sub);
		rt = new RestTemplate();
		kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/dangki", tk, String.class));
		RestTemplate rtp = new RestTemplate();
		kq = Boolean.parseBoolean(rtp.postForObject("http://localhost:8080/dkikhachhang", k, String.class));
		return "redirect:dangnhap";
	}

	@RequestMapping("/suakh/{IDKH}")
	public String suakh(@PathVariable("IDKH") int IDKH, Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDKH", String.valueOf(IDKH));
		KhachHang kh = rt.getForObject("http://localhost:8080/khachhang/{IDKH}", KhachHang.class, params);

		model.addAttribute("kh", kh);
		return "sua_thongtinkhachhang";
	}

	@RequestMapping("/suakhachhang/{IDKH}")
	public String suakhachhang(Model model, HttpServletRequest request, @PathVariable("IDKH") int IDKH, KhachHang kh) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<KhachHang[]> list = rt.getForEntity("http://localhost:8080/khachhang", KhachHang[].class);
		KhachHang users[] = list.getBody();
		for (KhachHang u : users) {
			if (u.getEmail().equalsIgnoreCase(kh.getEmail()) && u.getIDKH() != kh.getIDKH()) {
				model.addAttribute("mess_skh", "Email này đã có tài khoản! Mời nhập lại!");
				return "sua_thongtinkhachhang";
			}
		}
		rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDKH", String.valueOf(IDKH));
		rt.put("http://localhost:8080/khachhang/{IDKH}", kh, params);
		return "redirect:../thongtinkhachhang";
	}

	// ===============================================================
	@RequestMapping("/xemgiohang")
	public String getgiohang() {
		return "giohang";
	}

	// ================== QUÊN MẬT KHẨU =================
	@RequestMapping("/quenmatkhau")
	public String quenmatkhau() {
		return "quenmatkhau";
	}
	


	@PostMapping("/quenmatkhau")
	public String quenmatkhau(String Email, HttpServletRequest request, Model model) {
		KhachHang[] listkh = getListKhachHang();
		for (KhachHang kh : listkh) {
			if (kh.getEmail().equalsIgnoreCase(Email)) {
				RestTemplate rt = new RestTemplate();
				Map<String, String> params = new HashMap<>();
				params.put("Username", kh.getUsername());
				
				TaiKhoan tk = rt.getForObject("http://localhost:8080/taikhoan/{Username}", TaiKhoan.class, params);
				
//				tk.setPassword("12345");
				String sub = "QUÊN MẬT KHẨU";
				
				String content = "Xin chào " + kh.getHoTen()
						+ "\n TheGioiBep gửi bạn lại thông tin đăng nhập bạn đã tạo trước đó. \n Tên tài khoản:\t"
						+ tk.getUsername() + "\n Mật khẩu:\t" + tk.getPassword();
						
				triggerMail(Email, content, sub);
			}
		}
		model.addAttribute("mess_qmk", "Tên người dùng chưa đúng! Mời nhập lại!");
		return "dangnhap";
	}
}
