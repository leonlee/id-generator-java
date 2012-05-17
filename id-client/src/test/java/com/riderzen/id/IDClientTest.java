package com.riderzen.id;

import com.riderzen.id.api.IDClient;
import com.riderzen.id.api.IDClientFactory;
import com.riderzen.id.api.IDInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;
import static org.testng.Reporter.log;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-16
 */
@Test(groups = {"unit"})
public class IDClientTest {
    private IDClient idClient;

    @BeforeClass
    public void init() {
        idClient = IDClientFactory.getIdClient();
    }

    @Test
    public void testGenerate() {
        String id = idClient.generateID();

        log("id: " + id, true);

        assertNotNull(id);
        assertTrue(id.length() > 0);
        assertTrue(id.length() <= 29);
    }

    @Test
    public void testDecode() {
        long before = System.currentTimeMillis();
        String id = idClient.generateID();
        long after = System.currentTimeMillis();

        IDInfo idInfo = idClient.decode(id);

        log("idInfo: " + idInfo, true);

        assertNotNull(idInfo);
        assertTrue(idInfo.getTimestamp() >= before);
        assertTrue(idInfo.getTimestamp() <= after);
        assertTrue(idInfo.getRegion() == 0);
        assertTrue(idInfo.getReserved() == 0);
        assertTrue(idInfo.getNode() == 0);
        assertTrue(idInfo.getSequence() >= 0);
        assertTrue(idInfo.getSequence() < 1024);
    }

    @Test
    public void testDecodeHex() {
        long before = System.currentTimeMillis();
        String id = idClient.generateHexID();
        long after = System.currentTimeMillis();

        IDInfo idInfo = idClient.decodeHex(id);

        log("idInfo: " + idInfo, true);

        assertNotNull(idInfo);
        assertTrue(idInfo.getTimestamp() >= before);
        assertTrue(idInfo.getTimestamp() <= after);
        assertTrue(idInfo.getRegion() == 0);
        assertTrue(idInfo.getReserved() == 0);
        assertTrue(idInfo.getNode() == 0);
        assertTrue(idInfo.getSequence() >= 0);
        assertTrue(idInfo.getSequence() < 1024);
    }

    @Test
    public void testGenerateWithRegion() {
        int region = 15;
        long before = System.currentTimeMillis();
        String id = idClient.generateID(region);
        long after = System.currentTimeMillis();

        IDInfo idInfo = idClient.decode(id);

        log("idInfo: " + idInfo, true);

        assertNotNull(idInfo);
        assertTrue(idInfo.getTimestamp() >= before);
        assertTrue(idInfo.getTimestamp() <= after);
        assertTrue(idInfo.getRegion() == region);
        assertTrue(idInfo.getReserved() == 0);
        assertTrue(idInfo.getNode() == 0);
        assertTrue(idInfo.getSequence() >= 0);
        assertTrue(idInfo.getSequence() < 1024);
    }

    @Test
    public void testBatchGenerate() {
        int len = 2000;
        String[] ids = idClient.batchGenerate(len);

        assertNotNull(ids);
        assertTrue(ids.length == len);

        String[] unSortedIds = new String[len];
        System.arraycopy(ids, 0, unSortedIds, 0, len);

        Arrays.sort(ids);

        for (int i = 0; i < unSortedIds.length; i++) {
            assertEquals(unSortedIds[i], ids[i]);
            log("id1: " + ids[i], true);
            log("id2: " + unSortedIds[i], true);
        }
    }

    @Test
    public void testBatchGenerateHex() {
        int len = 2000;
        String[] ids = idClient.batchGenerateHex(len);

        assertNotNull(ids);
        assertTrue(ids.length == len);

        String[] unSortedIds = new String[len];
        System.arraycopy(ids, 0, unSortedIds, 0, len);

        Arrays.sort(ids);

        for (int i = 0; i < unSortedIds.length; i++) {
            assertEquals(unSortedIds[i], ids[i]);
            log("id1: " + ids[i], true);
            log("id2: " + unSortedIds[i], true);
        }
    }
}
