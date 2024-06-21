package com.example.becoderapi.monitoring.auth;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "com.example.becoderapi.monitoring.auth:name=LoginMonitor")
public class LoginMonitor implements LoginMonitorMBean {

    private int successLoginCount;
    private int loginCount;

    @Override
    @ManagedAttribute
    public int getSuccessLoginCount() {
        return successLoginCount;
    }

    @Override
    @ManagedAttribute
    public void setSuccessLoginCount(int successLoginCount) {
        this.successLoginCount = successLoginCount;
    }

    @Override
    @ManagedOperation
    public void incrementSuccessLoginCount() {
        successLoginCount++;
    }

    @Override
    @ManagedAttribute
    public int getLoginCount() {
        return loginCount;
    }

    @Override
    @ManagedAttribute
    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    @ManagedOperation
    public void incrementLoginCount() {
        loginCount++;
    }
}
