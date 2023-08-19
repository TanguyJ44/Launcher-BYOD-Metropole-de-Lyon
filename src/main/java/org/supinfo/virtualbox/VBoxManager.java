package org.supinfo.virtualbox;

import org.supinfo.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VBoxManager {

    public static boolean healthCheck(String... vboxManagePath) {
        String path = vboxManagePath.length < 1 ? Constants.vboxManagePath : vboxManagePath[0];
        try {
            Process process = Runtime.getRuntime().exec(path + " --version");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("VirtualBox version " + output.toString());
                return true;
            } else {
                System.out.println("Aucune installation VirtualBox trouvée !");
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
