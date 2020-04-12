package afedorov.servlets.products;

import afedorov.dao.interfaces.ProductDao;
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

@WebServlet("/deleteProduct")
public class ProductDeleteServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductDeleteServlet.class.getName());
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = ServiceManager.getInstance(getServletContext()).getProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET deleteProduct");
        String id = request.getParameter("delete");
        try {
            productDao.remove(Long.parseLong(id));
            logger.info("Login check... end GET deleteProduct");
            response.sendRedirect(request.getContextPath() + "/viewProduct");
        } catch (EntityExistException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(e.getMessage());
            logger.info("Login check... error GET deleteProduct");
        }
    }
}