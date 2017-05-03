/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas
 */
public class CargoTest {

    public CargoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetIdCargo() {
        System.out.println("getIdCargo");
        Cargo instance = new Cargo(12, "Professor Basico");
        Integer expResult = 12;
        Integer result = instance.getIdCargo();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdCargo method, of class Cargo.
     */
    @Test
    public void testSetIdCargo() {
        System.out.println("setIdCargo");
        Integer novoId = 3600;
        Cargo instance = new Cargo();
        assertEquals(instance.getIdCargo(), null);
        instance.setIdCargo(3600);
        assertEquals(instance.getIdCargo(), novoId);
    }

    /**
     * Test of getNome method, of class Cargo.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Cargo instance = new Cargo(19, "Professor Basico");
        String expResult = "Professor Basico";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNome method, of class Cargo.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String novoNome = "Professor Medio";
        Cargo instance = new Cargo();
        assertEquals(instance.getNome(), null);
        instance.setNome(novoNome);
        assertEquals(instance.getNome(), novoNome);
    }
}
