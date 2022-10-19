package tests.ExampleTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ExampleDemowebshopTests {

/*
    curl 'https://demowebshop.tricentis.com/addproducttocart/details/72/1' \
            -H 'Accept: *\/*' \
            -H 'Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7' \
            -H 'Connection: keep-alive' \
            -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8' \
            -H 'Cookie: Nop.customer=0d25b410-b928-421a-a20c-3beeac1eca0f; ARRAffinity=754c43f3fb666b4689fd8452291c08520e941e1737ad17b31babd87059cc27ae; ARRAffinitySameSite=754c43f3fb666b4689fd8452291c08520e941e1737ad17b31babd87059cc27ae; __utma=78382081.517345802.1665580485.1665580485.1665580485.1; __utmc=78382081; __utmz=78382081.1665580485.1.1.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=72; __utmt=1; __atuvc=4%7C41; __atuvs=6346bdd7fc7aa14f003; __utmb=78382081.5.10.1665580485' \
            -H 'Origin: https://demowebshop.tricentis.com' \
            -H 'Referer: https://demowebshop.tricentis.com/build-your-cheap-own-computer' \
            -H 'Sec-Fetch-Dest: empty' \
            -H 'Sec-Fetch-Mode: cors' \
            -H 'Sec-Fetch-Site: same-origin' \
            -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36' \
            -H 'X-Requested-With: XMLHttpRequest' \
            -H 'sec-ch-ua: "Chromium";v="106", "Google Chrome";v="106", "Not;A=Brand";v="99"' \
            -H 'sec-ch-ua-mobile: ?0' \
            -H 'sec-ch-ua-platform: "Windows"' \
            --data-raw 'product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1' \
            --compressed
*/

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demowebshop.tricentis.com";
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    @Test
    void addToCartTest() {
        given()
                .log().uri()
                .log().body()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=0d25b410-b928-421a-a20c-3beeac1eca0f;")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    void addNewUserToCartTest() {
        String quantity = "3";

        given()
                .log().uri()
                .log().body()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=" +quantity)
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("updatetopcartsectionhtml", is("("+quantity+")"))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    void addToCartWithUITest() {
        String authCookieName = "Nop.customer",
               authCookieValue = "0d25b410-b928-421a-a20c-3beeac1eca0f",
               body = "product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1";

        given()
                .log().uri()
                .log().body()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(authCookieName, authCookieValue)
                .body(body)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

        open("/Themes/DefaultClean/Content/images/logo.png");
        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        open("");
        open("");
    }

    @Test
    void addToCartWithUIWithAuthTest() {
        String authCookieName = "NOPCOMMERCE.AUTH";

        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("Email=vbdv%40feferf.ru&Password=itLf7%40U%40Bf6khGH")
                .log().all()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieName);


        String body = "product_attribute_72_5_18=53" +
                "&product_attribute_72_6_19=54" +
                "&product_attribute_72_3_20=57" +
                "&addtocart_72.EnteredQuantity=1";

        String cartSize = given()
                .log().uri()
                .log().body()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(authCookieName, authCookieValue)
                .body(body)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .extract().path("updatetopcartsectionhtml");

        open("/Themes/DefaultClean/Content/images/logo.png");
        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        open("");
        $(".cart-qty").shouldHave(Condition.text(cartSize));
        open("");
    }

    @Test
    @DisplayName("Редактирование профиля авторизованного пользователя")
    void editWithAuthTest() {
        String authCookieName = "NOPCOMMERCE.AUTH";

        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("Email=yulia1%40ya.ru&Password=123456")
                .log().all()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieName);

        open("/Themes/DefaultClean/Content/images/logo.png");
        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        open("/customer/info");
        $("#FirstName").setValue("Olga1");
        $("#LastName").setValue("Orlova1");
        $(byValue("M")).click();
        $(".save-customer-info-button").click();
        $("#FirstName").shouldHave(value("Olga1"));
        $("#LastName").shouldHave(value("Orlova1"));
      }
}
