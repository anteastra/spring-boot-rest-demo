package com.status.controller;

import com.status.annotations.CurrentlyLoggedUser;
import com.status.domain.DomainUser;
import com.status.domain.Status;
import com.status.utils.PerformanceMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anteastra on 20.10.2015.
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_DOMAIN_USER')")
public class StatusController {

    @RequestMapping(method = RequestMethod.GET, value = "/status")
    public Status getStatus(@CurrentlyLoggedUser DomainUser domainUser) {
        //System.out.println("---in getStatus---");
        String cpu = PerformanceMonitor.getProcessCpuLoad().toString();
        String free = PerformanceMonitor.getFreePhysicalMemory().toString();
        String total = PerformanceMonitor.getTotalPhysicalMemory().toString();

        Status status = new Status("localhost:" + PerformanceMonitor.port, cpu, free, total);
        return status;
    }
}
