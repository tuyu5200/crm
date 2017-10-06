package com.crm.dao.base;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */

public interface BaseDao<T> {
    /**
     * 增加
     *
     * @param t
     */
    void save(T t);

    /**
     * 根据id删除
     *
     * @param id
     */
    void delete(int id);

    /**
     * 更新
     *
     * @param t
     */
    void update(T t);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> queryAll();

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    T queryById(int id);

    /**
     * 通过给定的id集合查询所有的对象
     *
     * @param ids
     * @return
     */
    List<T> queryByIds(Integer[] ids);
}
