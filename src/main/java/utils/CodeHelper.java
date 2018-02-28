package utils;

public class CodeHelper {
    public static boolean isNullOfStr(String content) {
        if (content == null || content.equals("")) {
            return true;
        }
        return false;
    }
}
