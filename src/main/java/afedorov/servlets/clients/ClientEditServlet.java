package afedorov.servlets.clients;

import afedorov.dao.impl.inmemory.ClientDaoImpl;
import afedorov.dao.interfaces.ClientDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editClient")
public class ClientEditServlet extends HttpServlet {
    private ClientDao clientDao = new ClientDaoImpl();
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
