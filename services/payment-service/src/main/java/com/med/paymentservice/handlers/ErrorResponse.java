package com.med.paymentservice.handlers;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
