package com.noteanddata.amon.data;

public class CpuInfoBuilder {
    private long user;
    private long system;
    private long idle;
    private long iowait;
    private long irq;
    private long softirq;
    private long total;

    public CpuInfoBuilder setUser(long user) {
        this.user = user;
        return this;
    }

    public CpuInfoBuilder setSystem(long system) {
        this.system = system;
        return this;
    }

    public CpuInfoBuilder setIdle(long idle) {
        this.idle = idle;
        return this;
    }

    public CpuInfoBuilder setIowait(long iowait) {
        this.iowait = iowait;
        return this;
    }

    public CpuInfoBuilder setIrq(long irq) {
        this.irq = irq;
        return this;
    }

    public CpuInfoBuilder setSoftirq(long softirq) {
        this.softirq = softirq;
        return this;
    }

    public CpuInfoBuilder setTotal(long total) {
        this.total = total;
        return this;
    }

    public CpuInfo build() {
        return new CpuInfo(user, system, idle, iowait, irq, softirq, total);
    }
}