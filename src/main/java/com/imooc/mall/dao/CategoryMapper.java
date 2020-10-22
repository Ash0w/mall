package com.imooc.mall.dao;

import com.imooc.mall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//@Mapper 自动扫描mapper注解加到MallApplication
@Repository
public interface CategoryMapper {

    @Select("select * from mall_category where id = #{id}")
    Category findById(@Param("id") Integer id);

    Category queryById(Integer id);
}
