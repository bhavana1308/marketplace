package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.launchcode.marketplace.model.CartList;

import java.util.List;

@Mapper
public interface CartListMapper {

    CartList getByCartListId(int id);

    List<CartList> getAllFromCartList();

    int insertCartList(CartList cartList);

    int updateCartList(CartList cartList);

    void updateCartItemsOrderNumber(String orderNumber);

    int deleteByCartListId(int id);

    List<CartList> getCartItemsWithProducts();

    List<CartList> getCartItemsByOrderNumber(String orderNumber);

}
