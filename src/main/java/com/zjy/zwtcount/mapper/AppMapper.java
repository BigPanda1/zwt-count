package com.zjy.zwtcount.mapper;

import com.zjy.zwtcount.entity.Cvs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Date 2020/4/1 9:53
 * @Created by zjy
 */
public interface AppMapper {
    List<Cvs> selAll();
    void delAll();

    void addListCvs(@Param("list")List<Cvs> list);
}
