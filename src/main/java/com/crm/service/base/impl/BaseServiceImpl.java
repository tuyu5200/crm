package com.crm.service.base.impl;

import com.crm.dao.base.BaseDao;
import com.crm.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@SuppressWarnings("all")
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void save(T t) {
        this.baseDao.save(t);
    }

    @Override
    public void delete(int id) {
        this.baseDao.delete(id);
    }

    @Override
    public void update(T t) {
        this.baseDao.update(t);
    }

    @Override
    public List<T> queryAll() {
        return this.baseDao.queryAll();
    }

    @Override
    public T queryById(int id) {
        return (T) this.baseDao.queryById(id);
    }
}
