package com.wenba.rest.controller.vo;



public class Item {

    private String name;
    private int weight;
    private ItemCode itemCode;
    private int price;

    public Item(String name, int price, int weight, ItemCode itemCode) {
        super();
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.itemCode = itemCode;
    }
}
