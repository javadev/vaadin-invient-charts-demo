package com.invient.vaadin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class InvientChartsDemoApp extends Application implements
        HttpServletRequestListener {

    private Boolean isAppRunningOnGAE;
    private Window mainWindow;

    public boolean isAppRunningOnGAE() {
        if (isAppRunningOnGAE == null) {
            return false;
        }
        return isAppRunningOnGAE;
    }

    @Override
    public void init() {
        mainWindow = new InvientChartsDemoBar();
        setMainWindow(mainWindow);
        getMainWindow().showNotification(
                "To hide a series, click on its legend label.");
    }

    @Override
    public void onRequestStart(HttpServletRequest request,
            HttpServletResponse response) {
        if (isAppRunningOnGAE == null) {
            isAppRunningOnGAE = false;
            String serverInfo = request.getSession().getServletContext()
                    .getServerInfo();
            if (serverInfo != null && serverInfo.contains("Google")) {
                isAppRunningOnGAE = true;
            }
        }
    }

    @Override
    public void onRequestEnd(HttpServletRequest request,
            HttpServletResponse response) {

    }
}
