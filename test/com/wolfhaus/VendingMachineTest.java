package com.wolfhaus;

import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {

    public VendingMachineTest() {

    }

    @Test
    public void identifyCoinTest() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("Quarter", vendingMachine.IdentifyCoin());
    }
}