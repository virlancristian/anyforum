package net.anyforum.backend.models.user;

public class UserPublicDataUpdate extends BasicUser {
    private String aboutMe;
    private Boolean isNSFWOn;

    public UserPublicDataUpdate() {
        super();
    }

    public UserPublicDataUpdate(String username, String aboutMe, Boolean isNSFWOn) {
        super(username, "");
        this.aboutMe = aboutMe;
        this.isNSFWOn = isNSFWOn;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Boolean getIsNSFWOn() {
        return isNSFWOn;
    }

    public void setIsNSFWOn(Boolean isNSFWOn) {
        this.isNSFWOn = isNSFWOn;
    }

    @Override
    public String toString() {
        return "{\"UserPublicDataUpdate\":{"
                + "\"aboutMe\":\"" + aboutMe + "\""
                + ", \"isNSFWOn\":\"" + isNSFWOn + "\""
                + "}}";
    }
}
