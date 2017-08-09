package Phantom.ReverseGeocoding.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.locks.ReentrantLock;

//import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

//import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
//import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.stereotype.Service;

/**
 * @Project: CNPC_VMS
 * @Title: 系统公用组件
 * @Description: TODO 使使用自定义sql 进行分页查询 使用时请在service 方法集成此类可以直接调用此方法使用 spring
 *               注解的方式获取EntityManager 如果不能继承此方法可以直接new这个类进行查询
 *               使用原生jpa获取EntityManager 的方法
 * @author: lisj
 * @date: 2014年4月11日 上午8:41:51
 * @company: Beijing Huayou Information and Communication Technology Co.,Ltd
 * @Copyright: Copyright (c) 2014
 * @version V2.0
 */
// @Service
public class BaseJpaSearch {

	private static final Logger logger = Logger.getLogger(BaseJpaSearch.class);
	// @PersistenceContext(name = "VMS")
	// private static EntityManager entityManager = Persistence.createEntityManagerFactory("VMS").createEntityManager();
	// @PersistenceContext(name = "VMS")
	// private EntityManager entityManager;
	
	// @Autowired
	// @Resource(name="entityManagerFactory")
	private EntityManagerFactory factory;
	
		  
	// private ReentrantLock lock = new ReentrantLock();
	
	//private static final EntityManager entityManager;

	// @Autowired
	// @Resource(name="entityManagerFactory")
	// private static final EntityManagerFactory factory;
	
	/*static {
		factory = Persistence.createEntityManagerFactory("VMS");
		entityManager = factory.createEntityManager();
	}*/

	/**
	 * @param <T>
	 * @Description:TODO 使用自定义sql 进行分页查询 使用时请在service 方法集成此类可以直接调用此方法使用 spring
	 *                   注解的方式获取EntityManager 如果不能继承此方法可以直接new这个类进行查询
	 *                   使用原生jpa获取EntityManager 的方法
	 * @param : sql 要进行查询的sql
	 * @param : pageable 分页信息
	 * @param : Class c 进行分页查询返回的实体类的类型 如果没有相关jpa实体类请填入object 或者null
	 * @return T 的spring 分页查询结果
	 * @throws Exception
	 */

