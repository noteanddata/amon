package com.noteanddata.amon.format;

import com.noteanddata.amon.data.CpuInfo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CpuInfoFormater {
    public static final String CPU_HEADER = "usr  sys  idle iow  irq  softirq";

    private NumberFormat numberFormat;

    public CpuInfoFormater() {
        init();
    }

    private void init() {
        numberFormat = new DecimalFormat("#00.0");
    }


    public String formatCpuInfo(CpuInfo cpuInfo) {
        StringBuilder sb = new StringBuilder();

        sb.append(format(cpuInfo.getUser(), cpuInfo.getTotal(), 4)).append(" ");
        sb.append(format(cpuInfo.getSystem(), cpuInfo.getTotal(), 4)).append(" ");
        sb.append(format(cpuInfo.getIdle(), cpuInfo.getTotal(), 4)).append(" ");
        sb.append(format(cpuInfo.getIowait(), cpuInfo.getTotal(), 4)).append(" ");
        sb.append(format(cpuInfo.getIrq(), cpuInfo.getTotal(), 4)).append(" ");
        sb.append(format(cpuInfo.getSoftirq(), cpuInfo.getTotal(), 4)).append(" ");


        return sb.toString();
    }

    public String format(long value, long total, int width) {
        return rightPadding(numberFormat.format((100 * (double)value) / total), width);
    }
    public String rightPadding(String str, int num) {
        return String.format("%1$-" + num + "s", str);
    }
}
