package com.kanghaopeng.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kanghaopeng.Enum.AppHttpCodeEnum;
import com.kanghaopeng.Impl.RecordsImpl;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Records;
import com.kanghaopeng.mapper.RecordsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService extends ServiceImpl<RecordsMapper, Records> implements RecordsImpl {
    @Override
    public ResponseResult RecordsBatchInsert(List<Records> recordsList) {
        return baseMapper.RecordsBatchInsert(recordsList)>0?
                new ResponseResult<>().ok(AppHttpCodeEnum.SUCCESS.getCode(),
                        recordsList,"操作成功！"):ResponseResult.errorResult(AppHttpCodeEnum.FAIL);
    }
}
