package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.launchcode.marketplace.model.Buyer;

import java.util.List;

@Mapper
public interface BuyerMapper {

    List<Buyer> getAllFromBuyer();

    int insertBuyer(Buyer buyer);

    int updateBuyer(Buyer buyer);

    Buyer getBuyerByEmailAndPassword(String email, String password);


}
