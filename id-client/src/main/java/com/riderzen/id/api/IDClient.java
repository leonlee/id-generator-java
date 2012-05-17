package com.riderzen.id.api;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-15
 */
public interface IDClient {
    String generateID();

    String generateID(int region);

    IDInfo decode(String id);

    String[] batchGenerate(int count);

    String[] batchGenerate(int region, int count);

    String generateHexID();

    String generateHexID(int region);

    IDInfo decodeHex(String hexId);

    String[] batchGenerateHex(int count);

    String[] batchGenerateHex(int region, int count);

}
