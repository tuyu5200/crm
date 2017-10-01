package com.crm.service.base;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
public interface BaseService<T> {
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
     * 更新，修改
     *
     * @param t
     */
    void update(T t);

    /**
     * 查询全部
     *
     * @return
     */
    List<T> queryAll();

    /**
     * 通过id查询单个对象
     *
     * @param id
     * @return
     */
    T queryById(int id);

}
