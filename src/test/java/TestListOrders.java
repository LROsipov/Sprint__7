import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.OrdersSteps;

import static org.hamcrest.Matchers.notNullValue;
@DisplayName("Список заказов")
public class TestListOrders {
    OrdersSteps ordersSteps = new OrdersSteps();
    @Test
    @DisplayName("Проверка получения списка заказов")
    public void checkReturnListOrders(){
        Response response;
        response = ordersSteps.getOrder();
        Allure.step("Проверяем что код ответа  <200>",
                ()->  response.then().assertThat().statusCode(200));
        Allure.step("Проверяем что тело ответа содержит <orders>",
                ()->  response.then().assertThat().body("orders", notNullValue()));

    }
}
