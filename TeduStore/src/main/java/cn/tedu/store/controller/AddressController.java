package cn.tedu.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/address")
@Controller
public class AddressController extends BaseController{

	/**
	 * 显示地址页面
	 * @return
	 */
	@RequestMapping("/showAddress.do")
	public String showAddress(){
		return "addressAdmin";
	}
}








