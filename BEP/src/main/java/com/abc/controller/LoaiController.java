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
public class LoaiController extends GetList {
	@RequestMapping("/loai")
	public String getloai() {
		return "admin/ds_loai";
	}

	@RequestMapping("/them_loai")
	public String them_loai() {
		return "admin/them_loai";
	}

	@RequestMapping("/addloai")
	public String addloai(Loai sp) {
		RestTemplate rt = new RestTemplate();
		kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/loai", sp, String.class));
		return "redirect:./loai";
	}

	@RequestMapping("/deleteloai/{IDLoai}")
	public String deleteloai(@PathVariable("IDLoai") int IDLoai) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDLoai", String.valueOf(IDLoai));
		rt.delete("http://localhost:8080/loai/{IDLoai}", params);
		return "redirect:../loai";
	}

	@RequestMapping("/editl/{IDLoai}")
	public String editl(@PathVariable("IDLoai") int IDLoai, Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDLoai", String.valueOf(IDLoai));
		Loai sp = rt.getForObject("http://localhost:8080/loai/{IDLoai}", Loai.class, params);
		model.addAttribute("l", sp);
		return "admin/sua_loai";
	}

	@RequestMapping("/editloai/{IDLoai}")
	public String editloai(@PathVariable("IDLoai") int IDLoai, Loai sp) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDLoai", String.valueOf(IDLoai));
		rt.put("http://localhost:8080/loai/{IDLoai}", sp, params);
		return "redirect:../loai";
	}
}
