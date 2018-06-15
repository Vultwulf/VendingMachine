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
    protected int InsertedCoinsValue;

    public VendingMachine(){
        this.Quarter = new Coin(25, 6, 25);
        this.Nickel = new Coin(21, 5, 50);
        this.Dime = new Coin(18, 2, 10);
        this.InvalidCoin = new Coin();

        // Add the initial products
        this.Products.add(new Product("cola", 100, "A1", 1));
        this.Products.add(new Product("chips", 50, "A2", 1));
        this.Products.add(new Product("cola", 65, "A3", 1));
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

    public List<Coin> ReturnCoin()
    {
        List<Coin> returnedCoins = new ArrayList<Coin>();
        // We must make change for the remaining InsertedCoinsValue

        while(this.InsertedCoinsValue > 0)
        {
            Coin coin;
            if(InsertedCoinsValue >= 25)
            {
                coin = new Coin(25, 6, 25);
                returnedCoins.add(coin);
            } else if(InsertedCoinsValue >= 10)
            {
                coin = new Coin(18, 2, 10);
                returnedCoins.add(coin);

            } else {
                coin = new Coin(21, 5, 5);
                returnedCoins.add(coin);
            }
            this.InsertedCoinsValue -= coin.Value;
        }

        return returnedCoins;
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