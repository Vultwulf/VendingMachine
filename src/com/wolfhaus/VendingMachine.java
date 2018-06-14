package com.wolfhaus;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    protected Coin Quarter;
    protected Coin Nickel;
    protected Coin Dime;
    protected Coin InvalidCoin;
    protected List<Coin> InsertedCoins = new ArrayList<Coin>();

    public VendingMachine(){
        this.Quarter = new Coin(25, 6, 25);
        this.Nickel = new Coin(21, 5, 5);
        this.Dime = new Coin(18, 2, 10);
        this.InvalidCoin = new Coin();
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
    }
}