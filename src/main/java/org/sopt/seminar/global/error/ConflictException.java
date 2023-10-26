package org.sopt.seminar.global.error;

public class ConflictException extends BusinessException {
    public ConflictException() {
        super(ErrorStatus.CONFLICT);
    }

    public ConflictException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
