package com.hexagonal.microservice_cart.constants;

public class ValidationConstants {

    public static final int ZERO = 0;
    public static final int SEVEN = 7;
    public static final int NAME_LENGTH = 50;
    public static final int STATUS_LENGTH = 60;

    public static final String SPRING = "spring";

    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";
    public static final String cart_DATE = "cartDate";
    public static final String STATUS = "status";
    public static final String EMAZON_HEXAGONAL = "emazon-hexagonal";

    public static final String CART = "cart";
    public static final String MESSAGE = "message";
    public static final String ERROR = "error";

    public static final String CARTS_SUCCESSFULLY = "cart update successfully";
    public static final String ARTICLE_NOT_FOUND = "Article not found";
    public static final String STOCK_INSUFFICIENT = "Stock insufficient";
    public static final String AN_ERROR_OCCURRED = "An error occurred";

    public static final String TITLE = "Hexagonal Monolithic API";
    public static final String TERMS_OF_SERVICE_URL = "http://swagger.io/terms/";
    public static final String LICENSE_NAME = "Apache 2.0";
    public static final String LICENSE_URL = "http://springdoc.org";
    public static final String APP_DESCRIPTION = "${app.description}";
    public static final String APP_VERSION = "${app.version}";

    public static final String BASE_URL = "/";
    public static final String CARTS_URL = "/carts";
    public static final String ROOT_ARTICLE_ID = "/{articleId}";
    public static final String ARTICLES_URL = "http://localhost:8080/articles";
    public static final String INCREASE_STOCK_ARTICLE_ID = "/increase-stock/{articleId}";
    public static final String ARTICLE_ID = "articleId";
    public static final String ADDITIONAL_STOCK = "additionalStock";

    public static final String ROL = "rol";
    public static final String PRIVATE = "294A404E635266556A586E327235753878214125442A472D4B6150645367566B";
    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_UI_RESOURCES = "/swagger-ui/**";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ROLE =  "ROLE_";
    public static final String ARTICLE_ID_NOT_NULL = "The article id must not be null";
    public static final String cart_ID_NOT_NULL = "The cart ID must not be null";
    public static final String CART_SAVED_SUCCESSFULLY = "Cart saved successfully";

    public static final String ROL_ADMIN_AUX= "hasAnyRole('admin', 'aux_bodega')";
    public static final String ROL_ADMIN = "hasRole('admin')";
    public static final String ROL_CUSTOMER = "hasAnyRole('customer')";

    public static final String V3_API = "/v3/api-docs/**";
    public static final String AUTH = "/auth/**";
    public static final String ALL_API = "/api/**";

    public static final String JSON = "application/json";
    public static final String ERROR_JWT = "{ \"error\": \"Access denied: Invalid or malformed JWT token\" }";

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}