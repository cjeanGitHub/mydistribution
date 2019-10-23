package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Address;
import cn.tedu.store.mapper.AddressMapper;

public class TestAddress {
	@Test
	public void testInsert(){
		ApplicationContext ac = new 
				ClassPathXmlApplicationContext(
						"spring-dao.xml",
						"spring-service.xml");
		AddressMapper am = ac.getBean("addressMapper",
				AddressMapper.class);
		Address address = new Address();
		address.setUid(2);
		address.setRecvName("小王");
		address.setRecvProvince("130000");
		address.setRecvCity("130100");
		address.setRecvArea("130102");
		address.setRecvDistrict("河北省石家庄市长安区");
		address.setRecvAddress("中鼎大厦8层");
		address.setRecvPhone("13800138000");
		am.insert(address);
	}
}





