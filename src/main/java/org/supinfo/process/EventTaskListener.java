package org.supinfo.process;

import java.util.EventListener;

public abstract class EventTaskListener implements EventListener {
    public void taskStatus(TaskStatus status) {}

    public void taskStatus(TaskStatus status, String message) {}
}
