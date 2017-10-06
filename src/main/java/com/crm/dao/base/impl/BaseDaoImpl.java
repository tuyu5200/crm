package com.crm.dao.base.impl;

import com.crm.dao.base.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@SuppressWarnings("all")
@Transactional(readOnly = true)
@Repository
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    protected Class<T> clazz;

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(T t) {
        if (t == null) {
            throw new IllegalArgumentException("参数不正确");
        }
        this.getSession().save(t);
        this.getSession().flush();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        this.getSession().delete(this.queryById(id));
        //加上这句就可以了，直接提交到数据库，不对缓存进行操作。不然就是对缓存进行操作。
        getSession().flush();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T t) {
        if (t == null) {
            throw new IllegalArgumentException("参数不正确");
        }
        this.getSession().update(t);
        //加上这句就可以了，直接提交到数据库，不对缓存进行操作。不然就是对缓存进行操作。
        this.getSession().flush();
    }

    public List<T> queryAll() {
        return (List<T>) this.getSession().createCriteria(this.clazz).list();
    }

    public T queryById(int id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("参数不正确");
        }
        return (T) this.getSession().get(this.clazz, id);
    }

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        // 处理T的原始类型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 得到类上面的尖括号中的数据类型 是一个数组
        Type[] types = type.getActualTypeArguments();
        if (types.length != 1) {
            throw new IllegalArgumentException("参数不能为空");
        }

        this.clazz = (Class<T>) types[0];
    }

    @Override
    public List<T> queryByIds(Integer[] ids) {
        String clazzName = this.clazz.getSimpleName();
        if (ids == null || ids.length < 1) {
            throw new IllegalArgumentException("参数不正确");
        }
        return this.getSession().createQuery("from " + clazzName + " t where t.id in (:ids)").setParameterList("ids", ids).list();
    }
}
