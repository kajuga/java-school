package afedorov.servlets.shoppingCarts;

import afedorov.dao.interfaces.CategoryDao;
import afedorov.entities.ProductInCart;
import afedorov.settings.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/shoppingCartRefresh")
public class ShoppingCartRefreshServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartRefreshServlet.class.getName());
    private CategoryDao categoryDao;

    @Override
    public void init() throws ServletException {
        categoryDao = ServiceManager.getInstance(getServletContext()).getCategoryDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start POST shoppingCartRefresh");
        HttpSession session = request.getSession();
        Map<ProductInCart, Integer> cart = (Map<ProductInCart, Integer>) session.getAttribute("shoppingCart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        ProductInCart productInCart = new ProductInCart();
        productInCart.setId(Long.parseLong(request.getParameter("id")));
        productInCart.setTitle(request.getParameter("title"));
        productInCart.setCategory(categoryDao.findById(Long.parseLong(request.getParameter("category"))));
        productInCart.setBrand(request.getParameter("brand"));
        productInCart.setColor(request.getParameter("color"));
        productInCart.setWeight(Double.parseDouble(request.getParameter("weight")));
        productInCart.setPrice(new BigDecimal(request.getParameter("price")));
        productInCart.setDescription(request.getParameter("description"));
        Integer count = Integer.parseInt(request.getParameter("count" + productInCart.getId()));
        if (count != null) {
            cart.put(productInCart, count);
        }
        session.setAttribute("shoppingCart", cart);
        logger.info("Login check... end POST shoppingCartRefresh, go forward");
        getServletContext().getRequestDispatcher("/views/shoppingCart/viewShoppingCart.jsp").forward(request, response);
    }
}