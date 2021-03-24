package example.util;

public class GoRestConstants {
    //  public static final String BASE_URL = "https://gorest.co.in/";
    public static final String BASE_URL = "https://reqres.in/api";
    public static final String PATH_GET_PUT_AND_DELETE = "public-api/users/";
    public static final String PATH_LIST_ALL = "/users";//users?page=2
    public static final String PATH_LIST_ONE = "/users/{id}";//ID
    public static final String PATH_POST = "/users";

    private GoRestConstants() {
        throw new IllegalStateException("Constant class");
    }

}
