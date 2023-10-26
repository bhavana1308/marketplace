package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.launchcode.marketplace.model.Discount;

import java.util.List;

@Mapper
public interface DiscountsMapper {

    List<Discount> getAllFromDiscounts();

    int insertDiscounts(Discount Discount);

    int updateDiscounts(Discount Discount);

}
