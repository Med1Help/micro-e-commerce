package com.med.customer.records;

import com.med.customer.documents.Adresse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Adresse adress
) {
}
