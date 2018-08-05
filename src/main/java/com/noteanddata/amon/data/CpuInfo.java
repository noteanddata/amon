package com.noteanddata.amon.data;

public class CpuInfo {
    private final long user;
    private final long system;
    private final long idle;
    private final long iowait;
    private final long irq;
    private final long softirq;
    private final long total;

    CpuInfo(long user, long system, long idle, long iowait, long irq, long softirq, long total) {
        this.user = user;
        this.system = system;
        this.idle = idle;
        this.iowait = iowait;
        this.irq = irq;
        this.softirq = softirq;
        this.total = total;
    }

    public long getUser() {
        return user;
    }

    public long getSystem() {
        return system;
    }

    public long getIdle() {
        return idle;
    }

    public long getIowait() {
        return iowait;
    }

    public long getIrq() {
        return irq;
    }

    public long getSoftirq() {
        return softirq;
    }

    public long getTotal() {
        return total;
    }




    @Override
    public String toString() {
        return "CpuInfo{" +
                "user=" + user +
                ", system=" + system +
                ", idle=" + idle +
                ", iowait=" + iowait +
                ", irq=" + irq +
                ", softirq=" + softirq +
                ", total=" + total +
                '}';
    }
}
