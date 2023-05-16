package uz.najottalim.demospringjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.najottalim.demospringjdbc.dao.Product;
import uz.najottalim.demospringjdbc.dao.ProductDAO;

import java.util.Date;
import java.util.List;

@RestController

@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/all-count")
    public Integer getAllProductsCount() {
        return productDAO.findAllCount();
    }

    @GetMapping("/name/{id}")
    public String getProductNameById(@PathVariable Integer id) {
        return productDAO.findProductNameById(id);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productDAO.findProductById(id);
    }

    @GetMapping("/price/{price}")
    public List<Product> getProductById(@PathVariable Double price) {
        return productDAO.findProductsByPrice(price);
    }
    @GetMapping("products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName){
        return productDAO.findProductsByCategory(categoryName);
    }
    @GetMapping("products/all")
    public List<Product> getProductOrderBy(){
        return productDAO.findProductsOrderBy();
    }

    @GetMapping("products/order-date/{orderDate}")
    public List<Product> getProductsOrderDate(@PathVariable Date orderDate){
        return productDAO.findProductsOrderDate(orderDate);
    }

    @GetMapping("orders/all-sum")
    public Double getOrderDateAllSum(){
        return productDAO.getOrderDateAllSum();
    }

    @GetMapping("order/avj/{date}")
    public Double getOrderAvjaPrice(@PathVariable Date date){
        return productDAO.getOrderAvjPrice(date);
    }

}
