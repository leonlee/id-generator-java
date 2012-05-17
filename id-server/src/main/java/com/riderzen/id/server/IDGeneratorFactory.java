package com.riderzen.id.server;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-15
 */
public class IDGeneratorFactory {
    private static class GeneratorHolder {
        public static IDGenerator instance = new IDGeneratorImpl();
    }
    public static IDGenerator getIdGenerator() {
        return GeneratorHolder.instance;
    }
}
