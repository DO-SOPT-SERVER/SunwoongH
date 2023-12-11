package org.sopt.api.auth;

import lombok.RequiredArgsConstructor;
import org.sopt.common.error.ErrorStatus;
import org.sopt.common.error.UnauthorizedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordHandler {
    private final PasswordEncoder passwordEncoder;

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public void validatePassword(String password, String encodedPassword) {
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new UnauthorizedException(ErrorStatus.MISMATCH_PASSWORD);
        }
    }
}
