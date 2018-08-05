package com.noteanddata.amon.parser;

import com.noteanddata.amon.data.CpuInfo;
import com.noteanddata.amon.data.CpuInfoBuilder;
import com.noteanddata.amon.format.CpuInfoFormater;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class CpuInfoParser {
    public static final String CPU_STAT_FILE = "/proc/stat";
    public static final String STAT_CPU = "cpu";

    private CpuInfoFormater cpuInfoFormater;

    public CpuInfoParser() {
        cpuInfoFormater = new CpuInfoFormater();
    }

    public String getStatCpu() {
        try {
            List<String> lines = FileUtils.readLines(new File(CPU_STAT_FILE), StandardCharsets.UTF_8);
            Optional<CpuInfo> cpuInfo = parseCpuInfo(lines);
            if(cpuInfo.isPresent()) {
                return cpuInfoFormater.formatCpuInfo(cpuInfo.get());
            }
        } catch (IOException e) {
            // ignore
        }
        return null;
    }



    public Optional<CpuInfo> parseCpuInfo(List<String> statLines) {
        try {
            if (null == statLines || statLines.size() == 0) {
                return Optional.empty();
            }
            String cpuLine = statLines.get(0).trim();
            if (!cpuLine.startsWith(STAT_CPU)) {
                return Optional.empty();
            }

            String numberStr = cpuLine.substring(STAT_CPU.length());
            String[] cpuNumbers = StringUtils.split(numberStr);
            long total = 0;

            long user = Long.valueOf(cpuNumbers[0]);
            total += user;

            total += Long.valueOf(cpuNumbers[1]); // nice

            long system = Long.valueOf(cpuNumbers[2]);
            total += system;

            long idle = Long.valueOf(cpuNumbers[3]);
            total += idle;

            long iowait = Long.valueOf(cpuNumbers[4]);
            total += iowait;

            long irq = Long.valueOf(cpuNumbers[5]);
            total += irq;

            long softirq = Long.valueOf(cpuNumbers[6]);
            total += softirq;

            for(int i = 7; i < cpuNumbers.length; ++i) {
                total += Long.valueOf(cpuNumbers[i]);
            }

            CpuInfo cpuInfo = new CpuInfoBuilder()
                    .setUser(user)
                    .setSystem(system)
                    .setIdle(idle)
                    .setIowait(iowait)
                    .setIrq(irq)
                    .setSoftirq(softirq)
                    .setTotal(total)
                    .build();
            return Optional.of(cpuInfo);
        }
        catch (Exception e) {
            // ignore
            return Optional.empty();
        }

    }
}
