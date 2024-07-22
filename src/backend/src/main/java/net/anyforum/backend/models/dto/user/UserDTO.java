package net.anyforum.backend.models.dto.user;

import net.anyforum.backend.models.api.auth.AuthRequestBody;
import net.anyforum.backend.models.api.user.UserAPIRequest;
import net.anyforum.backend.models.api.user.UserDataAPIResponse;
import net.anyforum.backend.models.database.user.UserDataDbEntity;
import net.anyforum.backend.models.database.user.UserDbEntity;

public class UserDTO {
    private UserDbEntity userDbEntity;
    private UserDataDbEntity userDataDbEntity;
    private UserAPIRequest userAPIRequest;
    private UserDataAPIResponse userDataAPIResponse;

    public UserDTO() {}

    public UserDTO(UserDbEntity userDbEntity, UserDataDbEntity userDataDbEntity) {
        this.userDbEntity = userDbEntity;
        this.userDataDbEntity = userDataDbEntity;
        this.userAPIRequest = null;
        this.userDataAPIResponse = null;
    }

    public UserDbEntity mapToUserDbEntity(AuthRequestBody newUserData,
                                          String id,
                                          String encryptedPassword,
                                          String salt) {
        UserDbEntity userDbEntity = new UserDbEntity();

        userDbEntity.setUsername(newUserData.getUsername());
        userDbEntity.setEmail(newUserData.getEmail());
        userDbEntity.setEncryptedPassword(encryptedPassword);
        userDbEntity.setSalt(salt);
        userDbEntity.setId(id);

        userDbEntity.setBanned(false);
        userDbEntity.setMuted(false);
        userDbEntity.setNsfwOn(false);

        return userDbEntity;
    }

    public UserDataAPIResponse mapToAPIResponse() {
        if(userDbEntity == null || userDataDbEntity == null) {
            return null;
        }

        return new UserDataAPIResponse(
                userDbEntity.getUsername(),
                userDbEntity.getId(),
                userDataDbEntity.getAboutMe(),
                userDataDbEntity.getProfilePictureURL(),
                userDataDbEntity.getJoinedAt(),
                userDbEntity.isMuted(),
                userDbEntity.isBanned(),
                userDbEntity.isNsfwOn()
        );
    }

    public UserDataDbEntity mapNewUserData(UserAPIRequest newUserData, UserDataDbEntity oldUserData) {
        String newAboutMe = newUserData.getAboutMe();
        String newProfilePictureURL = newUserData.getProfilePictureURL();

        if(newAboutMe != null) {
            oldUserData.setAboutMe(newAboutMe);
        }

        if(newProfilePictureURL != null) {
            oldUserData.setProfilePictureURL(newProfilePictureURL);
        }

        return oldUserData;
    }

    public UserDbEntity mapNewUserCredentials(UserAPIRequest newUserCredentials, UserDbEntity oldUserCredentials) {
        String newUsername = newUserCredentials.getUsername();

        if(newUsername != null) {
            oldUserCredentials.setUsername(newUsername);
        }

        return oldUserCredentials;
    }
}
