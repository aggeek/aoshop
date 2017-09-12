package com.aoshop.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.aoshop.common.ResponseCode;
import com.aoshop.common.ServerResponse;
import com.aoshop.dao.ShippingMapper;
import com.aoshop.pojo.Shipping;
import com.aoshop.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * author:liuao
 * description:
 * Date: create on 14:28 2017/9/8
 * modify by:
 *
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    public ServerResponse addShipping(Integer userId , Shipping shipping){
        if(userId==null|| shipping==null) {
            return  ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
       int count =  shippingMapper.insert(shipping);
        if(count>0){
            Map result= Maps.newHashMap();
            result.put("shippingId",shipping.getId());

            return  ServerResponse.createBySuccess("新增成功",result);
        }else {
            return  ServerResponse.createByErrorMessage("新增失败");
        }
    }
    public ServerResponse delete(Integer userId , Integer shippingId){
        if(userId==null|| shippingId==null) {
            return  ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
       int resultCount = shippingMapper.deleteByShippingIdUserId(shippingId,userId);
        if(resultCount>0){
            return  ServerResponse.createBySuccessMessage("删除成功");
        } else {
            return  ServerResponse.createByErrorMessage("删除失败");
        }

    }

    public ServerResponse update (Shipping shipping){
        if(shipping==null){
            return  ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int resultCount = shippingMapper.updateByShipping(shipping);
        if(resultCount>0){
            return  ServerResponse.createBySuccessMessage("修改地址成功");
        } else {
            return  ServerResponse.createByErrorMessage("新增地址失败");
        }
    }

    public ServerResponse select (Integer userId,Integer shippingId){

       Shipping shipping=  shippingMapper.selectByShippingIdUserId(userId,shippingId);
       if(shipping==null){
           return  ServerResponse.createByErrorMessage("无法查询到此地址");
       }
       return  ServerResponse.createBySuccess("查询成功",shipping);

    }
    public  ServerResponse<PageInfo> list(Integer userId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> list =shippingMapper.selectByUserId(userId);
        PageInfo pageInfo =new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
