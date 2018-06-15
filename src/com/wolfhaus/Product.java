package com.wolfhaus;

import java.math.BigDecimal;
import java.util.Currency;

public class Product {
    protected String Button;
    protected String Name;
    protected int Price;
    protected int Count;

    public Product(String name, int price, String button, int count)
    {
        this.Name = name;
        this.Price = price;
        this.Button = button;
        this.Count = count;
    }
}