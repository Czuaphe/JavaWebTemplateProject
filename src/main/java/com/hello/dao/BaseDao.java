package com.hello.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseDao<T> {

    private Class<T> entityClass;

    @Autowired
    private HibernateTemplate hibernateTemplate;
    /**
     * 通过反射获取子类确定的泛型类
     */
    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    // hibernate静态查询接口，使用HQL
    protected HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    // JPA动态查询接口，
    protected EntityManager getEntityManager() {
        return hibernateTemplate.getSessionFactory().createEntityManager();
    }
    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    /**
     * 根据ID加载PO实例
     *
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public T load(Serializable id) {
        return getSession().load(entityClass, id);
    }

    /**
     * 根据ID获取PO实例
     *
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public T get(Serializable id) {
        return getSession().get(entityClass, id);
    }

    /**
     * 获取PO的所有对象
     *
     * @return List
     */
    public List<T> loadAll() {
        return getHibernateTemplate().loadAll(entityClass);
    }

    /**
     * 保存PO
     *
     * @param entity
     */
    public void save(T entity) {
        getSession().save(entity);
    }

    /**
     * 删除PO
     *
     * @param entity
     */
    public void remove(T entity) {
        getSession().delete(entity);
    }

    /**
     * 删除tableNames数据，不能回滚，不能恢复，小心使用。
     *
     */
    public void removeAll(String tableName) {
        getSession().createSQLQuery("truncate TABLE " + tableName +"").executeUpdate();
    }

    /**
     * 更改PO
     *
     * @param entity
     */
    public void update(T entity) {
        getSession().update(entity);
    }

    /**
     * 执行HQL查询
     *
     * @param hql
     * @return 查询结果
     */
    public List find(String hql) {
        return this.getHibernateTemplate().find(hql);
    }

    /**
     * 执行带参的HQL查询
     *
     * @param hql
     * @param params
     * @return 查询结果
     */
    public List find(String hql, Object... params) {
        return this.getHibernateTemplate().find(hql,params);
    }

    /**
     * 对延迟加载的实体PO执行初始化
     * @param entity
     */
    public void initialize(Object entity) {
        this.getHibernateTemplate().initialize(entity);
    }


}
