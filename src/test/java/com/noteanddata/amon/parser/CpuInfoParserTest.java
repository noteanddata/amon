package com.noteanddata.amon.parser;

import com.noteanddata.amon.data.CpuInfo;
import com.noteanddata.amon.format.CpuInfoFormater;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CpuInfoParserTest {

    @Test
    public void testParseCpuInfo() throws Exception {

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("proc-stat.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> lines = new ArrayList<>();
        String line;
        while(null != (line = reader.readLine())) {
            lines.add(line);
        }
        inputStream.close();



        CpuInfoParser cpuInfoParser = new CpuInfoParser();
        Optional<CpuInfo> cpuInfo = cpuInfoParser.parseCpuInfo(lines);
        System.out.println(cpuInfo.isPresent());
        if(cpuInfo.isPresent()) {
            System.out.println(cpuInfo.get());

            CpuInfoFormater formater = new CpuInfoFormater();
            System.out.println(formater.formatCpuInfo(cpuInfo.get()));
        }

    }
}
