package net.anyforum.backend.models.database.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_no")
    private Integer entryNumber;

    @Column(unique = true)
    private String id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String salt;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Column(name = "is_banned")
    private boolean isBanned;

    @Column(name = "is_muted")
    private boolean isMuted;

    @Column(name = "nsfw_on")
    private boolean nsfwOn;

    public UserDbEntity() {}

    public UserDbEntity(String id, String username, String email, String salt, String encryptedPassword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.encryptedPassword = encryptedPassword;
        this.isBanned = false;
        this.isMuted = false;
        this.nsfwOn = false;
    }

    public UserDbEntity(Integer entryNumber, String id, String username, String email, String salt, String encryptedPassword) {
        this.entryNumber = entryNumber;
        this.id = id;
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.encryptedPassword = encryptedPassword;
        this.isBanned = false;
        this.isMuted = false;
        this.nsfwOn = false;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public boolean isNsfwOn() {
        return nsfwOn;
    }

    public void setNsfwOn(boolean nsfwOn) {
        this.nsfwOn = nsfwOn;
    }

    @Override
    public String toString() {
        return "User{" +
                "entryNumber=" + entryNumber +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", isBanned=" + isBanned +
                ", isMuted=" + isMuted +
                ", nsfwOn=" + nsfwOn +
                '}';
    }
}
