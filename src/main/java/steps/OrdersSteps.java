package steps;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import pojo.Order;

import static io.restassured.RestAssured.given;

public class OrdersSteps {
    public OrdersSteps() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Step("Создаем заказ")
    @Description("POST на ручку api/v1/orders")
    public Response createOrder(Order order){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получаем список заказов")
    @Description("GET  на ручку api/v1/orders")
    public Response getOrder(){
        return given()
                .get("api/v1/orders");
    }
}