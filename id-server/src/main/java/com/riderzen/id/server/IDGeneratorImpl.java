package com.riderzen.id.server;

import com.riderzen.id.Constants;
import com.riderzen.id.IDException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-15
 */
public class IDGeneratorImpl implements IDGenerator {
    private static final Logger log = LoggerFactory.getLogger(IDGeneratorImpl.class);
    private int node;
    private long lastTimestamp;
    private long sequence;

    public IDGeneratorImpl() {
        this(0);
    }

    public IDGeneratorImpl(int node) {
        this.node = node;
        lastTimestamp = System.currentTimeMillis();
        sequence = 0;
    }

    @Override
    public String generate() throws IDException {
        return generate(Constants.DEFAULT_REGION);
    }

    @Override
    public String generate(int region) throws IDException {
        return generateId(region, 10);
    }

    private String generateId(int region, int radix) throws IDException {
        Long current = System.currentTimeMillis();
        if (current != lastTimestamp) {
            lastTimestamp = current;
            sequence = 0;
        } else {
            sequence++;
            if (sequence >= 1024) {
                log.error("sequence is overflow: {}", sequence);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    log.warn("when sleeping for sequence", e);
                }
                return generate(region);
            }
        }

        String binaryId = generateBinary(current, region, 0, node, sequence);
        BigInteger id = new BigInteger(binaryId, 2);

        return id.toString(radix);
    }

    @Override
    public String generateBinary(Long current, int region, int reserved, int nodeId, long sequence) throws IDException {
        if (current < 0 || region < 0 || reserved < 0 || nodeId < 0 || sequence < 0) {
            throw new IDException(String.format("invalid parameters:{timestamp:%d, region:%d, reserved:%d, node:%d, sequence:%d}",
                    current, region, reserved, nodeId, sequence));
        }

        BitSet bitSet = new BitSet(96);
        String currentBits = Long.toBinaryString(current);
        if (currentBits.length() > 64) {
            throw new IDException("timestamp is overflow: " + current);
        }
        calculateBits(bitSet, currentBits, 64);

        String regionBits = Long.toBinaryString(region);
        if (regionBits.length() > 6) {
            throw new IDException("region is overflow: " + region);
        }
        calculateBits(bitSet, regionBits, 70);

        String reserveBits = Long.toBinaryString(reserved);
        if (reserveBits.length() > 4) {
            throw new IDException("reserved bit is overflow: " + regionBits);
        }
        calculateBits(bitSet, reserveBits, 74);

        String nodeBits = Long.toBinaryString(nodeId);
        if (nodeBits.length() > 12) {
            throw new IDException("node is overflow: " + nodeId);
        }
        calculateBits(bitSet, nodeBits, 86);

        String sequenceBits = Long.toBinaryString(sequence);
        if (sequenceBits.length() > 10) {
            throw new IDException("sequence is overflow: " + sequence);
        }
        calculateBits(bitSet, sequenceBits, 96);

        StringBuilder bitBuilder = new StringBuilder();
        for (int i = 0; i < 96; i++) {
            bitBuilder.append(bitSet.get(i) ? '1' : '0');
        }
        return bitBuilder.toString();
    }

    @Override
    public String generateHex() throws IDException {
        return generateHex(Constants.DEFAULT_REGION);
    }

    @Override
    public String generateHex(int region) throws IDException {
        String hex = generateId(region, 16);
        if (hex.length() < 24) {
            hex = StringUtils.leftPad(hex, 24, '0');
        }
        return hex;
    }

    private void calculateBits(BitSet bitSet, String bits, int start) {
        int index = start - bits.length();
        for (int i = 0, j = index; i < bits.length(); i++, j++) {
            if (bits.charAt(i) == '1') {
                bitSet.set(j);
            }
        }
    }


}
