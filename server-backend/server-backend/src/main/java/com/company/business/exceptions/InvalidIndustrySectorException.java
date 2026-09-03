package com.company.business.exceptions;

public class InvalidIndustrySectorException extends RuntimeException
{
    public InvalidIndustrySectorException(Long industryId, Long sectorId) {
        super(
            "Industry with ID " + industryId +
                " does not belong to sector with ID " + sectorId
        );
    }
}
