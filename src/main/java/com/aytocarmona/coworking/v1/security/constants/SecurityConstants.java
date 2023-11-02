package com.aytocarmona.coworking.v1.security.constants;

public class SecurityConstants {
    /**
     * Constructor of SecurityConstants without params
     */
    private SecurityConstants() {
    }

    /**
     * This constant represents request method POST
     */
    public static final String METHOD_POST = "POST";
    /**
     * This constant represents ths secret key to build the token
     */
    public static final String SECRET_KEY = "hKj$#dLm7@*fT9z";
    /**
     * This constant represents the expiration time of the token
     */
    public static final int EXPIRATION_TIME = 86400000;
    /**
     * This constant represents the prefix of the token
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * This constant represents the type of header response and request
     */
    public static final String HEADER_STRING = "Authorization";
    /**
     * This constant represents login url
     */
    public static final String LOGIN_URL = "/api/v1/login";
    /**
     * This constant represents the content type os response
     */
    public static final String APP_JSON = "application/json";
    /**
     * This constant represents the key of the json token response
     */
    public static final String TOKEN_JSON = "token";
    /**
     * URL for the Angular frontend application.
     */
    public static final String ANGULAR_URL = "http://localhost:4200";
    /**
     * URL pattern for the API endpoints.
     */
    public static final String API_URL = "/api/v1/**";
}
