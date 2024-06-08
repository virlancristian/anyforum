package net.anyforum.backend.models.database;

import jakarta.persistence.*;

@Entity
@Table(name = "user_auth_tokens")
public class UserAuthTokenDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_no")
    private Integer entryNumber;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "token")
    private String token;

    @Column(name = "issued_at")
    private String issuedAt;

    @Column(name = "expires_at")
    private String expiresAt;

    public UserAuthTokenDbEntity(String userID, String token, String issuedAt, String expiresAt) {
        this.userID = userID;
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    public UserAuthTokenDbEntity(Integer entryNumber, String userID, String token, String issuedAt, String expiresAt) {
        this.entryNumber = entryNumber;
        this.userID = userID;
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(String issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "UserAuthTokenDbEntity{" +
                "userID='" + userID + '\'' +
                ", token='" + token + '\'' +
                ", issuedAt='" + issuedAt + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                '}';
    }
}
