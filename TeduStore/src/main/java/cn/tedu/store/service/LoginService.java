package cn.tedu.store.service;



public class LoginService implements ILoginService{
	
	public String LoginMes(String name) {
		String mes = null;
		if("12".equals(name)) {
			mes = "success";
		}else {
			mes = "fail";
		}
		
		return mes;
	}

}
