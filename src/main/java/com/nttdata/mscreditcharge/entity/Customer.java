package com.nttdata.mscreditcharge.entity;

import lombok.Data;

@Data
public class Customer {

    String id;

    String name;

    String lastName;

    TypeCustomer typeCustomer;

    String document;

    Integer age;

}

