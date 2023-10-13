package com.kanghaopeng.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Records;

import java.util.List;

public interface RecordsImpl extends IService<Records> {
       ResponseResult RecordsBatchInsert(List<Records> recordsList);
}
