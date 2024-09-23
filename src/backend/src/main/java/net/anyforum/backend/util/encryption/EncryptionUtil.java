package net.anyforum.backend.util.encryption;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.LinkedList;
import java.util.List;

public class EncryptionUtil {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static List<String> getEncryptedPassword(@NotNull String password) {
        String encryptedPasswordFull = passwordEncoder.encode(password);
        String[] splitEncryptedPassword = encryptedPasswordFull.split("\\$");
        String salt = "$".concat(splitEncryptedPassword[1].concat("$".concat(splitEncryptedPassword[2].concat("$".concat(splitEncryptedPassword[3].substring(0, 22))))));
        String encryptedPassword = splitEncryptedPassword[splitEncryptedPassword.length - 1].substring(22);

        return new LinkedList<>(List.of(salt, encryptedPassword));
    }
}
