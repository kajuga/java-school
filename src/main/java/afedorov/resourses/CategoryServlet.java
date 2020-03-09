package afedorov.resourses;

import afedorov.dao.CategoryDao;
import afedorov.dao.impl.inmemory.CategoryDaoImpl;
import afedorov.entities.Category;
import afedorov.exceptions.EntityExistException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CategoryServlet extends HttpServlet {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String[]> parameters = req.getParameterMap();

            if (parameters.containsKey("delete")){
                Long id = Long.parseLong(parameters.get("delete")[0]);
                categoryDao.remove(id);
                resp.sendRedirect(req.getContextPath() + "/categories");
            } else {
                List<Category> categories = categoryDao.findAll();
                req.setAttribute("categories", categories.toArray());
                getServletContext().getRequestDispatcher("/main/categories/view.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category();
        category.setTitle(req.getParameter("title"));
        try {
            categoryDao.add(category);
            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (EntityExistException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }

    }
}