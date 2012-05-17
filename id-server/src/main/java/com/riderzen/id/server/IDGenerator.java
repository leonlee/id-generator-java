package com.riderzen.id.server;

import com.riderzen.id.IDException;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-10
 */
public interface IDGenerator {
    String generate() throws IDException;

    String generate(int region) throws IDException;

    String generateBinary(Long current, int region, int reserved, int nodeId, long sequence) throws IDException;

    String generateHex() throws IDException;

    String generateHex(int region) throws IDException;
}
