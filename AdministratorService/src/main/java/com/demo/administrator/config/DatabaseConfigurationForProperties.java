package com.demo.administrator.config;



public class DatabaseConfigurationForProperties {
		/*// 설정을 한 클래스에 몰아 넣기위해 클래스 안에 클래스를 만듬.
		@Configuration
		// 다수의 데이터 연결이 있다면, 규정상 매핑여부와 상관없이 한쪽 @Bean 의 접근자별 @Primary 를 강제로 지정해야함.
		@EnableTransactionManagement
		@MapperScan(basePackages ="com.demo.administrator.mappers")
		public static class Mybatis_MySQL
		{
			*//**
			 * mybatis sqlsessionfactory 
			 * @param dataSource
			 * @return
			 * @throws Exception
			 *//*
			@Bean
			public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception
			{
				final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
				sessionFactory.setDataSource(dataSource);
				PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
				sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper//*.xml"));
				return sessionFactory.getObject();
			}
	 
			*//**
			 * mybatis sqlsessiontemplate
			 * @param sqlSessionFactory
			 * @return
			 *//*
			@Bean
			public SqlSessionTemplate getSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
				final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
				return sqlSessionTemplate;
			}
		}*/
}
