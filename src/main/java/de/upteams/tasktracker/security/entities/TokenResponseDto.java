package de.upteams.tasktracker.security.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Response that contains access and refresh tokens generated for User during login process
 */
@Setter
@Getter
public class TokenResponseDto {

    private String accessToken;

    private String refreshToken;

    public TokenResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TokenResponseDto that = (TokenResponseDto) o;
        return Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken);
    }

    @Override
    public String toString() {
        return String.format("Access token - %s, refresh token - %s.", accessToken, refreshToken);
    }
}
