package afedorov.servlets.addresses;

import afedorov.dao.interfaces.AddressDao;
import afedorov.entities.Address;
import afedorov.settings.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewAddress")
public class AddressViewServlet extends HttpServlet {
    private AddressDao addressDao;

    @Override
    public void init() throws ServletException {
        addressDao = ServiceManager.getInstance(getServletContext()).getAddressDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") != null) {

            List<Address> addresses = addressDao.findAll();
            request.setAttribute("addresses", addresses);
            getServletContext().getRequestDispatcher("/views/addresses/viewAddress.jsp").forward(request, response);
        } else {
            response.sendRedirect("/ishop/access/errorLogin.jsp");
        }
    }
}
