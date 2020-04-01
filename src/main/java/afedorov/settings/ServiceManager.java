package afedorov.settings;

import afedorov.dao.impl.inmemory.*;
import afedorov.dao.impl.jdbc.CategoryDaoJdbcImpl;
import afedorov.dao.impl.jdbc.ProductDaoJdbcImpl;
import afedorov.dao.interfaces.*;

import javax.servlet.ServletContext;


public class ServiceManager {

	private final AddressDao addressDao;
	private final CategoryDao categoryDao;
	private final OrderDao orderDao;
	private final UserDao userDao;
	private final ProductDao productDao;

	private ServiceManager(ServletContext context) {
		this.addressDao = new AddressDaoImpl();
		this.categoryDao = new CategoryDaoJdbcImpl();
		this.orderDao = new OrderDaoImpl();
		this.userDao = new UserDaoImpl();
		this.productDao = new ProductDaoJdbcImpl();
	}

	public static synchronized ServiceManager getInstance(ServletContext context) {
		ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
		if (instance == null) {
			instance = new ServiceManager(context);
			context.setAttribute("SERVICE_MANAGER", instance);
		}
		return instance;
	}

	public AddressDao getAddressDao() {
		return addressDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}
}
