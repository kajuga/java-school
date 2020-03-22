package afedorov.servlets.addresses;

import afedorov.dao.interfaces.AddressDao;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.Address;
import afedorov.entities.User;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/createAddress")
public class AddressCreateServlet extends HttpServlet {
    private AddressDao addressDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
        userDao = ServiceManager.getInstance(getServletContext()).getUserDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") != null) {
            Address address = new Address();
            address.setUser(userDao.findById((Long) (session.getAttribute("userId"))));
            address.setCountry(request.getParameter("country"));
            address.setCity(request.getParameter("city"));
            address.setPostcode(Integer.parseInt(request.getParameter("postcode")));
            address.setStreet(request.getParameter("street"));
            address.setHouseNumber(request.getParameter("houseNumber"));
            address.setRoom(request.getParameter("room"));
            address.setPhone(request.getParameter("phone"));
            try {
                addressDao.add(address);

                response.sendRedirect(request.getContextPath() + "/views/addresses/addressSuccessfulCreated.jsp");
            } catch (EntityExistException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println(e.getMessage());
            }
        } else {
                response.sendRedirect("/ishop/access/errorLogin.jsp");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        User user = userDao.findById(userId);
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/views/addresses/createAddress.jsp").forward(request, response);
    }
}
