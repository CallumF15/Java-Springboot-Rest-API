
package com.company.business.exceptions;
public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(Long id) {
        super("Country not found with id: " + id);
    }

}
