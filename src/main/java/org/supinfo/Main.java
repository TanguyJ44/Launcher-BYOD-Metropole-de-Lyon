package org.supinfo;

import org.supinfo.installer.CheckInstall;
import org.supinfo.terminal.Install;
import org.supinfo.terminal.Launch;
import org.supinfo.utils.Constants;
import org.supinfo.virtualbox.VBoxManager;

public class Main {

    public static void main(String[] args) {
        System.out.println("Launcher BYOD - Métropole de Lyon");

        Constants.onInit();

        System.out.println("\nSystème " + Constants.osName + " (" + Constants.osArch + ")");

        if (CheckInstall.isInstalled()) {
            if (VBoxManager.healthCheck()) {
                Launch.onStart();
            } else {
                System.out.println("Votre installation de VirtualBox semble corrompu !");
                System.exit(0);
            }
        } else {
            Install.onStart();
        }
    }
}