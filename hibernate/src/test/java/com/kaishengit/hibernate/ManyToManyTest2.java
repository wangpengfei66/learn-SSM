package com.kaishengit.hibernate;

import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.CustomerProduct;
import com.kaishengit.pojo.Product;
import org.junit.Test;

public class ManyToManyTest2 extends BaseTest {

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setUserName("魏正先");

        Product product = new Product();
        product.setProductName("手枪");
        product.setPrice(90);

        CustomerProduct customerProduct = new CustomerProduct();
        customerProduct.setPrice(product.getPrice());
        customerProduct.setCustomer(customer);
        customerProduct.setProduct(product);

        session.save(product);
        session.save(customer);
        session.save(customerProduct);
    }

}
