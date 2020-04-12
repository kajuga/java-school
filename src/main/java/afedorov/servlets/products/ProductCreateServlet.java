package afedorov.servlets.products;

import afedorov.dao.interfaces.CategoryDao;
import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Category;
import afedorov.entities.Product;
import afedorov.exceptions.EntityExistException;
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

@WebServlet("/createProduct")
public class ProductCreateServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductCreateServlet.class.getName());
    private ProductDao productDao;
    private CategoryDao categoryDao;

    @Override
    public void init() throws ServletException {
        productDao = ServiceManager.getInstance(getServletContext()).getProductDao();
        categoryDao = ServiceManager.getInstance(getServletContext()).getCategoryDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start POST createProduct");
        Product product = new Product();
        product.setTitle(request.getParameter("title"));
        product.setCategory(categoryDao.findById(Long.parseLong(request.getParameter("category"))));
        product.setBrand(request.getParameter("brand"));
        product.setColor(request.getParameter("color"));
        product.setWeight(Double.parseDouble(request.getParameter("weight")));
        product.setPrice(new BigDecimal(request.getParameter("price")));
        product.setDescription(request.getParameter("description"));
        product.setCount(Integer.parseInt(request.getParameter("count")));
        try {
            productDao.add(product);
            logger.info("Login check... emd POST createProduct");
            response.sendRedirect(request.getContextPath() + "/viewProduct");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
            logger.info("Login check... error POST createProduct");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Login check... start GET createProduct");
        List<Category> categories = categoryDao.findAll();
        req.setAttribute("categories", categories);
        logger.info("Login check... end GET createProduct");
        getServletContext().getRequestDispatcher("/views/products/createProduct.jsp").forward(req, resp);
    }
}