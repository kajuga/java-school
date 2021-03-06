package afedorov.servlets.products;

import afedorov.dao.impl.inmemory.ProductDaoImpl;
import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Product;
import afedorov.settings.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewProduct")
public class ProductViewServlet extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = ServiceManager.getInstance(getServletContext()).getProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        if (session.getAttribute("userId") == null) {
            List<Product> products = productDao.findAll();
            request.setAttribute("products", products);
        }

        List<Product> products = productDao.findAll();
        request.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/views/products/viewProduct.jsp").forward(request, response);
    }
}