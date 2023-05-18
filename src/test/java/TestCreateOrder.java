import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pojo.Order;
import pojo.RandomOrder;
import steps.OrdersSteps;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
@DisplayName("Создание заказа")
public class TestCreateOrder  {
    private final Order order;

    OrdersSteps ordersSteps = new OrdersSteps();

    public TestCreateOrder(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new RandomOrder(new String[]{"BLACK"})},
                {new RandomOrder(new String[]{"GREY"})},
                {new RandomOrder(new String[]{"BLACK", "GREY"})},
                {new RandomOrder(null)},
        };
    }

    @Test
    @DisplayName("Проверка возможности заказа c различными цветами самоката")
    public void checkCreateOrder(){
                Response response;
                response = ordersSteps.createOrder(order);
                Allure.step("Проверяем что код ответа  <201>",
                ()->  response.then().assertThat().statusCode(201));
                Allure.step("Проверяем что тело ответа содержит <track>",
                ()->  response.then().assertThat().body("track", notNullValue()));

    }
}
