package com.abc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abc.model.*;

@Controller
@RequestMapping("/admin")
public class KhachhangController {
	@RequestMapping("/ds_khachhang")
	public String getkhachhang(Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<KhachHang[]> list = rt.getForEntity("http://localhost:8080/khachhang", KhachHang[].class);
		KhachHang[] listkh = list.getBody();
		model.addAttribute("listkh", listkh);
		return "admin/ds_khachhang";
	}

	@PostMapping("/khachhang")
	public String addkhachhang(KhachHang kh) {
		RestTemplate rt = new RestTemplate();
		boolean kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/khachhang", kh, String.class));

		return "redirect:ds_khachhang";
	}

	@RequestMapping("/them_khachhang")
	public String dangki(Model model) {
		/*
		 * RestTemplate rt = new RestTemplate(); ResponseEntity<TaiKhoan[]> list =
		 * rt.getForEntity("http://localhost:8080/taikhoan", TaiKhoan[].class);
		 * TaiKhoan[] listtk = list.getBody(); model.addAttribute("listk", listtk);
		 * 
		 * ResponseEntity<KhachHang[]> listn =
		 * rt.getForEntity("http://localhost:8080/khachhang", KhachHang[].class);
		 * KhachHang[] listkh = listn.getBody();
		 * 
		 * ArrayList<TaiKhoan> ltk = new ArrayList<>(); String s ="admin"; int k = 0;
		 * for (TaiKhoan u : listtk) { for (KhachHang kh : listkh) { if (u.getIDUser()
		 * == kh.getIDUser() || u.getIDQuyen()==1) { k = 1; break; } } if (k == 0) {
		 * ltk.add(u); } k = 0; } model.addAttribute("listkkh", ltk);
		 */
		return "admin/them_khachhang";
	}

	@RequestMapping("/dangkikhachhang/{IDKH}")
	public String fdangkikh(HttpServletRequest request, @PathVariable("IDKH") int IDKH) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDKH", String.valueOf(IDKH));
		KhachHang kh = rt.getForObject("http://localhost:8080/khachhang/{IDKH}", KhachHang.class, params);
		request.getSession().setAttribute("khdk", kh);
		return "admin/dangkikh";
	}
	
	@PostMapping("/dangkikhachhang")
	public String dangkikh(HttpServletRequest request, TaiKhoan tk, Model model, HttpSession session) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<TaiKhoan[]> list = rt.getForEntity("http://localhost:8080/taikhoan", TaiKhoan[].class);
		TaiKhoan[] listtk = list.getBody();
		for (TaiKhoan t : listtk) {
			if (t.getUsername().equalsIgnoreCase(tk.getUsername())) {
				model.addAttribute("mess_dkkh", "Tên người dùng này đã có! Mời nhập lại!");
				return "admin/dangkikh";
			}
		}
		KhachHang n = (KhachHang) session.getAttribute("khdk");

		boolean kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/dangki", tk, String.class));

		Map<String, String> params = new HashMap<>();
		params.put("IDKH", String.valueOf(n.getIDKH()));
		rt.put("http://localhost:8080/tkkhachhang/{IDKH}", tk.getUsername(), params);
		session.removeAttribute("khdk");

		return "redirect:ds_khachhang";
	}

	@RequestMapping("/deletekhachhang/{IDKH}")
	public String deletekhachhang(@PathVariable("IDKH") int IDKH) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDKH", String.valueOf(IDKH));

		// If nhan vien co tai khoan
		KhachHang kh = rt.getForObject("http://localhost:8080/khachhang/{IDKH}", KhachHang.class, params);
		rt.delete("http://localhost:8080/khachhang/{IDKH}", params);
		if (kh.getUsername() != "") {
			rt = new RestTemplate();
			params.put("Username", kh.getUsername());
			rt.delete("http://localhost:8080/taikhoanxu/{Username}", params);
		}

		return "redirect:../ds_khachhang";
	}
}
