package steps;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import pojo.Courier;

import static io.restassured.RestAssured.given;

public class CourierSteps  {
    public CourierSteps() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Step("Создаем курьера")
    @Description("POST на ручку api/v1/courier")
    public Response createCourier(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }
    @Step("Логинемся")
    @Description("POST на ручку /pi/v1/courier/login")
    public Response loginCourier(Courier courier) {
        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");

    }
    @Step("Удаляем курьера из базы")
    @Description("DELETE на ручку api/v1/courier/:id")
    public void deleteCourier(Courier courier) {
        try {
            int id = loginCourier(courier).then().extract().path("id");
            given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(courier)
                    .when()
                    .delete("/api/v1/courier/"+id);

        }
        catch (NullPointerException e){
            System.out.println("Nothing to delete after test");
        }

    }
}
