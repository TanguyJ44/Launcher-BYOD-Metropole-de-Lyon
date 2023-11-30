package org.supinfo;

import com.formdev.flatlaf.FlatIntelliJLaf;
import org.supinfo.gui.Frame;
import org.supinfo.installer.CheckInstall;
import org.supinfo.utils.Constants;
import org.supinfo.virtualbox.VBoxManager;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Launcher BYOD - Métropole de Lyon");

        Constants.onInit();

        System.out.println("\nSystème " + Constants.osName + " (" + Constants.osArch + ")");

        boolean isInstalled = false;

        if (CheckInstall.isInstalled()) {
            if (VBoxManager.healthCheck()) {
                isInstalled = true;
            } else {
                System.out.println("Votre installation de VirtualBox semble corrompu !");
                System.exit(0);
            }
        }

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean finalIsInstalled = isInstalled;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame(finalIsInstalled).setVisible(true);
            }
        });
    }
}