	public <T> Page<T> findBySQL(String sql, Pageable pageable,
			//public void findBySQL(String sql, Pageable pageable,
			@SuppressWarnings("rawtypes") Class c) throws Exception {

		// try{
		boolean isOrder = false;
		String orderSql = "";
		String standSql = "";
		String searchSql = "";
		String countSql = "";
		int count = 0;

		if (!(sql==null||sql.equals(""))) {
			String sqlstring = sql.toLowerCase();
			if (sqlstring.contains(" order ")) {
				if (sqlstring.contains("(")) {
					if (!(sqlstring.startsWith("(") && sqlstring.endsWith(")"))) {
						String isSqlstring = sqlstring.substring(
								sqlstring.lastIndexOf(")"), sqlstring.length());
						if (isSqlstring.contains(" order ")) {
							isOrder = true;
						}
					} else {
						String isSqlstring = sqlstring.substring(1,
								sqlstring.length()).substring(
								0,
								sqlstring.substring(1, sqlstring.length())
										.lastIndexOf(")"));

						isSqlstring = sqlstring.substring(
								sqlstring.lastIndexOf(")"), sqlstring.length());
						if (isSqlstring.contains(" order ")) {
							isOrder = true;
						}

					}
				} else {
					isOrder = true;
				}
			}
			if (!isOrder) {
				standSql = sql;
			} else {
				if (sqlstring.contains("(")) {
					if (!(sqlstring.startsWith("(") && sqlstring.endsWith(")"))) {
						sqlstring = sqlstring.substring(
								sqlstring.lastIndexOf(")"), sqlstring.length());
						// System.out.println(sqlstring);
						if (sqlstring.contains(" order ")) {
							orderSql = sqlstring.substring(
									sqlstring.lastIndexOf(" order "),
									sqlstring.length());
							int index = sql.toLowerCase()
									.lastIndexOf(" order ");
							standSql = sql.substring(0, index);
							// System.out.println(standSql);
						} else {
							standSql = sql;
						}
					} else {
						sqlstring = sqlstring.substring(1, sqlstring.length())
								.substring(
										0,
										sqlstring.substring(1,
												sqlstring.length())
												.lastIndexOf(")"));

						sqlstring = sqlstring.substring(
								sqlstring.lastIndexOf(")"), sqlstring.length());
						if (sqlstring.contains(" order ")) {
							orderSql = sqlstring.substring(
									sqlstring.lastIndexOf(" order "),
									sqlstring.length());
							standSql = sql.substring(0, sql.toLowerCase()
									.lastIndexOf(" order "));
						} else {
							standSql = sql;
						}
					}
				} else {
					orderSql = sqlstring.substring(
							sqlstring.lastIndexOf(" order "),
							sqlstring.length());
					standSql = sql.substring(0,
							sqlstring.lastIndexOf(" order "));
				}

			}

			HashMap<String, String> map = new HashMap<String, String>();
			if (c != null && c != Object.class && c != String.class) {
				if (pageable != null) {
					if (pageable.getSort() != null) {
						Iterator<Order> orderIterator = pageable.getSort()
								.iterator();

						while (orderIterator.hasNext()) {
							Order order = orderIterator.next();
							String property = order.getProperty();
							String direction = order.getDirection().toString();
							property = getColunm(property, c);
							map.put(property, direction);
						}
					}
				}
			}

			if (isOrder) {
				if (!map.isEmpty()) {
					for (String key : map.keySet()) {
						orderSql = orderSql + " " + "and " + key + "  "
								+ map.get(key);
					}
				}
			} else {
				if (!map.isEmpty()) {
					if (map.size() > 1) {
						for (String key : map.keySet()) {

							orderSql = key + "  " + map.get(key) + " and ";
						}

						orderSql = "order by "
								+ orderSql.substring(0,
										orderSql.lastIndexOf(" and "));
					}

				}
			}
			int number = pageable.getPageNumber();
			int size = pageable.getPageSize();

			// searchSql= standSql+" " + orderSql ;
			searchSql = "select * from (select row_.*, rownum rownum_ from ("
					+ standSql + " " + orderSql + " "
					+ ") row_ ) where  rownum_ >" + (number * size)
					+ " and rownum_<= " + ((number + 1) * size) + "";

			countSql = "select count(*) count_info_1 from (" + standSql + " "
					+ " " + orderSql + " " + ")";
			// System.out.println("查询的sql语句为" + searchSql);
			/*
			 * if(entityManager==null) {
			 * System.out.println("entityManager 没有初始化"); entityManager=
			 * Persistence
			 * .createEntityManagerFactory("VMS").createEntityManager(); }
			 */
			Query query;
			List<T> list = new ArrayList<T>();
			EntityManager entityManager = null;
			try {
				// checkEntityManager();
				entityManager = factory.createEntityManager();
				Query countQuery = entityManager.createNativeQuery(countSql);
				List<BigDecimal> result = countQuery.getResultList();
				if (result != null && result.size() > 0) {
					count = result.get(0).intValue();
				}
				
				if (c != null && c != Object.class && c != String.class) {
					query = entityManager.createNativeQuery(searchSql, c);
				} else {
					query = entityManager.createNativeQuery(searchSql);
				}
				
				// int index = (pageable.getPageNumber()) *
				// pageable.getPageSize();
				// query.setMaxResults(pageable.getPageSize()).setFirstResult(index);
				list = query.getResultList();
			} finally {
				entityManager.close();
			}

			// System.out.println("++++++++++++++++++++++++++++++++++++++"+count);
			Page<T> listpage = new PageImpl<T>(list, pageable, count);

			return listpage;
		} else {
			return null;
		}
	}

