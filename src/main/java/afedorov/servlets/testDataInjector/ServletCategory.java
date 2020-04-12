package afedorov.servlets.testDataInjector;

import afedorov.dao.impl.inmemory.CategoryDaoImpl;
import afedorov.dao.interfaces.CategoryDao;
import afedorov.entities.Category;
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
import java.util.List;

@WebServlet("/ServletAddCategory")
public class ServletCategory extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ServletCategory.class.getName());
    private CategoryDao categoryDao;

    @Override
    public void init() throws ServletException {
        categoryDao = ServiceManager.getInstance(getServletContext()).getCategoryDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Login check... start GET ServletAddCategory");
        Category category = new Category();
        category.setTitle("SPORT");
        Category category2 = new Category();
        category2.setTitle("BOOK");
        Category category3 = new Category();
        category3.setTitle("PORNO");
        categoryDao.add(category);
        categoryDao.add(category2);
        categoryDao.add(category3);
        List<Category> categories = categoryDao.findAll();
        request.setAttribute("categories", categories.toArray());
        logger.info("Login check... end GET ServletAddCategory");
        getServletContext().getRequestDispatcher("/views/categories/viewCategory.jsp").forward(request, response);
    }
}