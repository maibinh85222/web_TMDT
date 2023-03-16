package com.abc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abc.model.*;

@Controller
@RequestMapping("admin")
public class NhanvienController extends GetList {
	@RequestMapping("/ds_nhanvien")
	public String getnhanvien(Model model) {
		return "admin/ds_nhanvien";
	}

	@ModelAttribute("nv")
	public NhanVien getNV(HttpSession session1) {
		TaiKhoan tk = (TaiKhoan) session1.getAttribute("user");
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("Username", String.valueOf(tk.getUsername()));
		NhanVien nv = rt.getForObject("http://localhost:8080/nhanvientk/{Username}", NhanVien.class, params);
		return nv;
	}

	@PostMapping("/nhanvien")
	public String addnhanvien(NhanVien nv) {
		RestTemplate rt = new RestTemplate();
		kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/nhanvien", nv, String.class));

		return "redirect:ds_nhanvien";
	}

	@RequestMapping("/them_nhanvien")
	public String dangki() {
		return "admin/them_nhanvien";
	}

	@RequestMapping("/dangki/{IDNV}")
	public String fdangki(HttpServletRequest request, @PathVariable("IDNV") int IDNV) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDNV", String.valueOf(IDNV));
		NhanVien nv = rt.getForObject("http://localhost:8080/nhanvien/{IDNV}", NhanVien.class, params);
		request.getSession().setAttribute("nvdk", nv);
		return "admin/dangki";
	}

	@PostMapping("/dangki")
	public String dangki(HttpServletRequest request, TaiKhoan tk, Model model, HttpSession session) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<TaiKhoan[]> list = rt.getForEntity("http://localhost:8080/taikhoan", TaiKhoan[].class);
		TaiKhoan[] listtk = list.getBody();
		for (TaiKhoan t : listtk) {
			if (t.getUsername().equalsIgnoreCase(tk.getUsername())) {
				model.addAttribute("mess_dknv", "Tên người dùng này đã có! Mời nhập lại!");
				return "admin/dangki";
			}
		}
		NhanVien n = (NhanVien) session.getAttribute("nvdk");
		kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/dangki", tk, String.class));

		Map<String, String> params = new HashMap<>();
		params.put("IDNV", String.valueOf(n.getIDNV()));
		rt.put("http://localhost:8080/tknhanvien/{IDNV}", tk.getUsername(), params);
		session.removeAttribute("nvdk");
		return "redirect:ds_nhanvien";
	}

	@RequestMapping("/deletenhanvien/{IDNV}")
	public String deletenhanvien(@PathVariable("IDNV") int IDNV) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDNV", String.valueOf(IDNV));
		// If nhan vien co tai khoan
		NhanVien nv = rt.getForObject("http://localhost:8080/nhanvien/{IDNV}", NhanVien.class, params);
		rt.delete("http://localhost:8080/nhanvien/{IDNV}", params);
		if (nv.getUsername() != "") {
			rt = new RestTemplate();
			params.put("Username", nv.getUsername());
			rt.delete("http://localhost:8080/taikhoanxu/{Username}", params);
		}
		return "redirect:../ds_nhanvien";
	}

	@RequestMapping("/editnv/{IDNV}")
	public String editnv(@PathVariable("IDNV") int IDNV, Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDNV", String.valueOf(IDNV));
		NhanVien nv = rt.getForObject("http://localhost:8080/nhanvien/{IDNV}", NhanVien.class, params);
		model.addAttribute("nv", nv);
		return "admin/sua_nhanvien";
	}

	@RequestMapping("/editnhanvien/{IDNV}")
	public String editnhanvien(@PathVariable("IDNV") int IDNV, NhanVien nv) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDNV", String.valueOf(IDNV));
		rt.put("http://localhost:8080/nhanvien/{IDNV}", nv, params);
		return "redirect:../ds_nhanvien";
	}
}
