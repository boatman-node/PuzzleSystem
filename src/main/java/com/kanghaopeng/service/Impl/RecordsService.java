package com.kanghaopeng.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Records;
import com.kanghaopeng.mapper.RecordsMapper;
import com.kanghaopeng.service.RecordsImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecordsService extends ServiceImpl<RecordsMapper, Records> implements RecordsImpl {

    @Override
    public ResponseResult RecordsBatchInsert(List<Records> recordsList) {
        return null;
    }

    @Override
    public ResponseResult RecordSelectBatch(Records records, int pageNum, int pageSize) {
        return null;
    }
}
