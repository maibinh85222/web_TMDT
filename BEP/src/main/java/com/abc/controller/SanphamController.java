package com.abc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abc.model.*;

@Controller
@RequestMapping("admin/")
public class SanphamController extends GetList {
	@RequestMapping("/sanpham")
	public String getsanpham() {
		return "admin/ds_sanpham";
	}

	@RequestMapping("/them_sanpham")
	public String them_sanpham() {
		return "admin/them_sanpham";
	}

	@RequestMapping("/addsanpham")
	public String addsanpham(SanPham sp) {
		if (sp.getGiaKM() == 0)
			sp.setGiaKM(sp.getGia());
		RestTemplate rt = new RestTemplate();
		kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/sanpham", sp, String.class));

		return "redirect:./sanpham";
	}

	@RequestMapping("/deletesanpham/{IDSP}")
	public String deletesanpham(@PathVariable("IDSP") int IDSP) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDSP", String.valueOf(IDSP));
		rt.delete("http://localhost:8080/sanpham/{IDSP}", params);
		return "redirect:../sanpham";
	}

	@RequestMapping("/editsp/{IDSP}")
	public String editsp(@PathVariable("IDSP") int IDSP, Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDSP", String.valueOf(IDSP));
		SanPham sp = rt.getForObject("http://localhost:8080/sanpham/{IDSP}", SanPham.class, params);
		model.addAttribute("sp", sp);
		model.addAttribute("IDSP", sp.getIDSP());
		return "admin/sua_sanpham";
	}

	@RequestMapping("/editsanpham/{IDSP}")
	public String editsanpham(@PathVariable("IDSP") int IDSP, SanPham sp) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDSP", String.valueOf(IDSP));
		rt.put("http://localhost:8080/sanpham/{IDSP}", sp, params);

		return "redirect:../sanpham";
	}
}
