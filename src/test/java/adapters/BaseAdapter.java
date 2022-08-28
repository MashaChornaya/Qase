package adapters;

import static io.restassured.RestAssured.given;

public class BaseAdapter {
    protected final static String BASE_URL="https://app.qase.io/M1/";
    private final String ACCESS_TOKEN="f271f06fb83ece54e57a4b06ec05817df588d42b";

    public String get(String endpoint, int statusCode) {
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .when()
                .log().all()
                .get(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).extract().body().asString();
    }

    public String post(String endpoint, int statusCode, String requestBody) {
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .log().all()
                .post(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).
                extract().body().asString();
    }
}
