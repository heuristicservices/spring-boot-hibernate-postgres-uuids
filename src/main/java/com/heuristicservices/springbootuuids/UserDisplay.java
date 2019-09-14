package com.heuristicservices.springbootuuids;

import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface UserDisplay
{
    UUID getId();

    String getEmail();

    @Value("#{T(java.time.Period).between(target.dateOfBirth, T(java.time.LocalDate).now()).getYears()}")
    Integer getAge();
}
