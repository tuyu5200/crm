package com.crm.service.base.impl;

import com.crm.dao.base.BaseDao;
import com.crm.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(t))
            throw new IllegalArgumentException("保存时参数不能为空");
        this.baseDao.save(t);
    }

    @Override
    public void delete(int id) {
        this.baseDao.delete(id);
    }

    @Override
    public void update(T t) {
        if (Objects.isNull(t))
            throw new IllegalArgumentException("更新时参数不能为空");
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
