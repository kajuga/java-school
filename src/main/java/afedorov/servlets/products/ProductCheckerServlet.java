package afedorov.servlets.products;

import afedorov.exceptions.EntityExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/productCountCheck")
public class ProductCheckerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try (PreparedStatement prepStatment = getConnection().prepareStatement("UPDATE product SET count = count - (?)")) {
                long id = Long.parseLong(request.getParameter("id"));

                int pCount = Integer.parseInt(request.getParameter("count"+id));

                prepStatment.setInt(1, pCount);
                prepStatment.executeUpdate();
                try {
                    response.sendRedirect(request.getContextPath() + "/viewProduct");
                } catch (EntityExistException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println(e.getMessage());
                }

            } catch (SQLException exc) {
                exc.printStackTrace();
            }

        }


        protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

        }


        private Connection getConnection () {
            try {
                Class.forName("org.postgresql.Driver");
                return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ishop", "kajuga", "sashok");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

