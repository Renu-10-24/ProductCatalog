package dev.ren.productCatalog.services.exceptions;

public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
