package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.launchcode.marketplace.model.PurchaseOrder;

import java.util.List;

@Mapper
public interface PurchaseOrderMapper {

    PurchaseOrder selectPurchaseOrderById(int purchaseId);

    void insertPurchaseOrder(PurchaseOrder purchaseOrder);

    List<PurchaseOrder> getAllPurchaseOrders();

    int updatePurchaseOrder(PurchaseOrder purchaseOrder);

    int deletePurchaseOrder(int purchaseId);
}


