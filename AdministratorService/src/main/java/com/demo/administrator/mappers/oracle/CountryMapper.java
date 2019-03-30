package com.demo.administrator.mappers.oracle;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hclee
 * Mapper interface
 */
@Mapper
public interface CountryMapper {
	@Select("SELECT SYSDATE FROM DUAL")
	public String getCurrentDateTime();

	public String getCurrentDateTimeForQuery();
}