package com.mzt.toolkit.mapper;


import com.mzt.toolkit.domain.LogRecord;

import java.util.List;

public interface LogRecordMapper {

    int insertSelective(LogRecord record);

    LogRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogRecord record);

    List<LogRecord> queryByBizKey(String bizKey);

    List<LogRecord> queryByBizNo(String bizNo);
}