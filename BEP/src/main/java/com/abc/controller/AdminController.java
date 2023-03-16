package com.abc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abc.model.*;

@Controller
@RequestMapping("admin/")
public class AdminController {
	@RequestMapping("login")
	public String index() {
		return "admin/dangnhap";
	}

	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model) {
		HttpSession adsession = request.getSession();
		adsession.getAttribute("user");
		if (adsession.getAttribute("user") == null) {
			return "admin/dangnhap";
		}
		RestTemplate rt = new RestTemplate();
		ResponseEntity<DoanhSo[]> list = rt.getForEntity("http://localhost:8080/doanhso", DoanhSo[].class);
		DoanhSo[] listds = list.getBody();
		String send = "[";
		for (int i = 1; i <= 12; i++) {
			int check = 0;
			for (DoanhSo d : listds) {
				if (d.getThang() == i) {
					send = send + d.getTien();
					check = 1;
					break;
				}
			}
			if (check == 0)
				send = send + 0;
			if (i < 12)
				send = send + ",";
		}
		send = send + "]";
		model.addAttribute("doanhso", send);
		return "admin/index";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// session1.invalidate();
		session.removeAttribute("user");
		session.removeAttribute("nhanviendn");

		return "admin/dangnhap";
	}

	@PostMapping("/login")
	public String login(String username, String password, HttpServletRequest request, Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<TaiKhoan[]> list = rt.getForEntity("http://localhost:8080/taikhoan", TaiKhoan[].class);
		TaiKhoan users[] = list.getBody();
		for (TaiKhoan u : users) {

			if (u.getIDQuyen() == 1) {
				if (u.getUsername().equalsIgnoreCase(username)) {
					if (u.getPassword().equalsIgnoreCase(password)) {
						request.getSession().setAttribute("user", u);
						rt = new RestTemplate();
						ResponseEntity<NhanVien[]> listn = rt.getForEntity("http://localhost:8080/nhanvien",
								NhanVien[].class);
						NhanVien nvv[] = listn.getBody();
						for (NhanVien x : nvv) {
							if (x.getUsername().equalsIgnoreCase(u.getUsername())) {
								request.getSession().setAttribute("nhanviendn", x);
								return "redirect:/admin/index";
							}
						}
					}
					model.addAttribute("mess_lg", "Mật khẩu không đúng! Mời nhập lại!");
					return "admin/dangnhap";
				}
			}
		}
		model.addAttribute("mess_lg", "Tên người dùng không đúng! Mời nhập lại!");
		return "admin/dangnhap";
	}

	@RequestMapping("baocao")
	public String BaoCao(Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<DoanhSo[]> list = rt.getForEntity("http://localhost:8080/doanhso", DoanhSo[].class);
		DoanhSo[] listds = list.getBody();
		String send = "[";
		for (int i = 1; i <= 12; i++) {
			int check = 0;
			for (DoanhSo d : listds) {
				if (d.getThang() == i) {
					send = send + d.getTien();
					check = 1;
					break;
				}
			}
			if (check == 0)
				send = send + 0;
			if (i < 12)
				send = send + ",";
		}
		send = send + "]";
		model.addAttribute("doanhso", send);
		return "admin/baocao";
	}
}
