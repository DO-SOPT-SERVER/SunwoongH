package org.sopt.common.error;

public class ConflictException extends BusinessException {
    public ConflictException() {
        super(ErrorStatus.CONFLICT);
    }

    public ConflictException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
