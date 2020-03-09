package afedorov.resourses;

import afedorov.dao.CategoryDao;
import afedorov.dao.impl.inmemory.CategoryDaoImpl;
import afedorov.entities.Category;
import afedorov.exceptions.EntityExistException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();
    private CategoryDao categoryDao = new CategoryDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = getId(req.getPathInfo());
            if (id != null){
                Category category = categoryDao.findById(id);
                String json = mapper.writeValueAsString(category);
                resp.getWriter().print(json);
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
        Category category = mapper.readValue(req.getReader(), Category.class);
        try {
            categoryDao.add(category);
        } catch (EntityExistException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = getId(req.getPathInfo());
            categoryDao.remove(id);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(e.getMessage());
        }

    }

    private Long getId(String string) {
        if (string != null && !string.isEmpty()) {
            return Long.parseLong(string.substring(1));

        }
        return null;
    }
}
