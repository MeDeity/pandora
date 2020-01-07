package com.mengya.log.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 定义自己的Mapper,所有的Mapper都集成该接口
 * 特别注意，该接口不能被扫描到，否则会出错，这也是为什么要定义在外面的缘故
 * @param <T> 泛型类
 * create by fengwenhua at 2019-11-12 18:02:32
 */
public interface BaseTkMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
