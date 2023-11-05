package org.sopt.seminar.global.error;

public class InvalidValueException extends BusinessException {
    public InvalidValueException() {
        super(ErrorStatus.BAD_REQUEST);
    }

    public InvalidValueException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
