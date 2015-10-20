package com.status.utils;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Value;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by anteastra on 19.10.2015.
 */
public class PerformanceMonitor {

    public static int port;

    public static Double getProcessCpuLoad() {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Double value = osBean.getSystemCpuLoad();

        if (value == -1.0) return Double.NaN;  // usually takes a couple of seconds before we get real values

        return ((int) (value * 1000) / 10.0);        // returns a percentage value with 1 decimal point precision
    }

    public static Double getTotalPhysicalMemory() {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Double value = (osBean.getTotalPhysicalMemorySize() * 10 / 1024 / 1024 / 1024) / 10.0;

        return value;
    }

    public static Double getFreePhysicalMemory() {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Double value = (osBean.getFreePhysicalMemorySize() * 10 / 1024 / 1024 / 1024) / 10.0;

        return value;
    }
}