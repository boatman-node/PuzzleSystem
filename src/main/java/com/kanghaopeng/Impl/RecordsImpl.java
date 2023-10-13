package com.kanghaopeng.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Records;
import com.kanghaopeng.entity.Users;

import java.sql.Date;
import java.util.List;

public interface RecordsImpl extends IService<Records> {
       ResponseResult RecordsBatchInsert(List<Records> recordsList);

       ResponseResult RecordSelectBatch(Records records,int pageNum, int pageSize);
}
