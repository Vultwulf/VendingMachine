package com.wolfhaus;

import java.math.BigDecimal;

public class Coin {
    protected int Size;
    protected int Weight;
    protected int Value;

    public Coin(int coinSize, int coinWeight, int coinValue)
    {
        this.Size = coinSize;
        this.Weight = coinWeight;
        this.Value = coinValue;
    }

    public Coin(int coinSize, int coinWeight)
    {
        this.Size = coinSize;
        this.Weight = coinWeight;
    }

    public Coin()
    {
        this.Size = 0;
        this.Weight = 0;
        this.Value = 0;
    }
}