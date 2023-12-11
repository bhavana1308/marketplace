package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.launchcode.marketplace.model.OrderDetail;

@Mapper
public interface OrderDetailMapper {

    void insertOrderDetail(OrderDetail orderDetail);

    OrderDetail getOrderDetailByOrderNumber(String orderNumber);


}
