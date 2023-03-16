package com.abc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.abc.model.*;
import com.abc.controller.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Controller
public class DathangController extends GetList {

	@RequestMapping(value = "/giohang", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addgiohang(Model model, HttpServletRequest request, GioHang gh) {
		HttpSession adsession = request.getSession();
//		KhachHang tk=(KhachHang) adsession.getAttribute("login"); 
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");
		String Username = tk.getUsername();
		gh.setUsername(Username);

		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDSP", String.valueOf(gh.getIDSP()));
		SanPham sp = rt.getForObject("http://localhost:8080/sanpham/{IDSP}", SanPham.class, params);
		gh.setGia(sp.getGiaKM());
		// KIỂM TRA SP ĐÃ ĐƯỢC THÊM TRƯỚC ĐÓ CHƯA
		GioHang[] listgh = getListGHHbyIDKH(tk.getUsername());
		for (GioHang g : listgh) {
			if (g.getIDSP() == gh.getIDSP()) {
				gh.setSL(gh.getSL() + g.getSL());
				params.put("IDSP", String.valueOf(gh.getIDSP()));
				rt.put("http://localhost:8080/giohang/" + Username + "/{IDSP}", gh, params);
				updateGioHang(request);
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("redirect:./sanpham");
				return modelAndView;
			}
		}
		rt = new RestTemplate();
		boolean kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/giohang", gh, String.class));
		updateGioHang(request);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:./sanpham");
		return modelAndView;
	}

	@RequestMapping(value = "/cgiohang", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addcgiohang(Model model, HttpServletRequest request, GioHang gh) {
		HttpSession adsession = request.getSession();
		Map<String, String> params = new HashMap<>();
		RestTemplate rt = new RestTemplate();
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");
		String Username = tk.getUsername();
		gh.setUsername(Username);
		// KIỂM TRA SP ĐÃ ĐƯỢC THÊM TRƯỚC ĐÓ CHƯA
		GioHang[] listgh = getListGHHbyIDKH(tk.getUsername());
		for (GioHang g : listgh) {
			if (g.getIDSP() == gh.getIDSP()) {
				gh.setSL(gh.getSL() + g.getSL());
				params.put("IDSP", String.valueOf(gh.getIDSP()));
				rt.put("http://localhost:8080/giohang/" + Username + "/{IDSP}", gh, params);
				updateGioHang(request);
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("redirect:./sanpham/" + gh.getIDSP());
				return modelAndView;
			}
		}

		boolean kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/giohang", gh, String.class));
		updateGioHang(request);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:./sanpham/" + gh.getIDSP());

		return modelAndView;
	}

	@RequestMapping(value = "/muangay", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView muangay(Model model, HttpServletRequest request, GioHang gh) {
		HttpSession adsession = request.getSession();
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");
		String Username = tk.getUsername();
		gh.setUsername(Username);

		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDSP", String.valueOf(gh.getIDSP()));
		SanPham sp = rt.getForObject("http://localhost:8080/sanpham/{IDSP}", SanPham.class, params);
		gh.setGia(sp.getGiaKM());

		// KIỂM TRA SP ĐÃ ĐƯỢC THÊM TRƯỚC ĐÓ CHƯA
		GioHang[] listgh = getListGHHbyIDKH(tk.getUsername());
		for (GioHang g : listgh) {
			if (g.getIDSP() == gh.getIDSP()) {
				gh.setSL(gh.getSL() + g.getSL());
				params.put("IDSP", String.valueOf(gh.getIDSP()));
				rt.put("http://localhost:8080/giohang/" + Username + "/{IDSP}", gh, params);
				updateGioHang(request);
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("redirect:./xemgiohang");
				return modelAndView;
			}
		}

		rt = new RestTemplate();
		boolean kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/giohang", gh, String.class));
		updateGioHang(request);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:./xemgiohang");
		return modelAndView;
	}

	@RequestMapping("/xoasp/{IDSP}")
	public String xoasp(@PathVariable("IDSP") int IDSP, HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDSP", String.valueOf(IDSP));

		HttpSession adsession = request.getSession();
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");

		String Username = tk.getUsername();
		rt.delete("http://localhost:8080/xoagiohang/" + Username + "/{IDSP}", params);
		updateGioHang(request);
		return "redirect:../xemgiohang";
	}

	@RequestMapping("/lichsumuahang")
	public String lichsumuahang(HttpServletRequest request, Model model) {
		KhachHang k = getKHbyLogin(request);
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		DonHang list[] = null;
		if (k == null) {
			HttpSession adsession = request.getSession();
			adsession.getAttribute("login");
			if (adsession.getAttribute("login") == null)
				return null;
			TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");
			params.put("Username", tk.getUsername());
			NhanVien nv = rt.getForObject("http://localhost:8080/nhanvientk/{Username}", NhanVien.class, params);
			list = getListDHbyIDNV(nv.getIDNV());
		} else
			list = getListDHbyIDKH(k.getIDKH());

		for (int i = 0; i < list.length; i++) {
			params.put("IDDH", String.valueOf(list[i].getIDDH()));
			ResponseEntity<ChiTietDH[]> listct = rt.getForEntity("http://localhost:8080/chitietdh/{IDDH}",
					ChiTietDH[].class, params);
			ChiTietDH listCT[] = listct.getBody();
			list[i].setList(listCT);
		}
		model.addAttribute("listdonhang", list);
		return "lichsumuahang";
	}

	@RequestMapping("/dathang")
	public String dathang(Model model, HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		HttpSession adsession = request.getSession();
		adsession.getAttribute("login");
		if (adsession.getAttribute("login") == null)
			return "dathang";
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");

		if (tk.getIDQuyen() == 1) {
			ResponseEntity<NhanVien[]> list = rt.getForEntity("http://localhost:8080/nhanvien", NhanVien[].class);
			NhanVien[] listkh = list.getBody();
			for (NhanVien k : listkh) {
				if (tk.getUsername().equalsIgnoreCase(k.getUsername())) {
					KhachHang kh = null;
					model.addAttribute("kh", kh);
//					return suakh
//					break;
				}
			}
		} else {
			ResponseEntity<KhachHang[]> list = rt.getForEntity("http://localhost:8080/khachhang", KhachHang[].class);
			KhachHang[] listkh = list.getBody();
			for (KhachHang k : listkh) {
				if (tk.getUsername().equalsIgnoreCase(k.getUsername())) {
					model.addAttribute("email", k.getEmail());
					if (k.getHoTen().isEmpty())
						k = null;
					model.addAttribute("kh", k);
					break;
				}
			}
		}
		return "dathang";
	}
	
	@Autowired
	private EmailSenderService service;
	public void triggerMail(String Email, String sub, String content) {

		service.sendSimpleEmail(Email,
				sub,
				content
				);
	}
	
	@RequestMapping("/xacnhandathang")
	public String xacnhandathang(DonHang dh, Model model, HttpServletRequest request, String Email) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		HttpSession adsession = request.getSession();
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");
		params.put("Username", tk.getUsername());
		KhachHang khach = rt.getForObject("http://localhost:8080/khachhangtk/{Username}", KhachHang.class, params);
		// if (tk.getIDQuyen() == 2) {
		if (khach.getIDKH() == 0) {
			khach.setHoTen(dh.getHoTenNguoiNhan());
			khach.setSDT(dh.getSDT());
			khach.setDiaChi(dh.getDiaChi());
			khach.setEmail(Email);
			if (tk.getIDQuyen() == 1)
				khach.setUsername(null);
			else
				khach.setUsername(tk.getUsername());

			rt = new RestTemplate();
			kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/newkhachhang", khach, String.class));

		}
		if (tk.getIDQuyen() == 2) {
			params = new HashMap<>();
			params.put("Username", tk.getUsername());
			khach = rt.getForObject("http://localhost:8080/khachhangtk/{Username}", KhachHang.class, params);
		} else {
			khach = rt.getForObject("http://localhost:8080/getKhachHang_IDKH", KhachHang.class);
			params.put("Username", tk.getUsername());
			NhanVien nv = rt.getForObject("http://localhost:8080/nhanvientk/{Username}", NhanVien.class, params);
			dh.setIDNV(nv.getIDNV());
		}
		dh.setIDKH(khach.getIDKH());
		if (dh.getPhuongThucTT() == 1) {
			return "redirect:./thanhtoanpaypal";
		}
		// thêm đơn hàng
		rt = new RestTemplate();
		if (tk.getIDQuyen() == 2)
			kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/donhang", dh, String.class));
		else
			kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/donhangbynhanvien", dh, String.class));

		// lấy IDDH vừa thêm
		dh = rt.getForObject("http://localhost:8080/getDonHang_IDDH", DonHang.class);
		dh.getNgayGiaoDich();
		
		// Thêm chitietdh từ list gio hàng của user
		params.put("Username", tk.getUsername());
		ResponseEntity<GioHang[]> listg = rt.getForEntity("http://localhost:8080/giohang/{Username}", GioHang[].class,
				params);
		GioHang listgh[] = listg.getBody();
		for (GioHang gh : listgh) {
			ChiTietDH ct = new ChiTietDH();
			ct.setIDDH(dh.getIDDH());
			ct.setIDSP(gh.getIDSP());
			ct.setSL(gh.getSL());
			ct.setGia(gh.getGia());
			rt = new RestTemplate();
			kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/themchitietdh", ct, String.class));
		}
		// xóa giohang theo userZZ
		params.put("Username", tk.getUsername());
		rt.delete("http://localhost:8080/xoagiohang/{Username}", params);
		updateGioHang(request);
		String sub = "XÁC NHẬN ĐƠN HÀNG";
		String content = "Xin chào " + khach.getHoTen() + "!\n Đơn hàng của bạn được đặt vào ngày "
				+ dh.getNgayGiaoDich() + ".\nTổng tiền: " + dh.getTongTien() + "\n Họ tên khách nhận hàng: "
				+ dh.getHoTenNguoiNhan() + "\nGiao tại: " + dh.getDiaChi() + "\n\n Cảm ơn bạn đã tin tưởng các sản"
				+ "phẩm của Chúng tôi. Chúng tôi sẽ phản hồi đơn hàng bạn sớm nhất có thể.";
//		EmailSenderServive.sendSimpleEmail(Email,sub, content);
//		sendMail(Email, sub, content);
		triggerMail(Email,content,sub);

		return "success";
	}
}
