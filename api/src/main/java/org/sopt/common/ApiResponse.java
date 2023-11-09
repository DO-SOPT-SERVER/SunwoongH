package org.sopt.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.sopt.error.ErrorStatus;
import org.springframework.http.ResponseEntity;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ApiResponse<T> {
    private final int status;
    private final String message;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private final T data;

    private static ApiResponse<?> of(SuccessStatus successStatus) {
        return builder()
                .status(successStatus.getHttpStatus().value())
                .message(successStatus.getMessage())
                .build();
    }

    private static <T> ApiResponse<?> of(SuccessStatus successStatus, T data) {
        return builder()
                .status(successStatus.getHttpStatus().value())
                .message(successStatus.getMessage())
                .data(data)
                .build();
    }

    private static ApiResponse<?> of(ErrorStatus errorStatus) {
        return builder()
                .status(errorStatus.getHttpStatus().value())
                .message(errorStatus.getMessage())
                .build();
    }

    public static ResponseEntity<ApiResponse<?>> success(SuccessStatus successStatus) {
        return ResponseEntity.status(successStatus.getHttpStatus())
                .body(ApiResponse.of(successStatus));
    }

    public static <T> ResponseEntity<ApiResponse<?>> success(SuccessStatus successStatus, T data) {
        return ResponseEntity.status(successStatus.getHttpStatus())
                .body(ApiResponse.of(successStatus, data));
    }

    public static ResponseEntity<ApiResponse<?>> failure(ErrorStatus errorStatus) {
        return ResponseEntity.status(errorStatus.getHttpStatus())
                .body(ApiResponse.of(errorStatus));
    }
}
