package afedorov.dao.impl.inmemory;

import afedorov.entities.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase {

    final static List<Category> CATEGORIES = new ArrayList<>();
    final static List<Order> ORDERS = new ArrayList<>();
    final static List<Product> PRODUCTS = new ArrayList<>();
    final static List<User> USERS = new ArrayList<>();
    final static List<Address> ADDRESSES = new ArrayList<>();

     enum Role {
        CUSTOMER,
        EMPLOYEE
    }
}

