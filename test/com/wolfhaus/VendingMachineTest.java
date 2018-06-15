package com.wolfhaus;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineTest {

    private VendingMachine VendingMachine;

    public VendingMachineTest() {

    }

    @Before
    public void setUp() {
        // Instantiate the VendingMachine object
        this.VendingMachine = new VendingMachine();
    }

    @Test
    public void identifyCoinTest() {
        Coin coinAttributes = new Coin(25, 6);
        assertEquals(this.VendingMachine.Quarter, this.VendingMachine.IdentifyCoin(coinAttributes));

        coinAttributes = new Coin(21, 5);
        assertEquals(this.VendingMachine.Nickel, this.VendingMachine.IdentifyCoin(coinAttributes));

        coinAttributes = new Coin(18, 2);
        assertEquals(this.VendingMachine.Dime, this.VendingMachine.IdentifyCoin(coinAttributes));

        coinAttributes = new Coin(56, 20);
        assertEquals(this.VendingMachine.InvalidCoin, this.VendingMachine.IdentifyCoin(coinAttributes));
    }

    @Test
    public void acceptCoinTest() {
        // Determine what type of coin this is
        Coin coinAttributes = new Coin(25, 6);
        Coin coin = this.VendingMachine.IdentifyCoin(coinAttributes);

        // Insert this coin into the machine
        this.VendingMachine.InsertCoin(coin);

        assertEquals(this.VendingMachine.Quarter, this.VendingMachine.InsertedCoins.get(0));
        assertEquals(1, this.VendingMachine.InsertedCoins.size());
    }

    @Test
    public void selectProductColaWithNoMoneyTest() {
        this.VendingMachine.SelectProduct("A1");
        assertEquals("INSERT COIN", this.VendingMachine.Display);
    }

    @Test
    public void selectProductChipsWithMoneyTest() {
        Coin coinAttributes = new Coin(25, 6);
        Coin coin = this.VendingMachine.IdentifyCoin(coinAttributes);

        this.VendingMachine.InsertCoin(coin);
        this.VendingMachine.InsertCoin(coin);

        this.VendingMachine.SelectProduct("A2");
        assertEquals("THANK YOU", this.VendingMachine.Display);
        assertEquals(0, this.VendingMachine.InsertedCoinsValue);
    }

    @Test
    public void returnCoinTest() {
        // Insert three quarters
        Coin coinAttributes = new Coin(25, 6);
        Coin coin = this.VendingMachine.IdentifyCoin(coinAttributes);
        this.VendingMachine.InsertCoin(coin);
        this.VendingMachine.InsertCoin(coin);
        this.VendingMachine.InsertCoin(coin);

        // Insert one dime
        coinAttributes = new Coin(18, 2);
        coin = this.VendingMachine.IdentifyCoin(coinAttributes);
        this.VendingMachine.InsertCoin(coin);

        // Expect 35 cents back
        List<Coin> returnedCoins = this.VendingMachine.SelectProduct("A2");
        assertEquals("THANK YOU", this.VendingMachine.Display);

        // Expect one quarter, one dime back
        assertEquals(2, returnedCoins.size());
        assertEquals(25, returnedCoins.get(0).Value);
        assertEquals(10, returnedCoins.get(1).Value);
    }
}