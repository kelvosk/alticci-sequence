package com.alticci.sequence.handler;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
public class ExceptionResponse {

    private HttpStatus status;
    private List<String> message;

    public String toJson(){
        return this.formatError().toString();
    }

    private StringBuilder formatError() {
        return new StringBuilder()
                .append("{")
                .append("\n")
                .append("\"error\":\"").append(status.value()).append("\"")
                .append(",")
                .append("\n")
                .append("\"message\":\"").append(message).append("\"")
                .append("\n")
                .append("}");
    }
}
