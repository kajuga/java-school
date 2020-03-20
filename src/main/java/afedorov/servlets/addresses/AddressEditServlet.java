package afedorov.servlets.addresses;

import afedorov.dao.interfaces.AddressDao;
import afedorov.dao.interfaces.UserDao;
import afedorov.entities.Address;
import afedorov.exceptions.EntityExistException;
import afedorov.settings.ServiceManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/editAddress")
public class AddressEditServlet extends HttpServlet {
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
                addressDao.update(Long.parseLong(request.getParameter("id")), address);
                response.sendRedirect(request.getContextPath() + "/viewAddress");
            } catch (EntityExistException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println(e.getMessage());
            }
        } else {
            response.sendRedirect("/ishop/access/errorLogin.jsp");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long addressId = Long.parseLong(request.getParameter("id"));
        if (addressId != null) {
            Address address = addressDao.findById(addressId);
            request.setAttribute("id", addressId);
            request.setAttribute("name", address.getUser().getName());
            request.setAttribute("lastName", address.getUser().getLastName());
            request.setAttribute("city", address.getCity());
            request.setAttribute("postcode", address.getPostcode());
            request.setAttribute("street", address.getStreet());
            request.setAttribute("houseNumber", address.getHouseNumber());
            request.setAttribute("room", address.getRoom());
            request.setAttribute("phone", address.getPhone());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/addresses/updateAddress.jsp");
            dispatcher.forward(request, response);
        } else {
            //Переход на страницу с просьюой авторизоваться
            RequestDispatcher dispatcher = request.getRequestDispatcher("/access/errorLogin.jsp");
            dispatcher.forward(request, response);
        }



    }
}
