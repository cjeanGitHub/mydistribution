package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface IDictService {
	/**
	 * 返回AreaList集合
	 * @param cityCode
	 * @return
	 */
	List<Area> getArea(String cityCode);
	/**
	 * 返回CityList集合
	 * @param provinceCode
	 * @return
	 */
	List<City> getCity(String provinceCode);
	/**
	 *返回ProvinceList集合
	 * @return
	 */
	List<Province> getProvince();
	
	String LoginMes(String name);

}
