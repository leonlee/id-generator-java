package com.riderzen.id.api;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-16
 */
public class IDInfo {
    private long timestamp;
    private int region;
    private int reserved;
    private int node;
    private int sequence;

    public IDInfo() {
    }

    public IDInfo(long timestamp, int region, int reserved, int node, int sequence) {
        this.timestamp = timestamp;
        this.region = region;
        this.reserved = reserved;
        this.node = node;
        this.sequence = sequence;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IDInfo idInfo = (IDInfo) o;

        if (node != idInfo.node) return false;
        if (region != idInfo.region) return false;
        if (reserved != idInfo.reserved) return false;
        if (sequence != idInfo.sequence) return false;
        if (timestamp != idInfo.timestamp) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + region;
        result = 31 * result + reserved;
        result = 31 * result + node;
        result = 31 * result + sequence;
        return result;
    }

    @Override
    public String toString() {
        return "IDInfo{" +
                "timestamp=" + timestamp +
                ", region=" + region +
                ", reserved=" + reserved +
                ", node=" + node +
                ", sequence=" + sequence +
                '}';
    }
}
