package org.sopt.common.error;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(ErrorStatus.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
