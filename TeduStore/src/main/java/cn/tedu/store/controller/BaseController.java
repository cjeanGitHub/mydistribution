package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import cn.tedu.store.bean.User;
//获取登录的用户id值
public class BaseController {
	public Integer getId(HttpSession session){
		//获取session中的user 对象
		User user = (User)session.
				getAttribute("user");
		if(user==null){
			return null;
		}else{
			//返回userid
			return user.getId();
		}
	}

}




