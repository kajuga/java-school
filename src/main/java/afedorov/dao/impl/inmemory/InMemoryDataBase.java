package afedorov.dao.impl.inmemory;

import afedorov.entities.Category;
import afedorov.entities.User;
import afedorov.entities.Order;
import afedorov.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase {

    final static List<Category> CATEGORIES = new ArrayList<>();
    final static List<Order> ORDERS = new ArrayList<>();
    final static List<Product> PRODUCTS = new ArrayList<>();
    final static List<User> USERS = new ArrayList<>();

     enum Role {
        CUSTOMER,
        EMPLOYEE
    }
}

