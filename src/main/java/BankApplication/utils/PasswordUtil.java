package BankApplication.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private final  static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        public static String hashPassword(String password) {
            return encoder.encode(password);
        }

        public static boolean verifyPassword(String inputtedPassword, String storedPassword) {
            return encoder.matches(inputtedPassword, storedPassword);
        }
    }

    