import com.sales.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;

public class OrderMetricsTest {

    private OrderMetrics orderMetrics;
    private List<Order> orders;

    @BeforeEach
    public void setUp() {
        orderMetrics = new OrderMetrics();

        Customer customer1 = new Customer();
        customer1.setCustomerId("C1");
        customer1.setName("Daniil");
        customer1.setCity("Minsk");

        Customer customer2 = new Customer();
        customer2.setCustomerId("C2");
        customer2.setName("Konstantin");
        customer2.setCity("Brest");

        OrderItem item1 = new OrderItem();
        item1.setProductName("Laptop");
        item1.setQuantity(1);
        item1.setPrice(1000.0);
        item1.setCategory(Category.ELECTRONICS);

        OrderItem item2 = new OrderItem();
        item2.setProductName("Book");
        item2.setQuantity(3);
        item2.setPrice(20.0);
        item2.setCategory(Category.BOOKS);

        OrderItem item3 = new OrderItem();
        item3.setProductName("T-shirt");
        item3.setQuantity(2);
        item3.setPrice(30.0);
        item3.setCategory(Category.CLOTHING);

        Order order1 = new Order();
        order1.setOrderId("O1");
        order1.setCustomer(customer1);
        order1.setItems(List.of(item1, item2));
        order1.setStatus(OrderStatus.DELIVERED);
        order1.setOrderDate(LocalDateTime.now());

        Order order2 = new Order();
        order2.setOrderId("O2");
        order2.setCustomer(customer2);
        order2.setItems(List.of(item2, item3));
        order2.setStatus(OrderStatus.SHIPPED);
        order2.setOrderDate(LocalDateTime.now());

        Order order3 = new Order();
        order3.setOrderId("O3");
        order3.setCustomer(customer1);
        order3.setItems(List.of(item3));
        order3.setStatus(OrderStatus.DELIVERED);
        order3.setOrderDate(LocalDateTime.now());

        orders = List.of(order1, order2, order3);
    }

    @Test
    public void getUniqueCities() {
        List<String> cities = orderMetrics.getUniqueCities(orders);
        assertEquals(2, cities.size());
        assertTrue(cities.containsAll(List.of("Minsk", "Brest")));
    }

    @Test
    public void getTotalIncome() {
        Double income = orderMetrics.getTotalIncome(orders);
        assertEquals(1120.0, income);
    }

    @Test
    public void getMostPopularProduct() {
        String popular = orderMetrics.getMostPopularProduct(orders);
        assertTrue(popular.equals("Book") || popular.equals("T-shirt"));
    }

    @Test
    public void getMostPopularProduct_ifEmpty() {
        List<Order> empty = List.of();
        String result = orderMetrics.getMostPopularProduct(empty);
        assertEquals("There aren't any products", result);
    }

    @Test
    public void getAverageCheck() {
        OptionalDouble avg = orderMetrics.getAverageCheck(orders);
        assertTrue(avg.isPresent());
        assertEquals(560.0, avg.getAsDouble());
    }

    @Test
    public void getCustomersWithMoreThan5Orders() {
        Customer customer = orders.get(0).getCustomer();
        List<Order> extended = orders;
        for (int i = 0; i < 6; i++) {
            Order extra = new Order();
            extra.setOrderId("Extra" + i);
            extra.setCustomer(customer);
            extra.setItems(orders.get(0).getItems());
            extra.setStatus(OrderStatus.NEW);
            extra.setOrderDate(LocalDateTime.now());
            extended = new java.util.ArrayList<>(extended);
            extended.add(extra);
        }

        List<String> result = orderMetrics.getCustomersWithMoreThan5Orders(extended);
        assertEquals(List.of("Daniil"), result);
    }

}
