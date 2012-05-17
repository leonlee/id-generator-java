package com.riderzen.id.api;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-15
 */
public class IDClientFactory {
    private static class ClientHolder {
        public static IDClient instance = new IDClientImpl();
    }

    public static IDClient getIdClient() {
        return ClientHolder.instance;
    }
}
