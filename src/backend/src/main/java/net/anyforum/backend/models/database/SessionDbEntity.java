package net.anyforum.backend.models.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="user_sessions")
public class SessionDbEntity {
    @Id
    @Column(name = "sessionID")
    private String sessionID;

    @Column(name = "userID")
    private String userID;

    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "expires_at")
    private String expiresAt;

    public SessionDbEntity() {}

    public SessionDbEntity(String userID, String sessionToken, String expiresAt) {
        this.userID = userID;
        this.sessionToken = sessionToken;
        this.expiresAt = expiresAt;
    }

    public SessionDbEntity(String sessionID, String userID, String sessionToken, String expiresAt) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.sessionToken = sessionToken;
        this.expiresAt = expiresAt;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "SessionDbEntity{" +
                "sessionID='" + sessionID + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                '}';
    }
}
