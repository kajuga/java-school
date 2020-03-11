package afedorov.resourses;

import afedorov.dao.impl.inmemory.CategoryDaoImpl;
import afedorov.dao.impl.inmemory.ProductDaoImpl;
import afedorov.dao.interfaces.CategoryDao;
import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Category;
import afedorov.entities.Product;
import afedorov.exceptions.EntityExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String[]> products = request.getParameterMap();
            if (products.containsKey("add") &&
                    products.get("add").length > 0 &&
                    "true".equals(products.get("add")[0])){
                List<Category> categories = categoryDao.findAll();
                request.setAttribute("categories", categories);
                getServletContext().getRequestDispatcher("/main/products/add.jsp").forward(request, response);
            }else if (products.containsKey("delete")){
                Long id = Long.parseLong(products.get("delete")[0]);
                products.remove(id);
                response.sendRedirect(request.getContextPath() + "/products");
            } else {
                List<Product> products1 = productDao.findAll();
                request.setAttribute("products", products1.toArray());
                getServletContext().getRequestDispatcher("/main/products/view.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            response.sendRedirect(request.getContextPath() + "/products");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
        }
    }



}
