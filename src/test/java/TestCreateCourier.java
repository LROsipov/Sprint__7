import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import pojo.Courier;
import pojo.RandomCourier;
import steps.CourierSteps;

import static org.hamcrest.Matchers.equalTo;

@DisplayName("Создание курьера")
public class TestCreateCourier {
    Courier courier = new RandomCourier();
    CourierSteps courierSteps = new CourierSteps();
    @Test
    @DisplayName("Проверка возможности создания курьера")
    public void checkCreateCourier(){
        Response response;
        response = courierSteps.createCourier(courier);
        Allure.step("Проверяем что код ответа  <201>",
                ()->  response.then().assertThat().statusCode(201));
        Allure.step("Проверяем что тело ответа  содержит ok со значением true",
                ()->  response.then().assertThat().body("ok", equalTo(true)));
    }
    @Test
    @DisplayName("Проверка  не возможностни создания курьера, без передачи [Логина] в теле запроса")
    public void checkCreateCourierWithoutLogin(){
        Response response;
        Allure.step("Генерируем данные для создания курьера без [Логина]",
                ()->   courier = new Courier("", RandomCourier.RANDOM_PASSWORD,RandomCourier.RANDOM_FIRST_NAME));
        response = courierSteps.createCourier(courier);
        Allure.step("Проверяем что код ответа  <400>",
                ()->  response.then().assertThat().statusCode(400));
        Allure.step("Проверяем что тело ответа  содержит сообщение \"Недостаточно данных для создания учетной записи\"",
                ()->  response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")));
    }
    @Test
    @DisplayName("Проверка  не возможностни создания курьера, без передачи [Пароля] в теле запроса")
    public void checkCreateCourierWithoutPassword(){
        Response response;
        Allure.step("Генерируем данные для создания курьера без [пароля]",
                ()->   courier = new Courier(RandomCourier.RANDOM_LOGIN, "",RandomCourier.RANDOM_FIRST_NAME));
        response = courierSteps.createCourier(courier);
        Allure.step("Проверяем что код ответа  <400>",
                ()->  response.then().assertThat().statusCode(400));
        Allure.step("Проверяем что тело ответа  содержит сообщение \"Недостаточно данных для создания учетной записи\"",
                ()->  response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")));
    }
    @Test
    @DisplayName("Проверка  не возможностни создания курьера, без передачи [Имени] в теле запроса")
    public void checkCreateCourierWithoutFirstName(){
        Response response;
        Allure.step("Генерируем данные для создания курьера без [имени]",
                ()->   courier = new Courier(RandomCourier.RANDOM_LOGIN, RandomCourier.RANDOM_PASSWORD,""));
        response = courierSteps.createCourier(courier);
        Allure.step("Проверяем что код ответа  <400>",
                ()->  response.then().assertThat().statusCode(400));
        Allure.step("Проверяем что тело ответа  содержит сообщение \"Недостаточно данных для создания учетной записи\"",
                ()->  response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")));
    }
    @Test
    @DisplayName("Проверка  не возможностни создания курьера, с занятым [Логином]")
    public void checkCreateDuplicateCourier(){
        courierSteps.createCourier(courier);
        Allure.step("Проверяем что код ответа  <409>",
                ()->  courierSteps.createCourier(courier).then().assertThat().statusCode(409));
        Allure.step("Проверяем что тело ответа  содержит сообщение \"Этот логин уже используется\"",
                ()->  courierSteps.createCourier(courier).then().assertThat().body("message", equalTo("Этот логин уже используется")));
    }
    @After
    public void cleanUp(){
        courierSteps.deleteCourier(courier);
    }

}
