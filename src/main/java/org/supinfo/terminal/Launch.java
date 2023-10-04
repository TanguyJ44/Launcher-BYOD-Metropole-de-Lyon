package org.supinfo.terminal;

import org.supinfo.utils.Constants;

import java.io.IOException;
import java.util.Scanner;

public class Launch {

    public static Scanner scanner = new Scanner(System.in);

    public static void onStart() {
        System.out.println("> Lancer mon environnement professionnel (O/n)");

        String useCustomVbox = scanner.nextLine();

        switch (useCustomVbox.toLowerCase()) {
            case "o":
                System.out.println("\n Démarrage de l'environnement professionnel, un instant...");
                startVm();
                break;
            case "n":
                System.out.println("\n A bienôt :)");
                System.exit(0);
                break;
            default:
                System.out.println("Saisie invalide !");
                onStart();
                break;
        }
    }

    public static void startVm() {
        String path = Constants.vboxManagePath + " startvm --type headless \"Windows 10 Pro Sample\"";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            System.out.println("VM démarrée !");
            connectMonitor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void connectMonitor() {
        String path = "mstsc /v:localhost:5000";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            System.out.println("\n A bienôt :)");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
