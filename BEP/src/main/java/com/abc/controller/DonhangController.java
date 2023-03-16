package com.abc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abc.model.*;

@Controller
@RequestMapping("/admin")
public class DonhangController extends GetList {
	@RequestMapping("/donhang")
	public String getdonhang(Model model) {
		return "admin/donhang";
	}

	@GetMapping("/chitietdh")
	public String getCT(Model model, int IDDH) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("IDDH", String.valueOf(IDDH));
		ResponseEntity<ChiTietDH[]> list = rt.getForEntity("http://localhost:8080/chitietdh/{IDDH}", ChiTietDH[].class,
				params);
		ChiTietDH listCT[] = list.getBody();
		model.addAttribute("listct", listCT);
		model.addAttribute("IDDH", IDDH);
		return "admin/donhang";
	}

	@RequestMapping("/editdh/{IDDH}")
	public String editdh(@PathVariable("IDDH") int IDDH, Model model) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDDH", String.valueOf(IDDH));
		DonHang dh = rt.getForObject("http://localhost:8080/donhang/{IDDH}", DonHang.class, params);
		model.addAttribute("dh", dh);

		rt = new RestTemplate();
		ResponseEntity<ChiTietDH[]> list = rt.getForEntity("http://localhost:8080/chitietdh/{IDDH}", ChiTietDH[].class,
				params);
		ChiTietDH listCT[] = list.getBody();
		model.addAttribute("listct", listCT);
		return "admin/sua_donhang";
	}

	@RequestMapping("/editdonhang/{IDDH}")
	public String editdonhang(@PathVariable("IDDH") int IDDH, DonHang dh) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("IDDH", String.valueOf(IDDH));
		rt.put("http://localhost:8080/donhang/{IDDH}", dh, params);
		return "redirect:../donhang";
	}
}
