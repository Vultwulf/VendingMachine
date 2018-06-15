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
        // Insert one quarter
        Coin coinAttributes = new Coin(25, 6);
        Coin coin = this.VendingMachine.IdentifyCoin(coinAttributes);
        this.VendingMachine.InsertCoin(coin);

        // Determine what the coin is by the attributes of the coin
        assertEquals(this.VendingMachine.Quarter, this.VendingMachine.InsertedCoins.get(0));
        assertEquals(1, this.VendingMachine.InsertedCoins.size());
    }

    @Test
    public void selectProductColaWithNoMoneyTest() {
        // Select a product
        this.VendingMachine.SelectProduct("A1");
        assertEquals("INSERT COIN", this.VendingMachine.Display);
    }

    @Test
    public void selectProductChipsWithMoneyTest() {
        // Insert two quarters
        Coin coinAttributes = new Coin(25, 6);
        Coin coin = this.VendingMachine.IdentifyCoin(coinAttributes);
        this.VendingMachine.InsertCoin(coin);
        this.VendingMachine.InsertCoin(coin);

        // Select a product
        this.VendingMachine.SelectProduct("A2");
        assertEquals("THANK YOU", this.VendingMachine.Display);
        assertEquals(0, this.VendingMachine.InsertedCoinsValue);
    }


    @Test
    public void selectProductSoldOut() {
        // Insert two quarters
        Coin coinAttributes = new Coin(25, 6);
        Coin coin = this.VendingMachine.IdentifyCoin(coinAttributes);
        this.VendingMachine.InsertCoin(coin);
        this.VendingMachine.InsertCoin(coin);

        // Select a product
        this.VendingMachine.SelectProduct("A2");
        assertEquals("THANK YOU", this.VendingMachine.Display);
        assertEquals(0, this.VendingMachine.InsertedCoinsValue);

        // Insert two quarters
        this.VendingMachine.InsertCoin(coin);
        this.VendingMachine.InsertCoin(coin);

        // Select a product
        this.VendingMachine.SelectProduct("A2");
        assertEquals("SOLD OUT", this.VendingMachine.Display);

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

        // Insert two nickels
        coinAttributes = new Coin(21, 5);
        coin = this.VendingMachine.IdentifyCoin(coinAttributes);
        this.VendingMachine.InsertCoin(coin);
        this.VendingMachine.InsertCoin(coin);

        // Expect 35 cents back
        List<Coin> returnedCoins = this.VendingMachine.SelectProduct("A2");
        assertEquals("THANK YOU", this.VendingMachine.Display);

        // Expect one quarter, one dime back
        assertEquals(3, returnedCoins.size());
        assertEquals(25, returnedCoins.get(0).Value);
        assertEquals(10, returnedCoins.get(1).Value);
        assertEquals(10, returnedCoins.get(2).Value);
    }

    @Test
    public void exactChangeTest() {
        // Test the normal mode functionality first
        this.VendingMachine.SelectProduct("A1");
        assertEquals("INSERT COIN", this.VendingMachine.Display);

        // Set the machine to Exact Change Only mode
        this.VendingMachine.ExactChangeOnly = true;
        this.VendingMachine.SelectProduct("A1");
        assertEquals("EXACT CHANGE ONLY", this.VendingMachine.Display);
    }
}