package com.kaishengit.dao;

import com.kaishengit.pojo.Customer;
import com.kaishengit.util.orm.Condition;
import com.kaishengit.util.orm.Page;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseDao<T,PK extends Serializable> {

    private Class<?> clazz;

    public BaseDao() {
        clazz = this.getClass();
        ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
        Type [] classes = type.getActualTypeArguments();
        clazz = (Class<?>) classes[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public T findById(PK id) {
        return (T) getSession().get(clazz,id);
    }
    public void deleteById(T entity) {
        getSession().delete(entity);
    }

    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(clazz);
        return criteria.list();
    }

    public List<T> findByProperty(String property,Object value) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.add(Restrictions.eq(property,value));
        List<T> customerList = criteria.list();
        return customerList;
    }

    public Long count() {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }
    /**
     * 根据条件查询总数据条数
     * @param conditions
     * @return
     */
    protected Long countByCondition(Criteria criteria,Condition...conditions) {
        for(Condition condition : conditions) {
            criteria.add(getCriterionByCondition(condition));
        }

        ResultTransformer resultTransformer = criteria.ROOT_ENTITY;//获取之前查询的列

        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(resultTransformer);
        return count;
    }

    /**
     * 总数据分页
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    public Page<T> findByPageNum(Integer currentPageNum,Integer pageSize) {
        //获取总条数
        int totalNum = count().intValue();
        Page<T> page = new Page<>(pageSize,totalNum,currentPageNum);
        Criteria criteria = getSession().createCriteria(clazz);

        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(pageSize);
        List<T> items = criteria.list();

        page.setItems(items);
        return page;
    }

    /**
     * 根据查询条件分页
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    public Page<T> findByPageNumAndCondition(Integer currentPageNum,Integer pageSize,Condition...conditions) {
        Criteria criteria = getSession().createCriteria(clazz);
        return findByPageNumAndCondition(criteria,currentPageNum,pageSize,conditions);
    }
    public Page<T> findByPageNumAndCondition(Criteria criteria, Integer currentPageNum, Integer pageSize, Condition[] conditions) {
        //获取总条数
        int totalNum = countByCondition(criteria,conditions).intValue();
        Page<T> page = new Page<>(pageSize,totalNum,currentPageNum);

        for(Condition condition : conditions) {
            criteria.add(getCriterionByCondition(condition));
        }
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(pageSize);
        List<T> items = criteria.list();

        page.setItems(items);
        return page;
    }


    /**
     * 分页，排序，查询
     * @param currentPageNum
     * @param pageSize
     * @param propertyName
     * @param orderType
     * @param conditions
     * @return
     */
    public Page<T> findByPageNumAndConditionWithSort(Criteria criteria,Integer currentPageNum,Integer pageSize,String propertyName,String orderType,Condition...conditions) {
        //获取总条数
        int totalNum = countByCondition(criteria,conditions).intValue();
        Page<T> page = new Page<>(pageSize,totalNum,currentPageNum);
        for(Condition condition : conditions) {
            criteria.add(getCriterionByCondition(condition));
        }
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(pageSize);
        if("desc".equalsIgnoreCase(orderType)) {
            criteria.addOrder(Order.desc(propertyName));
        }
        List<T> items = criteria.list();

        page.setItems(items);
        return page;
    }
    public Page<T> findByPageNumAndConditionWithSort(Integer currentPageNum,Integer pageSize,String propertyName,String orderType,Condition...conditions) {
        Criteria criteria = getSession().createCriteria(clazz);
        return findByPageNumAndConditionWithSort(criteria,currentPageNum,pageSize,propertyName,orderType,conditions);
    }


    /**
     * 根据一个条件查找
     * @param condition
     * @return
     */
    public List<T> findByCondition(Condition condition) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.add(getCriterionByCondition(condition));
        List<T> customerList = criteria.list();
        return customerList;
    }

    /**
     * 多条件查询
     * @param conditions
     * @return
     */
    public List<T> findByConditions(Condition...conditions) {
        Criteria criteria = getSession().createCriteria(clazz);
        for(Condition condition : conditions) {
            criteria.add(getCriterionByCondition(condition));
        }
        List<T> customerList = criteria.list();
        return customerList;
    }

    /**
     * 将condition对象转换为Criterion对象
     * @param condition
     * @return
     */
    protected Criterion getCriterionByCondition(Condition condition) {
        String propertyName = condition.getPropertyName();
        Object value = condition.getValue();
        String type = condition.getType();
        if(propertyName.contains("_or_")) {
            String [] propertyNames = propertyName.split("_or_");

            Disjunction disjunction = Restrictions.disjunction();
            for(String name : propertyNames) {
                disjunction.add(getCriterion(name,value,type));
            }
            return disjunction;
        }else {
            return getCriterion(propertyName,value,type);
        }
    }

    private Criterion getCriterion(String propertyName,Object value,String type) {
        if(type.equalsIgnoreCase("eq")) {
            return Restrictions.eq(propertyName,value);
        } else if(type.equalsIgnoreCase("gt")) {
            return Restrictions.gt(propertyName,value);
        } else if(type.equalsIgnoreCase("lt")) {
            return Restrictions.lt(propertyName,value);
        } else if(type.equalsIgnoreCase("ge")) {
            return Restrictions.ge(propertyName,value);
        } else if(type.equalsIgnoreCase("le")) {
            return Restrictions.le(propertyName,value);
        } else if(type.equalsIgnoreCase("like")) {
            return Restrictions.like(propertyName,value.toString(), MatchMode.ANYWHERE);
        }
        return null;
    }
}
