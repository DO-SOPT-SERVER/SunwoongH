package org.sopt.common.error;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(ErrorStatus.CONFLICT);
    }

    public UnauthorizedException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
