package com.med.customer.documents;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Adresse {

    private String street;
    private String houseNumber;
    private String zipCode;
}
