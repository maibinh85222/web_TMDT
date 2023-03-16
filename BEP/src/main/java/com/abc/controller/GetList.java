package com.abc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import com.abc.model.DonHang;
import com.abc.model.GioHang;
import com.abc.model.KhachHang;
import com.abc.model.Loai;
import com.abc.model.NhanVien;
import com.abc.model.SanPham;
import com.abc.model.TaiKhoan;

@Controller
public class GetList {
	
	public boolean kq = false;
//	public void sendMail(String Email,String sub, String content) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		MailSender mailSender = (MailSender) context.getBean("mailSender2");
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("ngoisaotihon11@gmail.com");
//		message.setTo(Email);
//		message.setSubject(sub);
//		message.setText(content);
//		mailSender.send(message);
//		context.close();
//	}
	@ModelAttribute("khachhang")
	public KhachHang getKHbyLogin(HttpServletRequest request) {
		HttpSession adsession = request.getSession();
		adsession.getAttribute("login");
		if (adsession.getAttribute("login") == null) {
			return null;
		}
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");
		if(tk.getIDQuyen()==1) return null;
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("Username", tk.getUsername());
		KhachHang kh = rt.getForObject("http://localhost:8080/khachhangtk/{Username}", KhachHang.class, params);
		return kh;
	}
	

	public void updateGioHang(HttpServletRequest request) {
		HttpSession adsession = request.getSession();
		TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("Username", tk.getUsername());
		ResponseEntity<GioHang[]> listg = rt.getForEntity("http://localhost:8080/giohang/{Username}", GioHang[].class,
				params);
		GioHang listgh[] = listg.getBody();
		int tongtien = 0;
		for (int i = 0; i < listgh.length; i++) {
			tongtien = tongtien + (listgh[i].getGia() * listgh[i].getSL());
		}
		request.getSession().setAttribute("tongtien", tongtien);
		int i = listgh.length;
		request.getSession().setAttribute("sogh", i);
		request.getSession().setAttribute("listgh", listgh);
	}

	public GioHang[] getListGHHbyIDKH(String username) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("Username", username);
		ResponseEntity<GioHang[]> listg = rt.getForEntity("http://localhost:8080/giohang/{Username}", GioHang[].class,
				params);
		GioHang[] listgh = listg.getBody();
		return listgh;
	}
	
	public DonHang[] getListDHbyIDKH(int IDKH) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("IDKH", String.valueOf(IDKH));
		ResponseEntity<DonHang[]> listg = rt.getForEntity("http://localhost:8080/donhangkh/{IDKH}", DonHang[].class,
				params);
		DonHang listdh[] = listg.getBody();
		return listdh;
	}
	
	public DonHang[] getListDHbyIDNV(int IDNV) {
		RestTemplate rt = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("IDNV", String.valueOf(IDNV));
		ResponseEntity<DonHang[]> listg = rt.getForEntity("http://localhost:8080/donhangnv/{IDNV}", DonHang[].class,
				params);
		DonHang listdh[] = listg.getBody();
		return listdh;
	}

	@ModelAttribute("listl")
	public Loai[] getListLoai() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Loai[]> listloai = rt.getForEntity("http://localhost:8080/loai", Loai[].class);
		Loai[] list = listloai.getBody();
		return list;
	}

	@ModelAttribute("listsp")
	public SanPham[] getListSanPham() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<SanPham[]> listsanpham = rt.getForEntity("http://localhost:8080/sanpham", SanPham[].class);
		SanPham[] list = listsanpham.getBody();
		return list;
	}
	
	@ModelAttribute("listkh")
	public KhachHang[] getListKhachHang() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<KhachHang[]> list = rt.getForEntity("http://localhost:8080/khachhang", KhachHang[].class);
		KhachHang[] listkh = list.getBody();
		return listkh;
	}
	
	@ModelAttribute("listnv")
	public NhanVien[] getListNhanVien() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<NhanVien[]> list = rt.getForEntity("http://localhost:8080/nhanvien", NhanVien[].class);
		NhanVien[] listnv = list.getBody();
		return listnv;
	}
	
	@ModelAttribute("listdh")
	public DonHang[] getListDonHang() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<DonHang[]> list = rt.getForEntity("http://localhost:8080/donhang", DonHang[].class);
		DonHang[] listdh = list.getBody();
		Map<String, String> params = new HashMap<>();
		
		for (DonHang donHang : listdh) {
			if(donHang.getIDNV()!=0)
			{
				params.put("IDNV", String.valueOf(donHang.getIDNV()));
				NhanVien nv = rt.getForObject("http://localhost:8080/nhanvien/{IDNV}", NhanVien.class, params);
				donHang.setHoTenNV(nv.getHoTen());
			}
			else donHang.setHoTenNV("Trống");
		}
		return listdh;
	}

}
