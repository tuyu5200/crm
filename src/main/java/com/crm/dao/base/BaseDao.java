package com.crm.dao.base;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */

public interface BaseDao<T> {

    void save(T t);

    void delete(int id);

    void update(T t);

    List<T> queryAll();

    T queryById(int id);
}
