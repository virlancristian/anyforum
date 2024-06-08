package net.anyforum.backend.services.user;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.models.database.UserAuthTokenDbEntity;
import net.anyforum.backend.repos.user.UserAuthTokenDbRepo;
import net.anyforum.backend.util.regex.RegexStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserAuthTokenDbService {
    @Autowired
    private UserAuthTokenDbRepo userAuthTokenDbRepo;
    private static final long DAY_LENGTH_MS = 86400000;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String addAuthToken(String userID) {
        String token = RegexStringGenerator.generateString(AuthConstants.AUTH_TOKEN_REGEX);
        String encryptedtoken = passwordEncoder.encode(token);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date issueDate = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(System.currentTimeMillis() * DAY_LENGTH_MS);

        userAuthTokenDbRepo.save(new UserAuthTokenDbEntity(userID, encryptedtoken, dateFormatter.format(issueDate), dateFormatter.format(expiryDate)));

        return token;
    }
}
