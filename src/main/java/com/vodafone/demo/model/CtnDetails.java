package com.vodafone.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CtnDetails extends BaseDetails{
    private List<Product> installedProducts;
    private String subscriptionType;

}
