package com.abc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.abc.config.PaypalPaymentIntent;
import com.abc.config.PaypalPaymentMethod;
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
import com.abc.service.PaypalService;
import com.abc.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaymentController {

	public static final String URL_PAYPAL_SUCCESS = "success";
	public static final String URL_PAYPAL_CANCEL = "cancel";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

	@GetMapping("/thanhtoanpaypal")
	public String index() {
		return "paypal";
	}

	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("price") double price,String Email,DonHang dh) {
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
//		price=5;
//		price=dh.getTongTien()/23000;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
//			ResponseEntity<KhachHang[]> list = rt.getForEntity("http://localhost:8080/khachhang", KhachHang[].class);
//			KhachHang[] listkh = list.getBody();
//			for (KhachHang k : listkh) {
//				if (tk.getUsername().equalsIgnoreCase(k.getUsername())) {
//					model.addAttribute("email", k.getEmail());
//					if (k.getHoTen().isEmpty())
//						k = null;
//					model.addAttribute("kh", k);
//					break;
//				}
//			}
			
//			dh = getForObject("http://localhost:8080/getDonHang_IDDH", DonHang.class);
//			dh = rt.getForObject("http://localhost:8080/getDonHang_IDDH", DonHang.class);
			// Thêm chitietdh từ list gio hàng của user
//			params.put("Username", tk.getUsername());
//			ResponseEntity<GioHang[]> listg = rt.getForEntity("http://localhost:8080/giohang/{Username}", GioHang[].class,
//					params);
//			GioHang listgh[] = listg.getBody();
//			for (GioHang gh : listgh) {
//				ChiTietDH ct = new ChiTietDH();
//				ct.setIDDH(dh.getIDDH());
//				ct.setIDSP(gh.getIDSP());
//				ct.setSL(gh.getSL());
//				ct.setGia(gh.getGia());
//				rt = new RestTemplate();
//				kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/themchitietdh", ct, String.class));
//			}
			
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
		return "cancel";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,GioHang gh,String Email,HttpServletRequest request,  Model model) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				
//				RestTemplate rt = new RestTemplate();
//				Map<String, String> params = new HashMap<>();
//				HttpSession adsession = request.getSession();
//				TaiKhoan tk = (TaiKhoan) adsession.getAttribute("login");
//				params.put("Username", tk.getUsername());
//				String Name=tk.getUsername();
////				KhachHang khach = rt.getForObject("http://localhost:8080/khachhangtk/{Username}", KhachHang.class, params);
//				// if (tk.getIDQuyen() == 2) {
////				if (khach.getIDKH() == 0) {
////					khach.setHoTen(dh.getHoTenNguoiNhan());
////					khach.setSDT(dh.getSDT());
////					khach.setDiaChi(dh.getDiaChi());
////					khach.setEmail(Email);
////					if (tk.getIDQuyen() == 1)
////						khach.setUsername(null);
////					else
////						khach.setUsername(tk.getUsername());
////
////					rt = new RestTemplate();
////					kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/newkhachhang", khach, String.class));
////
////				}
////				if (tk.getIDQuyen() == 2)
////					kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/donhang", dh, String.class));
////				rt = new RestTemplate();
//
//				// lấy IDDH vừa thêm
////				dh = rt.getForObject("http://localhost:8080/getDonHang_IDDH", DonHang.class);
////				dh.getNgayGiaoDich();
//				
//				// Thêm chitietdh từ list gio hàng của user
////				params.put("Username", tk.getUsername());
////				ResponseEntity<GioHang[]> listg = rt.getForEntity("http://localhost:8080/giohang/{Username}", GioHang[].class,
////						params);
////				GioHang listgh[] = listg.getBody();
////				for (GioHang gh : listgh) {
////					ChiTietDH ct = new ChiTietDH();
////					ct.setIDDH(dh.getIDDH());
////					ct.setIDSP(gh.getIDSP());
////					ct.setSL(gh.getSL());
////					ct.setGia(gh.getGia());
////					rt = new RestTemplate();
//////					kq = Boolean.parseBoolean(rt.postForObject("http://localhost:8080/themchitietdh", ct, String.class));
////				}
//				// xóa giohang theo userZZ
//				params.put("Username", tk.getUsername());
//				System.out.println{"Username"};
//				rt.delete("http://localhost:8080//xoagiohang/{Username}",params);
//			
//				
////				String sub = "XÁC NHẬN ĐƠN HÀNG";
////				String content = "Xin chào " + khach.getHoTen() + "!\n Đơn hàng của bạn được đặt từ BEPSHOP vào ngày "
////						+ dh.getNgayGiaoDich() + ".\nTổng tiền: " + dh.getTongTien() + "\n Họ tên khách nhận hàng: "
////						+ dh.getHoTenNguoiNhan() + "\nGiao tại: " + dh.getDiaChi() + "\n\n Cảm ơn bạn đã tin tưởng các sản"
////						+ "phẩm của BEPSHOP. Chúng tôi sẽ phản hồi đơn hàng bạn sớm nhất có thể.\n Mọi chi tiết thắc mắc liên hệ website: BEPSHOP.com";
//////				EmailSenderServive.sendSimpleEmail(Email,sub, content);
////				sendMail(Email, sub, content);
////				triggerMail(Email,sub,content);
				
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/cancel";
	}

	private void updateGioHang(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}
