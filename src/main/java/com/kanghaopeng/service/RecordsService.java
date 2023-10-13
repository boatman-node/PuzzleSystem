package com.kanghaopeng.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.kanghaopeng.Enum.AppHttpCodeEnum;
import com.kanghaopeng.Impl.RecordsImpl;
import com.kanghaopeng.dtos.PageResponseResult;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Records;
import com.kanghaopeng.entity.Users;
import com.kanghaopeng.mapper.RecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class RecordsService extends ServiceImpl<RecordsMapper, Records> implements RecordsImpl {
    /*插入做题数据*/
    @Override
    public ResponseResult RecordsBatchInsert(List<Records> recordsList) {
        return baseMapper.RecordsBatchInsert(recordsList)>0?
                new ResponseResult<>().ok(AppHttpCodeEnum.SUCCESS.getCode(),
                        recordsList,"操作成功！"):ResponseResult.errorResult(AppHttpCodeEnum.FAIL);
    }

    @Autowired
    Gson gson;
    @Override
    public ResponseResult RecordSelectBatch(Records records,int pageNum, int pageSize) {
        LambdaQueryWrapper<Records> recordsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(records.getId()!=null){
            recordsLambdaQueryWrapper.eq(Records::getUserId,records.getUserId());
        }else if(records.getTimestamp()!=null){
            recordsLambdaQueryWrapper.eq(Records::getTimestamp,records.getTimestamp());
        }else if (records.getIsCorrect()!=null){
            recordsLambdaQueryWrapper.eq(Records::getIsCorrect,records.getIsCorrect());
        }else if(records.getQuestionId()!=null){
            recordsLambdaQueryWrapper.eq(Records::getQuestionId,records.getQuestionId());
        }
        return ResponseResult.okResult(baseMapper.selectPage(new Page<Records>(pageNum,pageSize),recordsLambdaQueryWrapper));
    }

}
