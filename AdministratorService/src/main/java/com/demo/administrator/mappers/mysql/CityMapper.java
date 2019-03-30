package com.demo.administrator.mappers.mysql;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hclee
 * Mapper interface
 * TODO [완료]구조를 구현체 까지 가져갈 것인지 고민 필요.
 */
@Mapper
public interface CityMapper {
	@Select("SELECT NOW()")
	public String getCurrentDateTime();

	public String getCurrentDateTimeForQuery();
}