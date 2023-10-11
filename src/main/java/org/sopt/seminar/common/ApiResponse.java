package org.sopt.seminar.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
@Getter
public class ApiResponse<T> {
    private final int code;
    private final String status;
    private final boolean success;
    @JsonInclude(NON_NULL)
    private final T data;

    public static <T> ApiResponse<?> of(final HttpStatus httpStatus, final boolean isSuccess) {
        return ApiResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.name())
                .success(isSuccess)
                .build();
    }

    public static <T> ApiResponse<?> of(final HttpStatus httpStatus, final boolean isSuccess, final T data) {
        return ApiResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.name())
                .success(isSuccess)
                .data(data)
                .build();
    }
}
