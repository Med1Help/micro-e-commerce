package com.med.paymentservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException {

    private final String msg;
}
