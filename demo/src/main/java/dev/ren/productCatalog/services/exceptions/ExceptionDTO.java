package dev.ren.productCatalog.services.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(HttpStatus errorCode, String errorMessage) {
    public static ExceptionDTOBuilder builder() {
        return new ExceptionDTOBuilder();
    }

    public static class ExceptionDTOBuilder {
        private HttpStatus errorCode;
        private String errorMessage;

        ExceptionDTOBuilder() {
        }

        public ExceptionDTOBuilder errorCode(HttpStatus errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ExceptionDTOBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ExceptionDTO build() {
            return new ExceptionDTO(this.errorCode, this.errorMessage);
        }

        public String toString() {
            return "ExceptionDTO.ExceptionDTOBuilder(errorCode=" + this.errorCode + ", errorMessage=" + this.errorMessage + ")";
        }
    }
}
