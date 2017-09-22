package Phantom.Data.Initiation.Dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

 
/**
 * @Project: Phantom
 * @Title: 数据分析服务
 * @Description: <此接口只是为了显示springJPA实现的标准方法，每个DAO子接口应该继承它，使使用者更加清楚, 另外请确保此接口不是一个RepositoryBean>
 * @author: jiabotao
 * @date: 2017年3月23日 下午2:31:37
 * @company: 
 * @Copyright: Copyright (c) 2017
 * @version V1.0
 */

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends
		Repository<T, ID> {
	/**
	 * @Description: <添加或修改> <如果实体主键值存在，则会根据主键去更新数据，否则为新增记录>
	 * @param entity 实体类
	 * @return T 返回带ID的实体
	 * @throws Exception
	 */
	T save(T entity);

	/**
	 * @Description:<批量添加或更新> <添加或更新依据实体中的主键是否已经存在>
	 * @param entities  实体集合
	 * @return List<T> 返回保存信息对象的集合 
	 * @throws Exception
	 */
	List<T> save(Iterable<? extends T> entities);

	/**
	 * @Description:<根据主键查询实体>
	 * @param id   主键
	 * @return T 实体对象
	 * @throws Exception
	 */
	T findOne(ID id);

	/**
	 * @Description:<根据id判断实体是否存在>
	 * @param id  主键
	 * @return boolean 是否存在
	 * @throws Exception
	 */
	boolean exists(ID id);

	/**
	 * @Description: <查询所有实体> <此方法查询所有实体，如果数据量过大，请慎用>
	 * @param
	 * @return Iterable<T> 实体集合
	 * @throws Exception
	 */
	Iterable<T> findAll();

	/**
	 * @Description: <查询实体总数>
	 * @param
	 * @return long 实体总数
	 * @throws Exception
	 */
	long count();

	/**
	 * @Description:<根据主键删除实体>
	 * @param id 主键
	 * @return void
	 * @throws Exception
	 */
	void delete(ID id);

	/**
	 * @Description:<删除实体> <内部也是通过主键删除>
	 * @param entitie :要删除的实体类
	 * @return void
	 * @throws Exception
	 */
	void delete(T entity);

	/**
	 * @Description:<删除实体集合> <根据实体的主键去删除，此方法提交多条删除语句，效率欠佳>
	 * @param entities :要删除的实体类结合
	 * @return void
	 * @throws Exception
	 */
	void delete(Iterable<? extends T> entities);

	/**
	 * @Description:<排序查询所有实体> <关键在于Sort的构造，不同的构造满足不同的排序需求>
	 * @param :排序参数示例 ：sort = new Sort(Direction.DESC,"id","cityCode");
	 * @return Iterable<T> 实体类集合
	 * @throws Exception
	 */
	Iterable<T> findAll(Sort sort);

	/**
	 * @Description: 查询所有，可分页排序
	 * @param 分页信息对象pageable
	 * @return Page<T> 分页结果
	 * @throws Exception
	 */
	Page<T> findAll(Pageable pageable);

	/**
	 * @Description:多条件组合查询
	 * @param : specification
	 * @param : pageable 分页参数
	 * @return Page<T> 分页结果
	 * @throws Exception
	 */
	public Page<T> findAll(Specification<T> specification, Pageable pageable);

	/**
	 * @Description:批量删除
	 * @param :arg0 需要删除的实体类集合
	 * @return void
	 * @throws Exception
	 */
	public void deleteInBatch(Iterable<T> arg0);

	/**
	 * @Description:删除所有实体类
	 * @param :
	 * @return void
	 * @throws Exception
	 */
	public void deleteAll();
}

