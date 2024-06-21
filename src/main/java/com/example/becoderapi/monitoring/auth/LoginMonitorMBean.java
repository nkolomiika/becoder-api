package com.example.becoderapi.monitoring.auth;

public interface LoginMonitorMBean {
    int getSuccessLoginCount();
    void setSuccessLoginCount(int successLoginCount);
    void incrementSuccessLoginCount();

    int getLoginCount();
    void setLoginCount(int loginCount);
    void incrementLoginCount();
}
