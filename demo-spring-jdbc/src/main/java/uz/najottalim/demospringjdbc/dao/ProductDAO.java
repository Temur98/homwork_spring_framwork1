package uz.najottalim.demospringjdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import uz.najottalim.demospringjdbc.dao.Product.ProductRowMapper;

import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class ProductDAO {
    private Logger logger = LoggerFactory.getLogger(ProductDAO.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer findAllCount() {
        String sql = "SELECT COUNT(*) FROM PRODUCT";
        Integer product = jdbcTemplate.queryForObject(sql, Integer.class);
        logger.info("Product count {}", product);
        return product;
    }

    public String  findProductNameById(Integer id) {
        String sql = "SELECT name from product where id = ?";
        String product = jdbcTemplate.queryForObject(sql, String.class, id);
        logger.info("Product count {}", product);
        return product;
    }

    public Product  findProductById(Integer id) {
        String sql = "SELECT * from product where id = ?";
        Product product = jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
        logger.info("Product count {}", product);
        return product;
    }

    public List<Product> findProductsByPrice(Double price) {
        String sql = "SELECT * from product where price > ?";
        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper(), price);
        logger.info("Product count {}", products);
        return products;
    }

    public List<Product> findProductsByCategory(String categoryName) {
        String sql = "SELECT * FROM Product WHERE categoryName = ?";
        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper(), categoryName);
        return products;
    }

    public List<Product> findProductsOrderBy() {
        String sql = "SELECT price FROM Product ORDER BY price ASC";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public List<Product> findProductsOrderDate(Date orderDate) {
        String sql = "SELECT p.* FROM Product p join product_order o on p.id = o.product_id WHERE TO_CHAR(orderDate, 'YYYY-MM-DD' = ";
        return jdbcTemplate.query(sql, new ProductRowMapper(),orderDate);
    }

    public Double getOrderDateAllSum() {
        String sql = "SELECT SUM(price) FROM Product";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public Double getOrderAvjPrice(Date date) {
        String sql = "SELECT avj(sum(price),2) FROM Product p join product_order o on p.id = o.product_id WHERE TO_CHAR(orderDate, 'YYYY-MM-DD' = ";
        return jdbcTemplate.queryForObject(sql, Double.class, date);

    }


}
