package afedorov.servlets.products;

import afedorov.dao.interfaces.CategoryDao;
import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Category;
import afedorov.entities.Product;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/editProduct")
public class ProductEditServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductEditServlet.class.getName());
    private ProductDao productDao;
    private CategoryDao categoryDao;

    @Override
    public void init() throws ServletException {
        productDao = ServiceManager.getInstance(getServletContext()).getProductDao();
        categoryDao = ServiceManager.getInstance(getServletContext()).getCategoryDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start POST editProduct");
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
            productDao.update(Long.parseLong(request.getParameter("id")), product);
            logger.info("Login check... end POST editProduct");
            response.sendRedirect(request.getContextPath() + "/viewProduct");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
            logger.error("Error POST editProduct", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET editProduct");
        String id = request.getParameter("edit");
        Product product = productDao.findById(Long.parseLong(id));
        request.setAttribute("id", id);
        request.setAttribute("title", product.getTitle());
        request.setAttribute("category", product.getCategory());
        request.setAttribute("brand", product.getBrand());
        request.setAttribute("color", product.getColor());
        request.setAttribute("weight", product.getWeight());
        request.setAttribute("price", product.getPrice());
        request.setAttribute("description", product.getDescription());
        request.setAttribute("count", product.getCount());
        List<Category> categories = categoryDao.findAll();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/products/updateProduct.jsp");
        logger.info("Login check... end GET editProduct");
        dispatcher.forward(request, response);
    }
}