package com.nikirocks;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class Menu implements Serializable {

    private boolean home;
    private boolean secured;
    private boolean monitoring;

    public void setHome(boolean home) {
        this.home = home;
        this.secured = !home;
        this.monitoring = !home;
    }

    public void setSecured(boolean secured) {
        this.home = !secured;
        this.secured = secured;
        this.monitoring = !secured;
    }
    public void setMonitoring(boolean monitoring) {
        this.home = !monitoring;
        this.secured = !monitoring;
        this.monitoring = monitoring;
    }
    public boolean isHome() {
        return home;
    }

    public boolean isSecured() {
        return secured;
    }

    public boolean isMonitoring() {
        return monitoring;
    }
}
