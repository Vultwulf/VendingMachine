package com.wolfhaus;

import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {

    public VendingMachineTest() {

    }

    @Test
    public void identifyCoinTest() {
        VendingMachine vendingMachine = new VendingMachine();
        Coin coinAttributes = new Coin(25, 6);
        assertEquals(vendingMachine.Quarter, vendingMachine.IdentifyCoin(coinAttributes));

        coinAttributes = new Coin(21, 5);
        assertEquals(vendingMachine.Nickel, vendingMachine.IdentifyCoin(coinAttributes));

        coinAttributes = new Coin(18, 2);
        assertEquals(vendingMachine.Dime, vendingMachine.IdentifyCoin(coinAttributes));

        coinAttributes = new Coin(56, 20);
        assertEquals(vendingMachine.InvalidCoin, vendingMachine.IdentifyCoin(coinAttributes));
    }

    @Test
    public void acceptCoinTest() {
        VendingMachine vendingMachine = new VendingMachine();

        // Determine what type of coin this is
        Coin coinAttributes = new Coin(25, 6);
        Coin coin = vendingMachine.IdentifyCoin(coinAttributes);

        // Insert this coin into the machine
        vendingMachine.InsertCoin(coin);

        assertEquals(vendingMachine.Quarter, vendingMachine.InsertedCoins.get(0));
        assertEquals(1, vendingMachine.InsertedCoins.size());
    }

    @Test
    public void selectProductTest() {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.SelectProduct("A1");
        assertEquals("INSERT COIN", vendingMachine.Display);
    }
}