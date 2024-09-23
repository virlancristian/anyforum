package net.anyforum.backend.models.user;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.util.encryption.EncryptionUtil;
import net.anyforum.backend.util.regex.RegexStringGenerator;
import net.anyforum.backend.util.time.DateHelper;

import java.util.List;

public class UserDTO {
    public UserDTO() {}

    public static UserDataDbEntity mapUpdatedUserData(UserPublicDataUpdate userPublicDataUpdate, UserDataDbEntity oldUserData) {
        String newUsername = userPublicDataUpdate.getUsername();
        String newAboutMe = userPublicDataUpdate.getAboutMe();

        if(newUsername != null) {
            oldUserData.setUsername(newUsername);
        }

        if(newAboutMe != null) {
            oldUserData.setAboutMe(newAboutMe);
        }

        return oldUserData;
    }

    public static UserDbEntity mapUpdatedUserDbEntity(UserPublicDataUpdate userPublicDataUpdate, UserDbEntity oldUserDbEntity) {
        Boolean updatedIsNSFWOn = userPublicDataUpdate.getIsNSFWOn();

        if(updatedIsNSFWOn != null) {
            oldUserDbEntity.setNsfwOn(updatedIsNSFWOn);
        }

        return oldUserDbEntity;
    }

    public static UserDbEntity mapToUserDbEntity(UserSignup userSignupData) {
        String id = RegexStringGenerator.generateString(AuthConstants.ID_REGEX);
        List<String> encryptedPassword = EncryptionUtil.getEncryptedPassword(userSignupData.getPassword());

        return new UserDbEntity(id, userSignupData.getEmail(),  encryptedPassword.get(0), encryptedPassword.get(1));
    }

    public static UserDataDbEntity mapToUserDataDbEntity(String id) {
        String todaysDate = DateHelper.getCurrentDate();

        return new UserDataDbEntity(id, "", todaysDate);
    }

    public static UserPublicData mapToUserPublicData(UserDbEntity userDbEntity, UserDataDbEntity userDataDbEntity) {
        return new UserPublicData(
                userDataDbEntity.getUserID(),
                userDataDbEntity.getUsername(),
                userDataDbEntity.getAboutMe(),
                userDataDbEntity.getJoinedAt(),
                userDbEntity.isMuted(),
                userDbEntity.isBanned(),
                userDbEntity.isNsfwOn()
        );
    }

    public static UserSessionData mapToUserSessionData(UserDbEntity userDbEntity, UserDataDbEntity userDataDbEntity) {
        return new UserSessionData(
                userDataDbEntity.getUserID(),
                userDataDbEntity.getUsername(),
                userDbEntity.isMuted(),
                userDbEntity.isBanned(),
                userDbEntity.isNsfwOn()
        );
    }
}
