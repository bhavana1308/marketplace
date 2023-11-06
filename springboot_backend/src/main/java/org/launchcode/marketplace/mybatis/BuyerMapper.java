package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.launchcode.marketplace.model.Buyer;

import java.util.List;

@Mapper
public interface BuyerMapper {

    List<Buyer> getAllFromBuyer();

    int insertBuyer(Buyer buyer);

    int updateBuyer(Buyer buyer);

    int getBuyerByEmailAndPassword(String email, String password);

    int updateBuyerLoyaltyPoints(int buyerId, int loyaltyPoints);

    Buyer getBuyerById(int id);

    int deleteBuyer(int id);


}
