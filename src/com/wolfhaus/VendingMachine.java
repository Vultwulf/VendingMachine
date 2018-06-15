package com.wolfhaus;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    protected Coin Quarter;
    protected Coin Nickel;
    protected Coin Dime;
    protected Coin InvalidCoin;
    protected List<Coin> InsertedCoins = new ArrayList<Coin>();
    protected List<Product> Products = new ArrayList<Product>();
    protected String Display;
    protected double InsertedCoinsValue;

    public VendingMachine(){
        this.Quarter = new Coin(25, 6, 0.25);
        this.Nickel = new Coin(21, 5, 0.5);
        this.Dime = new Coin(18, 2, 0.10);
        this.InvalidCoin = new Coin();

        // Add the initial products
        this.Products.add(new Product("cola", 1.00, "A1", 1));
        this.Products.add(new Product("chips", 0.5, "A2", 1));
        this.Products.add(new Product("cola", 0.65, "A3", 1));
    }

    public Coin IdentifyCoin(Coin coin) {

        if(coin.Size == this.Quarter.Size && coin.Weight == this.Quarter.Weight)
        {
            // Coin identified as a Quarter
            return this.Quarter;
        } else if (coin.Size == this.Nickel.Size && coin.Weight == this.Nickel.Weight)
        {
            // Coin identified as a Nickel
            return this.Nickel;
        } else if (coin.Size == this.Dime.Size && coin.Weight == this.Dime.Weight)
        {
            // Coin identified as a Dime
            return this.Dime;
        }

        return this.InvalidCoin;
    }

    public void InsertCoin(Coin coin)
    {
        this.InsertedCoins.add(coin);
        InsertedCoinsValue += coin.Value;
    }

    public void SelectProduct(String button)
    {
        Product selectedProduct = this.Products.stream().filter(p -> p.Button.equals(button)).findFirst().orElse(null);

        if(this.InsertedCoinsValue < selectedProduct.Price) {
            this.Display = "INSERT COIN";
        } else {
            this.Display = "THANK YOU";
            this.InsertedCoinsValue -= selectedProduct.Price;
        }
    }
}