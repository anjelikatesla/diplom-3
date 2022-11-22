package com.example.api.client;

import com.example.User;
import com.example.constants.Constants;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthRegisterClient {

    public static final String REGISTER_PATH = Constants.BASE_PATH + "api/auth/register";

    public static final String DELETE_USER_PATH = Constants.BASE_PATH + "api/auth/user";

    @Step("Создание пользователя")
    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(REGISTER_PATH);
    }

    public static String createUserBeforeTests(User user) {
        createUser(user);
        return AuthLoginClient.getUserToken(user);
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String token) {
        given()
                .auth().oauth2(token)
                .when()
                .delete(DELETE_USER_PATH);
    }
}