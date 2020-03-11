package afedorov.dao.impl.inmemory;

import afedorov.entities.Category;
import afedorov.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBase {

    final static List<Category> CATEGORIES = new ArrayList<>();
    final static List<Order> ORDERS = new ArrayList<>();
}
