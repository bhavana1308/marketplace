package org.launchcode.marketplace.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.launchcode.marketplace.model.Product;
import org.launchcode.marketplace.model.PurchaseOrder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;


public class Runner {

    public static void main(String[] args) throws IOException {


        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession session = sqlSessionFactory.openSession()) {

//            System.out.println("-------------BUYER----------");
//            BuyerMapper buyerMapper = session.getMapper(BuyerMapper.class);
//            Buyer buyer1 = new Buyer(4, "aaa.test@gmail.com", "aaa", "Test", "Welcome123$");
//            int insertedBuyer = buyerMapper.insertBuyer(buyer1);
//            if (insertedBuyer == 1) {
//                System.out.println("record inserted successfully");
//            }
//
//            Buyer buyer2 = new Buyer(3, "venny@gmail.com", "Venny", "A", "10101");
//            int updatedBuyer = buyerMapper.updateBuyer(buyer2);
//            if (updatedBuyer == 1) {
//                System.out.println("record updated successfully");
//            }
//
//            List<Buyer> list = buyerMapper.getAllFromBuyer();
//            list.forEach(t -> System.out.println(t));
//
//            System.out.println("-------------CART LIST----------");
//            CartListMapper cartListMapper = session.getMapper(CartListMapper.class);
//            int deletedCartList = cartListMapper.deleteByCartListId(2);
//            if (deletedCartList == 1) {
//                System.out.println("record deleted successfully");
//            }
//
//            CartList cartList = cartListMapper.getByCartListId(3);
//            System.out.println(cartList);
//
//            List<CartList> list1 = cartListMapper.getAllFromCartList();
//            list1.forEach(t -> System.out.println(t));
//
//            System.out.println("-------------CATEGORY----------");
//            CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
//            Category category1 = new Category(4, "Electronics", "Laptop");
//            int insertedCategory = categoryMapper.insertCategory(category1);
//            if (insertedCategory == 1) {
//                System.out.println("record inserted successfully");
//            }
//
//            Category category2 = new Category(3, "Crockery", "Household Items");
//            int updatedCategory = categoryMapper.updateCategory(category2);
//            if (updatedCategory == 1) {
//                System.out.println("record updated successfully");
//            }
//
//            Category category = categoryMapper.getByCategoryId(3);
//            System.out.println(category);
//
//            List<Category> list2 = categoryMapper.getAllFromCategory();
//            list2.forEach(t -> System.out.println(t));
//
//            System.out.println("-------------DISCOUNTS----------");
//            DiscountsMapper discountsMapper = session.getMapper(DiscountsMapper.class);
//            Discount discount1 = new Discount(4, "Christmas Day Sale", "Additional Discount for Electronics, Hurry up and grab the deal", 20);
//            int insertedDiscount = discountsMapper.insertDiscounts(discount1);
//            if (insertedDiscount == 1) {
//                System.out.println("record inserted successfully");
//            }
//            Discount discount2 = new Discount(3, "Independence Day Sale", "Additional Discount for Electronics, Hurry up and grab the deal", 25);
//            int updatedDiscount = discountsMapper.updateDiscounts(discount2);
//            if (updatedDiscount == 1) {
//                System.out.println("record updated successfully");
//            }
//
//            List<Discount> list3 = discountsMapper.getAllFromDiscounts();
//            list3.forEach(t -> System.out.println(t));

            System.out.println("-------------PRODUCTS----------");
            ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);

            List<Product> list4 = productsMapper.getAllFromProducts();
            list4.forEach(t -> System.out.println(t));

            PurchaseOrderMapper purchaseOrderMapper = session.getMapper(PurchaseOrderMapper.class);
            List<PurchaseOrder> list5 = purchaseOrderMapper.getAllPurchaseOrders();
            list5.forEach(t -> System.out.println(t));

//            Product product = productsMapper.getByProductName("Shirts");
//            System.out.println(product);

            session.commit();

        }
    }
}
