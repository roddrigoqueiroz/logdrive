package logdrive.dto;

public record ResponseDTO<T>(
        boolean success,
        String message,
        T data
) {
}
