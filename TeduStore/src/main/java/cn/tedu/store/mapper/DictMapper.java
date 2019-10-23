package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface DictMapper {
	/**
	 * 通过编号获取区县的名称
	 * @param areaCode
	 * @return
	 */
	String selectAreaNameByCode(String areaCode);
	/**
	 * 通过编号获取城市名称
	 * @param cityCode
	 * @return
	 */
	String selectCityNameByCode(String cityCode);
	/**
	 * 通过编号获取省名称
	 * @param provinceCode
	 * @return
	 */
	String selectProvinceNameByCode(String provinceCode);
	
	/**
	 * 查询区县的信息，封装成Area对象
	 * 通过list返回
	 * @param cityCode
	 * @return
	 */
	List<Area> selectArea(String cityCode);
	/**
	 * 查询城市信息，封装成city对象
	 * 通过list返回
	 * @param provinceCode
	 * @return
	 */
	List<City> selectCity(String provinceCode);
	/**
	 * 查询省信息，封装成province对象，
	 * 通过list返回
	 * @return
	 */
	List<Province> selectProvince();

}
