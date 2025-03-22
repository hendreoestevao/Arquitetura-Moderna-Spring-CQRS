package com.curso.cqrs.api.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Person {
    public String id;
    public String fullName;
    public Date birthDate;
    public Integer age;
}
