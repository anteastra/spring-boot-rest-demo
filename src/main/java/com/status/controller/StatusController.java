package com.status.controller;

import com.status.domain.Status;
import com.status.utils.PerformanceMonitor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by anteastra on 20.10.2015.
 */
@Controller
public class StatusController {
    @RequestMapping(method = RequestMethod.GET, value = "/status")
    public
    @ResponseBody
    Status getStatus() {
        System.out.println("---in getStatus---");
        String cpu = PerformanceMonitor.getProcessCpuLoad().toString();
        String free = PerformanceMonitor.getFreePhysicalMemory().toString();
        String total = PerformanceMonitor.getTotalPhysicalMemory().toString();

        Status status = new Status("localhost:" + PerformanceMonitor.port, cpu, free, total);
        return status;
    }
}
