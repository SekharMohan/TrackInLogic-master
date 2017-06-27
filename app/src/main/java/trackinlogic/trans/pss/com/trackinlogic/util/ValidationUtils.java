package trackinlogic.trans.pss.com.trackinlogic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by z018982 on 19-05-2017.
 */

public final class ValidationUtils {
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";


    public static boolean isValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public static boolean isvalidPhoneNumber(String phoneNumber) {

        return android.util.Patterns.PHONE.matcher(phoneNumber).matches();

    }

    public static boolean isValidEmail(String email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }


}
