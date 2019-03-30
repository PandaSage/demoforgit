package com.demo.administrator.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.demo.administrator.base.object.CmMap;
import com.demo.administrator.base.utils.CmFunction;

import lombok.extern.slf4j.Slf4j;

/**
 * common data Access object
 * @author hclee
 */
@Slf4j
@Repository
public class CmMysqlDao extends SqlSessionDaoSupport {
	//mapper(xml) default package
	private final static String _query_path = "com.demo.administrator.mappers.mysql.";
	
	@Override
	@Resource(name = "SqlSessionFactoryForMySql")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override 
	@Resource(name = "SqlSessionTemplateForMySql")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	private String getLanguage() {
		String result = "";
		try {
			HttpServletRequest request = CmFunction.getCurrentRequest();
			
			String language = CmFunction.getStringValue(request.getSession().getAttribute("language"));
			if ( !language.equals("") && !language.equals("ko") ) {
				result = "_" + language;
			}
		} catch (Exception e) {
		}
		return result;
	}
	
	/**
	 * query id값 반환 method
	 * @param queryId
	 * @return
	 */
	private String getQueryId(String queryId) {
		log.debug(queryId);
		return _query_path + queryId;
	}

	/**
	 * Insert
	 * @param queryId
	 * @param reqVo
	 * @return insert row count
	 */
	public int insert(String queryId, CmMap reqVo){
		reqVo.put("_language", this.getLanguage());
		return super.getSqlSession().insert(this.getQueryId(queryId), reqVo);
	}
	/**
	 * update
	 * @param queryId
	 * @param reqVo
	 * @return update row count
	 */
	public int update(String queryId, CmMap reqVo){
		reqVo.put("_language", this.getLanguage());
		return super.getSqlSession().update(this.getQueryId(queryId), reqVo);
	}
	/**
	 * delete
	 * @param queryId
	 * @param reqVo
	 * @return delete row count
	 */
	public int delete(String queryId, CmMap reqVo){
		reqVo.put("_language", this.getLanguage());
		return super.getSqlSession().delete(this.getQueryId(queryId), reqVo);
	}
	/**
	 * one Object mapping select value, No parameter
	 * @param queryId
	 * @return Object
	 */
	public Object selectOne(String queryId){
		return super.getSqlSession().selectOne(this.getQueryId(queryId));
	}
	/**
	 * one String Object mapping select value, with parameter, No parameter
	 * @param queryId
	 * @return String
	 */
	public String selectOneToString(String queryId){
		return super.getSqlSession().selectOne(this.getQueryId(queryId)).toString();
	}
	/**
	 * one Object mapping select value, with parameter
	 * @param queryId
	 * @param reqVo
	 * @return Object
	 */
	public Object selectOne(String queryId, CmMap reqVo){
		reqVo.put("_language", this.getLanguage());
		return super.getSqlSession().selectOne(this.getQueryId(queryId), reqVo);
	}
	/**
	 * one String Object mapping select value, with parameter
	 * @param queryId
	 * @param reqVo
	 * @return Object
	 */
	public String selectOneToString(String queryId, CmMap reqVo){
		reqVo.put("_language", this.getLanguage());
		return super.getSqlSession().selectOne(this.getQueryId(queryId), reqVo).toString();
	}
	/**
	 * Multi Object mapping select value, No parameter
	 * @param queryId
	 * @return List<Object>
	 */
	public List<Object> selectList(String queryId){
		return super.getSqlSession().selectList(this.getQueryId(queryId));
	}
	/**
	 * Multi Object mapping select value, with parameter
	 * @param queryId
	 * @param reqVo
	 * @return List<Object>
	 */
	public List<Object> selectList(String queryId, CmMap reqVo){
		reqVo.put("_language", this.getLanguage());
		return super.getSqlSession().selectList(this.getQueryId(queryId),reqVo);
	}
}
