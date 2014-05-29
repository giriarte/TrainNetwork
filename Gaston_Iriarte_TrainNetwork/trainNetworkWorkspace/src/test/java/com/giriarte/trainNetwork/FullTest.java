package com.giriarte.trainNetwork;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.giriarte.trainNetwork.discriminator.ExactStopDiscriminator;
import com.giriarte.trainNetwork.discriminator.MaxDistanceDiscriminator;
import com.giriarte.trainNetwork.discriminator.MaxStopDiscriminator;
import com.giriarte.trainNetwork.service.TrainNetworkService;
import com.giriarte.trainNetwork.service.TrainNetworkServiceImpl;

public class FullTest {
	
	private TrainNetworkService trainNetworkService;
	
	@Before
	public void initialize() {
		trainNetworkService = new TrainNetworkServiceImpl();
		trainNetworkService.addCity('A');
		trainNetworkService.addCity('B');
		trainNetworkService.addCity('C');
		trainNetworkService.addCity('D');
		trainNetworkService.addCity('E');
		trainNetworkService.addRoute("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
	}

	@Test
	public void test1(){
		assertEquals(Integer.valueOf(9), trainNetworkService.getDirectDistance("ABC"));
	}
	
	@Test
	public void test2(){
		assertEquals(Integer.valueOf(5), trainNetworkService.getDirectDistance("AD"));
	}
	@Test
	public void test3(){
		assertEquals(Integer.valueOf(13), trainNetworkService.getDirectDistance("ADC"));
	}
	@Test
	public void test4(){
		assertEquals(Integer.valueOf(22), trainNetworkService.getDirectDistance("AEBCD"));
	}
	@Test
	public void test5(){
		assertEquals(Integer.valueOf(-1), trainNetworkService.getDirectDistance("AED"));
	}

	@Test
	public void test6() {
		assertEquals(Integer.valueOf(2), trainNetworkService.getRoutesCount('C', 'C', new MaxStopDiscriminator(3)));
	}
	
	@Test
	public void test7() {
		assertEquals(Integer.valueOf(3), trainNetworkService.getRoutesCount('A', 'C', new ExactStopDiscriminator(4)));
	}
	
	@Test
	public void test8() {
		assertEquals(Integer.valueOf(9), trainNetworkService.getMiniumDistance('A', 'C'));
	}

	@Test
	public void test9() {
		assertEquals(Integer.valueOf(9), trainNetworkService.getMiniumDistance('B', 'B'));
	}
	
	@Test
	public void test10() {
		assertEquals(Integer.valueOf(7),  trainNetworkService.getRoutesCount('C', 'C', new MaxDistanceDiscriminator(30)));
	}
	
	
}
