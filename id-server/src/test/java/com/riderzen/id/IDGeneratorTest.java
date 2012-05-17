package com.riderzen.id;

import com.riderzen.id.server.IDGenerator;
import com.riderzen.id.server.IDGeneratorImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigInteger;

import static org.testng.Assert.*;
import static org.testng.Reporter.log;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-10
 */
@Test(groups = {"unit"})
public class IDGeneratorTest {

    private IDGenerator generator;

    @BeforeClass
    public void init() {
        generator = new IDGeneratorImpl();
    }

    @Test
    public void testGenerateHex() throws IDException {
        String hex = generator.generateHex();
        log("hex: " + hex, true);

        assertNotNull(hex);
        assertTrue(hex.length() == 24);
    }

    @Test
    public void testGenerate() throws IDException {
        String id = generator.generate();
        log("id: " + id, true);

        Assert.assertNotNull(id);
        Assert.assertTrue(id.length() > 0);
        Assert.assertTrue(id.length() <= 29);
    }

    @Test(invocationCount = 1)
    public void testBinaryId1() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 0;
        Integer reserve = 0;
        Integer nodeId = 0;
        Integer sequence = 0;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test
    public void testBinaryId2() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 63;
        Integer reserve = 15;
        Integer nodeId = 4095;
        Integer sequence = 1023;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test
    public void testBinaryId3() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 1;
        Integer reserve = 1;
        Integer nodeId = 1;
        Integer sequence = 1;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test
    public void testBinaryId4() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 62;
        Integer reserve = 14;
        Integer nodeId = 4094;
        Integer sequence = 1022;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test
    public void testBinaryId5() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 31;
        Integer reserve = 8;
        Integer nodeId = 2047;
        Integer sequence = 511;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId6() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 64;
        Integer reserve = 8;
        Integer nodeId = 2047;
        Integer sequence = 511;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId7() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 63;
        Integer reserve = 16;
        Integer nodeId = 2047;
        Integer sequence = 511;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId8() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 63;
        Integer reserve = 15;
        Integer nodeId = 4096;
        Integer sequence = 511;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId9() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 63;
        Integer reserve = 15;
        Integer nodeId = 4095;
        Integer sequence = 1024;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId10() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = -4;
        Integer reserve = 15;
        Integer nodeId = 4095;
        Integer sequence = 1023;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);


        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId11() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 4;
        Integer reserve = -15;
        Integer nodeId = 4095;
        Integer sequence = 1023;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId12() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 4;
        Integer reserve = 15;
        Integer nodeId = -4095;
        Integer sequence = 1023;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId13() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = 4;
        Integer reserve = 15;
        Integer nodeId = 4095;
        Integer sequence = -1023;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }

    @Test(expectedExceptions = {IDException.class})
    public void testBinaryId14() throws IDException {
        Long time = System.currentTimeMillis();
        Integer region = -4;
        Integer reserve = 888;
        Integer nodeId = 4095;
        Integer sequence = -1023;

        String binaryId = generator.generateBinary(time, region, reserve, nodeId, sequence);

        log("binaryId = " + binaryId, true);

        Assert.assertNotNull(binaryId);
        Assert.assertEquals(binaryId.length(), 96);
        Assert.assertTrue(new BigInteger(binaryId.substring(0, 64), 2).longValue() == time);
        Assert.assertTrue(new BigInteger(binaryId.substring(64, 70), 2).intValue() == region);
        Assert.assertTrue(new BigInteger(binaryId.substring(70, 74), 2).intValue() == reserve);
        Assert.assertTrue(new BigInteger(binaryId.substring(74, 86), 2).intValue() == nodeId);
        Assert.assertTrue(new BigInteger(binaryId.substring(86, 96), 2).intValue() == sequence);
    }
}
