package org.supinfo.process;

import org.supinfo.utils.Constants;

import javax.swing.*;
import java.io.IOException;

public class Launch extends SwingWorker<Boolean, Void> {

    private EventTaskListener listener;

    @Override
    protected Boolean doInBackground() throws Exception {
        listener.taskStatus(TaskStatus.LOADING);
        return startVm();
    }

    protected boolean startVm() {
        String path = Constants.vboxManagePath + " startvm --type headless \"Windows 10 Pro Sample\"";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            listener.taskStatus(TaskStatus.RUNNING);
            return connectMonitor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean connectMonitor() {
        String path = "mstsc /v:localhost:5000";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setTaskCompleteListener(EventTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void done() {
        listener.taskStatus(TaskStatus.DONE);
    }
}
