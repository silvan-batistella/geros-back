package dev.batistella.geros.util;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.apache.commons.lang3.StringUtils;

public abstract class EmailUtils {

    public static boolean validarEmail(String email) {

        if (StringUtils.isEmpty(email)) {

            return false;
        } else {

            boolean result = true;
            try {
                InternetAddress emailAddr = new InternetAddress(email);
                emailAddr.validate();
            } catch (AddressException ex) {
                result = false;
            }
            return result;
        }
    }
}
