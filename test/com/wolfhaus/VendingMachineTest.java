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
}