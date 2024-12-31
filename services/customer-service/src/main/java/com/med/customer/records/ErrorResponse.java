package com.med.customer.records;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {

}
