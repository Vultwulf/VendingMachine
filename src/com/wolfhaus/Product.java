package com.wolfhaus;

public class Product {
    protected String Button;
    protected String Name;
    protected double Price;
    protected int Count;

    public Product(String name, double price, String button, int count)
    {
        this.Name = name;
        this.Price = price;
        this.Button = button;
        this.Count = count;
    }
}