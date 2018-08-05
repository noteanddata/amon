package com.noteanddata.amon;

import com.noteanddata.amon.format.CpuInfoFormater;
import com.noteanddata.amon.parser.CpuInfoParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AMonRunner {
    public static final String TIME_WIDTH_FORMAT = "%1$-9s";

    private CpuInfoParser cpuInfoParser;
    private DateFormat dateFormat;

    public AMonRunner() {
        cpuInfoParser = new CpuInfoParser();
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public void start() {
        new Thread(()->{
            System.out.println(String.format(TIME_WIDTH_FORMAT, "time ") + CpuInfoFormater.CPU_HEADER);
            while(true) {
                oneRun();

                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }).start();
    }

    private void oneRun() {
        String time = getTime();
        String cpuInfo = cpuInfoParser.getStatCpu();
        System.out.println(time + cpuInfo);
    }

    private String getTime() {
        Date now = new Date();
        return String.format(TIME_WIDTH_FORMAT, dateFormat.format(now));
    }
}
