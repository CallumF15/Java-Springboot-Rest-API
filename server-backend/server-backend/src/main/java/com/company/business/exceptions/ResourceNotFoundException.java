
package com.company.business.exceptions;
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id, String message) {
        super(message + "not found with id: " + id);
    }

}
