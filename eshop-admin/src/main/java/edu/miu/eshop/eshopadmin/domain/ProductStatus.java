package edu.miu.eshop.eshopadmin.domain;

import lombok.ToString;

@ToString
//@JsonDeserialize(using = ProductStatusDeserializer.class)
public enum ProductStatus {

    NEW,  ACTIVE,  DOWNLOADED;
//
//    private String state;
//
//    public String getState() {
//        return state;
//    }
//
//    ProductStatus(String state) {
//        this.state = state;
//    }
}