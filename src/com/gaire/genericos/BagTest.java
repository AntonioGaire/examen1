package com.gaire.genericos;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class BagTest {

	@Test
	void testAddE() {
		Integer i = 5;
		Bag<Integer> bg = new Bag<>();
		bg.add(i);
		
		HashSet<Integer> hs = new HashSet<>();
		hs.add(i);
		
		assertEquals(hs, bg.uniqueSet());
	}

	@Test
	void testAddEInt() {
		Integer i = 5;
		Bag<Integer> bg = new Bag<>();
		bg.add(i, 7);
		
		assertEquals(7, bg.getCount(i));
	}

	@Test
	void testGetCount() {
		Integer i = 5;
		Bag<Integer> bg = new Bag<>();
		bg.add(i, 7);
		
		assertEquals(7, bg.getCount(i));
	}

	@Test
	void testRemoveE() {
		Integer i = 5;
		Bag<Integer> bg = new Bag<>();
		bg.add(i, 7);
		bg.remove(i);
		
		assertEquals(0, bg.getCount(i));
	}

	@Test
	void testRemoveEInt() {
		Integer i = 5;
		Bag<Integer> bg = new Bag<>();
		bg.add(i, 7);
		bg.remove(i,5);
		
		assertEquals(2, bg.getCount(i));
	}

	@Test
	void testSize() {
		Integer i = 5;
		Bag<Integer> bg = new Bag<>();
		bg.add(i, 7);
		assertEquals(7, bg.size());
	}

	@Test
	void testUniqueSet() {
		Integer i = 5, j=7;
		Bag<Integer> bg = new Bag<>();
		bg.add(i);
		bg.add(j,5);
		
		HashSet<Integer> hs = new HashSet<>();
		hs.add(i);
		hs.add(j);
		
		assertEquals(hs, bg.uniqueSet());
	}

}
