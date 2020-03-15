package afedorov.servlets.users;

import afedorov.dao.impl.inmemory.UserDaoImpl;
import afedorov.dao.interfaces.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editUser")
public class UserEditServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    //todo

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Product product = new Product();
//        product.setTitle(request.getParameter("title"));
//        product.setCategory(categoryDao.findById(Long.parseLong(request.getParameter("category"))));
//        product.setBrand(request.getParameter("brand"));
//        product.setColor(request.getParameter("color"));
//        product.setWeight(Double.parseDouble(request.getParameter("weight")));
//        product.setPrice(new BigDecimal(request.getParameter("price")));
//        product.setDescription(request.getParameter("description"));
//        product.setCount(Integer.parseInt(request.getParameter("count")));
//
////        List<Category> categories = categoryDao.findAll();
////        request.setAttribute("categories", categories);
//        try {
//            productDao.update(Long.parseLong(request.getParameter("id")), product);
//            response.sendRedirect(request.getContextPath() + "/viewProduct");
//        } catch (EntityExistException e) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().println(e.getMessage());
//        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