	/**
	 * @Description:通过反射获取实体类的@Colunm 注解
	 * @param :property 实体类的属性名称
	 * @param :c 实体类的type
	 * @return 实体类 属性的注解内的name
	 * @throws Exception
	 */
	public static String getColunm(String property,
			@SuppressWarnings("rawtypes") Class c) {
		String result = "";
		String mothodName = "";
		mothodName = "get" + property.substring(0, 1).toUpperCase()
				+ property.substring(1, property.length());
		try {
			@SuppressWarnings("unchecked")
			Method m = c.getMethod(mothodName);
			if (m != null) {
				Annotation[] aa = m.getAnnotations();
				if (aa != null && aa.length > 0) {
					for (int i = 0; i < aa.length; i++) {
						String annotationName = aa[i].annotationType()
								.getName();
						if (annotationName == javax.persistence.Column.class
								.getName()) {
							result = aa[i]
									.toString()
									.substring(
											aa[i].toString().indexOf(" name="),
											aa[i].toString().length())
									.substring(
											0,
											aa[i].toString()
													.substring(
															aa[i].toString()
																	.indexOf(
																			" name="),
															aa[i].toString()
																	.length())
													.indexOf(","))
									.replace(" name=", "");

						}
					}
				}
			}
		} catch (NoSuchMethodException e) {

			e.printStackTrace();
		} catch (SecurityException e) {

			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @Description:使用自定义sql 进行分页查询 使用时请在service 方法集成此类可以直接调用此方法使用 spring
	 *                       注解的方式获取EntityManager 如果不能继承此方法可以直接new这个类进行查询
	 *                       使用原生jpa获取EntityManager 的方法
	 * @param : sql 要进行查询的sql
	 * @return 返回 查询的object数组列表对象
	 * @throws Exception
	 */
	public List<Object[]> findBySQL(String sql) throws Exception {
		// checkEntityManager();
		EntityManager entityManager = null;
		try {
			/*if(null == entityManager){
				entityManager = factory.createEntityManager();
			}*/
			entityManager = factory.createEntityManager();
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			return list;
		} finally {
			// entityManager.flush();
			entityManager.close();
		}

	}

	/**
	 * @Description:TODO 根据findBySQL方法，改造结果，使其含有字段编码，方便页面显示
	 * @Description:根据findBySQL方法，改造结果，使其含有字段编码，方便页面显示
	 * @param : sql 要进行查询的sql
	 * @param : pageable 分页信息
	 * @param ：<T> 泛型（实体类）
	 * @return : 返回带字段编码的结果
	 * @throws Exception
	 */
	public <T> Page<Map<String, Object>> findBySQLAndName(String sql,
			Pageable pageable, String[] names) throws Exception {
		List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();

		Page<Object[]> page = findBySQL(sql, pageable, Object.class);
		try {

			List<Object[]> list = page.getContent();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = list.get(i);
				Map<String, Object> maptemp = new HashMap<String, Object>();
				for (int j = 0; j < names.length; j++) {
					maptemp.put(names[j], obj[j]);
				}
				resultlist.add(maptemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Page<Map<String, Object>> listpage = new PageImpl<Map<String, Object>>(
				resultlist, pageable, page.getTotalElements());
		return listpage;
	}

	/**
	 * @Description:使用自定义sql执行更新操作
	 * @param :sql 要执行update的sql
	 * @return 返回影响的行数
	 * @throws Exception
	 */
	public int updateSql(String sql) {
		// EntityManager em = factory.createEntityManager();
		// checkEntityManager();
		int res = 0;
		/*if (entityManager == null) {
			logger.info("creating new entirymanager");
			if (null == factory) {
				logger.info("create new factory...");
				factory = Persistence.createEntityManagerFactory("VMS");
			}
			entityManager = factory.createEntityManager();
		}*/
		EntityManager entityManager = null;
		
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			Query update = entityManager.createNativeQuery(sql);
			res = update.executeUpdate();
			entityManager.getTransaction().commit();
		} finally {
			// entityManager.flush();
			// entityManager.close();
		}
		return res;
	}
	
	/*private void checkEntityManager(){
		
		
		if (entityManager == null) {
			if(lock.tryLock()){
				try{
					entityManager = factory.createEntityManager();
					logger.info("初始化entityManager");
				} finally{
					lock.unlock();
				}
			} else {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}*/

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		logger.info("初始化EntityManager");
		this.factory = factory;
	}
	
	
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
}
