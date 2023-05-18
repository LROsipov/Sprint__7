import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import pojo.Courier;

import pojo.RandomCourier;
import steps.CourierSteps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@DisplayName("Логин курьера")
public class TestLoginCourier {
    Response response;
    CourierSteps courierSteps = new CourierSteps();

    String randomLogin = RandomStringUtils.random(10);

    String randomPassword = RandomStringUtils.random(10);

    String randomName = RandomStringUtils.random(10);
   
    Courier courier = new Courier(randomLogin, randomPassword, randomName);

    @Test
    @DisplayName("Проверка возможности авторизации")
    public void checkLoginCourier(){
        response = courierSteps.loginCourier(courier);
        Allure.step("Проверяем что код ответа  [200]",
                ()->  response.then().assertThat().statusCode(200));
        Allure.step("Проверяем что тело ответа  содержит [id]",
                ()->  response.then().assertThat().body("id", notNullValue()));
    }
    @Test
    @DisplayName("Проверка невозможности авторизации без [Логина]")
    public void checkLoginCourierWithoutLogin(){
        courier.setLogin("");
        response = courierSteps.loginCourier(courier);
        Allure.step("Проверяем что код ответа  [400]",
                ()->  response.then().assertThat().statusCode(400));
        Allure.step("Проверяем что тело ответа  содержит  [message]:  \"Недостаточно данных для входа\"",
                ()->  response.then().assertThat().body("message", equalTo("Недостаточно данных для входа")));
    }
    @Test
    @DisplayName("Проверка невозможности авторизации без [Пароля]")
    public void checkLoginCourierWithoutPassword(){
        courier.setPassword("");
        response = courierSteps.loginCourier(courier);
        Allure.step("Проверяем что код ответа  [400]",
                ()->  response.then().assertThat().statusCode(400));
        Allure.step("Проверяем что тело ответа  содержит  [message]:  \"Недостаточно данных для входа\"",
                ()->  response.then().assertThat().body("message", equalTo("Недостаточно данных для входа")));
    }
    @Test
    @DisplayName("Проверка невозможности авторизации незарегестрированного пользвателя")
    public void checkLoginCourierWithoutRegistry(){
        courier = new Courier("unregistered","unregistered","unregistered");
        response = courierSteps.loginCourier(courier);
        Allure.step("Проверяем что код ответа  [400]",
                ()->  response.then().assertThat().statusCode(400));
        Allure.step("Проверяем что тело ответа  содержит  [message]:  \"Недостаточно данных для входа\"",
                ()->  response.then().assertThat().body("message", equalTo("Недостаточно данных для входа")));
    }
    @Before
    public void setUp(){
        courierSteps.createCourier(courier);
    }
    @After
    public void cleanUp(){
        courierSteps.deleteCourier(courier);
    }

}
