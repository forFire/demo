package com.flyer.mapper;

import com.flyer.domain.Demo;
import com.flyer.domain.DemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DemoMapper {
    int countByExample(DemoExample example);

    int deleteByExample(DemoExample example);

    int insert(Demo record);

    int insertSelective(Demo record);

    List<Demo> selectByExampleWithRowbounds(DemoExample example, RowBounds rowBounds);

    List<Demo> selectByExample(DemoExample example);

    int updateByExampleSelective(@Param("record") Demo record, @Param("example") DemoExample example);

    int updateByExample(@Param("record") Demo record, @Param("example") DemoExample example);
}