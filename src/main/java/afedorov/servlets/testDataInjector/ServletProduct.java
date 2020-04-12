package afedorov.servlets.testDataInjector;

import afedorov.dao.interfaces.CategoryDao;
import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Product;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/ServletAddProduct")
public class ServletProduct extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ServletProduct.class.getName());
    private ProductDao productDao;
    private CategoryDao categoryDao;

    @Override
    public void init() throws ServletException {
        productDao = ServiceManager.getInstance(getServletContext()).getProductDao();
        categoryDao = ServiceManager.getInstance(getServletContext()).getCategoryDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET ServletAddProduct");
        Product product = new Product();
        product.setTitle("Latex toy");
        product.setCategory(categoryDao.findById(1L));
        product.setBrand("Brothers");
        product.setColor("black");
        product.setWeight(45.55);
        product.setPrice(BigDecimal.valueOf(99.15));
        product.setDescription("wtf");
        product.setCount(99);

        Product product2 = new Product();
        product2.setTitle("Holy Bible");
        product2.setCategory(categoryDao.findById(2L));
        product2.setBrand("RPC");
        product2.setColor("ruby");
        product2.setWeight(10.10);
        product2.setPrice(BigDecimal.valueOf(66.60));
        product2.setDescription("best choise for rest!");
        product2.setCount(666);

        Product product3 = new Product();
        product3.setTitle("Snickers");
        product3.setCategory(categoryDao.findById(3L));
        product3.setBrand("Adidas");
        product3.setColor("white");
        product3.setWeight(2.55);
        product3.setPrice(BigDecimal.valueOf(12.99));
        product3.setDescription("cool shoes!");
        product3.setCount(15);

        productDao.add(product);
        productDao.add(product2);
        productDao.add(product3);

        List<Product> products = productDao.findAll();
        request.setAttribute("products", products);
        logger.info("Login check... end GET ServletAddProduct");
        getServletContext().getRequestDispatcher("/views/products/viewProduct.jsp").forward(request, response);
    }
}