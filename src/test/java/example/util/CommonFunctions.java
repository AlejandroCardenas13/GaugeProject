package example.util;

import java.util.UUID;

public class CommonFunctions {

    public static String getId() {
        return UUID.randomUUID().toString();
    }


    private CommonFunctions() {
        throw new IllegalStateException("Utility function class");
    }
}
