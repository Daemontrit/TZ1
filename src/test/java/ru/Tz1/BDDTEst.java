package ru.Tz1;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

public class BDDTEst {

    @DataProvider(name = "random-data")
    public static Object[][] createData() {
        return new Object[][]{{new DataGenerator().getSaltString() + "@mail.com", new DataGenerator().getSaltString()}};
    }


    @Test(dataProvider = "random-data")
    public void postRequestExampleTest(String email, String name) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("name",name);
        requestBody.put("tasks",new JSONArray().put(12));
        requestBody.put("companies",new JSONArray().put(36).put(37));
        requestBody.put("hobby","Стрельба из лука, Настолки");
        requestBody.put("adres","адрес 1");
        requestBody.put("name1","Тестовый, ясен пень");
        requestBody.put("surname1","Иванов");
        requestBody.put("fathername1","Петров");
        requestBody.put("cat","Маруся");
        requestBody.put("dog","Ушастый");
        requestBody.put("parrot","Васька");
        requestBody.put("cavy","Кто ты?");
        requestBody.put("hamster","Хомяк");
        requestBody.put("squirrel","Белая горячка к нам пришла");
        requestBody.put("phone","333 33 33");
        requestBody.put("inn","123456789012");
        requestBody.put("gender","m");
        requestBody.put("birthday","01.01.1995");
        requestBody.put("date_start","11.11.2000");

        RequestSpecification request = RestAssured.given();
        request.contentType("application/json; charset=utf-8");
        request.body(requestBody.toString());

        Response response = request.post("http://users.bugred.ru/tasks/rest/createuser");
        //response.getBody().print();
        response.jsonPath().prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.getBody().jsonPath().get("email"), requestBody.get("email"));
        Assert.assertEquals(response.getBody().jsonPath().get("name"), requestBody.get("name"));
        Assert.assertEquals(response.getBody().jsonPath().get("hobby"), requestBody.get("hobby"));
        Assert.assertEquals(response.getBody().jsonPath().get("phone"), requestBody.get("phone"));
        step("request",()-> (request.post("http://users.bugred.ru/tasks/rest/createuser")));
        step("response",()-> (response.jsonPath()));

       // step("do something",()-> doSomething());

        }



    }


