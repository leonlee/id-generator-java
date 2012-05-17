package com.riderzen.id.api;

import com.riderzen.id.IDException;
import com.riderzen.id.server.IDGenerator;
import com.riderzen.id.server.IDGeneratorFactory;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-15
 */
public class IDClientImpl implements IDClient {
    private static final Logger log = LoggerFactory.getLogger(IDClientImpl.class);
    private final IDGenerator generator;

    public IDClientImpl() {
        generator = IDGeneratorFactory.getIdGenerator();
    }

    @Override
    public String generateID() {
        String id = null;
        try {
            id = generator.generate();
        } catch (IDException e) {
            log.error("caught id exception", e);
        }
        return id;
    }

    @Override
    public String generateID(int region) {
        String id = null;
        try {
            id = generator.generate(region);
        } catch (IDException e) {
            log.error("caught id exception", e);
        }
        return id;
    }

    @Override
    public IDInfo decode(String id) {
        return decodeId(id, 10);
    }

    private IDInfo decodeId(String id, int radix) {
        if (id == null) return null;

        BigInteger idInteger = new BigInteger(id, radix);
        String idBits = idInteger.toString(2);
        if (idBits.length() < 96) {
            idBits = StringUtils.leftPad(idBits, 96, '0');
        }

        long timestamp = new BigInteger(idBits.substring(0, 64), 2).longValue();
        int region = new BigInteger(idBits.substring(64, 70), 2).intValue();
        int reserved = new BigInteger(idBits.substring(70, 74), 2).intValue();
        int node = new BigInteger(idBits.substring(74, 86), 2).intValue();
        int sequence = new BigInteger(idBits.substring(86, 96), 2).intValue();

        return new IDInfo(timestamp, region, reserved, node, sequence);
    }

    @Override
    public String[] batchGenerate(int count) {
        String[] ids = new String[count];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = generateID();
        }
        return ids;
    }

    @Override
    public String[] batchGenerate(int region, int count) {
        String[] ids = new String[count];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = generateID(region);
        }
        return ids;
    }

    @Override
    public String generateHexID() {
        String hex = null;
        try {
            hex = generator.generateHex();
        } catch (IDException e) {
            log.error("caught id exception", e);
        }
        return hex;
    }

    @Override
    public String generateHexID(int region) {
        String hex = null;
        try {
            hex = generator.generateHex(region);
        } catch (IDException e) {
            log.error("caught id exception", e);
        }
        return hex;

    }

    @Override
    public IDInfo decodeHex(String hexId) {
        return decodeId(hexId, 16);
    }

    @Override
    public String[] batchGenerateHex(int count) {
        String[] ids = new String[count];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = generateHexID();
        }
        return ids;
    }

    @Override
    public String[] batchGenerateHex(int region, int count) {
        String[] ids = new String[count];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = generateHexID(region);
        }
        return ids;
    }
}
