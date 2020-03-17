package afedorov.servlets.shoppingCarts;

import afedorov.dao.impl.inmemory.CategoryDaoImpl;
import afedorov.dao.interfaces.CategoryDao;
import afedorov.servlets.model.ProductModel;

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
    private CategoryDao categoryDao = new CategoryDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<ProductModel, Integer> cart = (Map<ProductModel, Integer>) session.getAttribute("shoppingCart");
        if (cart == null){
            cart = new HashMap<>();
        }

        ProductModel productModel = new ProductModel();
        productModel.setId(Long.parseLong(request.getParameter("id")));
        productModel.setTitle(request.getParameter("title"));
        productModel.setCategory(categoryDao.findById(Long.parseLong(request.getParameter("category"))));
        productModel.setBrand(request.getParameter("brand"));
        productModel.setColor(request.getParameter("color"));
        productModel.setWeight(Double.parseDouble(request.getParameter("weight")));
        productModel.setPrice(new BigDecimal(request.getParameter("price")));
        productModel.setDescription(request.getParameter("description"));

        Integer count = Integer.parseInt(request.getParameter("count" + productModel.getId()));

        if (count != null){
            cart.put(productModel, count);
        }

         session.setAttribute("shoppingCart", cart);
         getServletContext().getRequestDispatcher("/views/shoppingCart/viewShoppingCart.jsp").forward(request, response);
    }
}
