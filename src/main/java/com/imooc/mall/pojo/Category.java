package com.imooc.mall.pojo;

import lombok.Data;

import java.util.Date;

//lombok引入注解
@Data
public class Category {

    private Integer id;//类别Id/
    private Integer parentId;//父类别id当id=0时说明是根节点,一级类别
    private String name;//类别名称
    private Integer status;//类别状态1-正常,2-已废弃
    private Integer sortOrder;//排序编号,同类展示顺序,数值相等则自然排序
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
}
