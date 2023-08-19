package org.supinfo.terminal;

import org.supinfo.virtualbox.VBoxManager;

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
                // Procédure d'installation
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

}
