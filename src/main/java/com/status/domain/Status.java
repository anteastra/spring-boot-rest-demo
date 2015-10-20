package com.status.domain;

/**
 * Created by anteastra on 20.10.2015.
 */
public class Status {

    private String ip;
    private String cpuUsage;
    private String freeMemory;
    private String totalMemory;

    public Status(String ip, String cpuUsage, String freeMemory, String totalMemory) {
        this.ip = ip;
        this.cpuUsage = cpuUsage;
        this.freeMemory = freeMemory;
        this.totalMemory = totalMemory;
    }

    public String getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(String freeMemory) {
        this.freeMemory = freeMemory;
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage;
    }
}
