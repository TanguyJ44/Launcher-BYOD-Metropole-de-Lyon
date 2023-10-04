package org.supinfo.terminal;

import org.supinfo.utils.Constants;
import org.supinfo.virtualbox.VBoxManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Install {

    public static Scanner scanner = new Scanner(System.in);

    public static void onStart() {
        System.out.println("\n# Bienvenue dans l'assistant d'installation");
        System.out.println("> Souhaitez-vous utiliser une installation Virtualbox existante ? (o/N)");

        String useCustomVbox = scanner.nextLine();

        switch (useCustomVbox.toLowerCase()) {
            case "o":
                getCustomVboxPath();
                break;
            case "n":
                installVBox();
                break;
            default:
                System.out.println("Saisie invalide !");
                onStart();
                break;
        }
    }

    public static void getCustomVboxPath() {
        System.out.println("\n> Veuillez indiquer le chemin vers votre VBoxManage.exe");
        System.out.println("  -> Par exemple : C:\\Program Files\\Oracle\\VirtualBox\\VBoxManage.exe");

        String customVboxPath = scanner.nextLine();

        if (VBoxManager.healthCheck(customVboxPath)) {
            // Procédure d'installation
        } else {
            System.out.println("Impossible d'utiliser le Virtualbox indiqué !");
            System.exit(0);
        }
    }

    public static void installVBox() {
        String path = Constants.installPath + "\\files\\virtualbox-win-install.exe --msiparams INSTALLDIR=" + Constants.installPath + "\\VirtualBox --msiparams VBOX_INSTALLDESKTOPSHORTCUT=0 --msiparams VBOX_INSTALLQUICKLAUNCHSHORTCUT=0 --msiparams VBOX_REGISTERFILEEXTENSIONS=0 --silent --ignore-reboot";

        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + path);
            process.waitFor();
            System.out.println("Installation de VirtualBox terminée !");
            customVmDataPath();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void customVmDataPath() {
        String path = Constants.vboxManagePath + " setproperty machinefolder " + Constants.installPath + "\\VMData";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();
            System.out.println("Chemin de stockage des VM personnalisé !");
            installVBoxExtensionPack();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void installVBoxExtensionPack() {
        String path = Constants.vboxManagePath + " extpack install --replace \"" + Constants.installPath + "\\files\\Oracle_VM_VirtualBox_Extension_Pack-7.0.10.vbox-extpack\" --accept-license=33d7284dc4a0ece381196fda3cfe2ed0e1e8e7ed7f27b9a9ebc4ee22e24bd23c";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();
            System.out.println("Installation de l'extension pack terminée !");
            disableUpdate();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disableUpdate() {
        String path = Constants.vboxManagePath + " setextradata global GUI/UpdateDate never";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            System.out.println("Mise à jour automatique désactivée !");
            importVmSample();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void importVmSample() {
        String path = Constants.vboxManagePath + " import \"" + Constants.installPath + "\\files\\Windows 10 Pro Sample.ova\"";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            System.out.println("Importation de la VM terminée !");
            System.out.println("\nFin de l'installation !");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
