package com.vodafone.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDetails {
    private String identifier;
    private String customerName;
    private String address;
}
