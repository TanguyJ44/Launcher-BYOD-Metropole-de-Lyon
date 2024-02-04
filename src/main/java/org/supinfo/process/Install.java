package org.supinfo.process;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.supinfo.utils.Constants;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Install extends SwingWorker<Boolean, Void> {

    private EventTaskListener listener;

    @Override
    protected Boolean doInBackground() throws Exception {
        listener.taskStatus(TaskStatus.LOADING, "Lancement de la procédure d'installation ...\n\n");
        return createProgramDirectory();
    }

    protected boolean createProgramDirectory() {
        listener.taskStatus(TaskStatus.RUNNING, "Création du dossier de l'application ...\n");
        try {
            Path programFolder = Paths.get(Constants.installPath);
            if (Files.exists(programFolder)) {
                listener.taskStatus(TaskStatus.FAILED, "Une version corrompue de l'application a été détectée !\n");
                return false;
            }
            Files.createDirectory(programFolder);
            listener.taskStatus(TaskStatus.RUNNING, "Dossier de l'application créé !\n");
            return downloadInstallationPack();
        } catch (IOException e) {
            listener.taskStatus(TaskStatus.FAILED, "Erreur lors de la création du dossier de l'application !\n");
            return false;
        }
    }

    protected boolean downloadInstallationPack() {
        listener.taskStatus(TaskStatus.RUNNING, "Téléchargement des ressources en ligne ...\n");

        OutputStream ops = null;
        InputStream ins = null;
        try {
            URL url = new URL(Constants.installPack);
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            ins = connection.getInputStream();
            ops = new FileOutputStream(Constants.installPath + "\\files.zip");
            final byte[] bt = new byte[1024];
            int len;
            while ((len = ins.read(bt)) != -1) {
                ops.write(bt, 0, len);
            }
        } catch (IOException ex) {
            listener.taskStatus(TaskStatus.FAILED, "Erreur lors du téléchargement des ressources !\n");
            return false;
        } finally {
            try {
                if (ops != null) ops.close();
                if (ins != null) ins.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            listener.taskStatus(TaskStatus.RUNNING, "Téléchargement des ressources terminé !\n");
            return extractInstallationPack();
        }
    }

    protected boolean extractInstallationPack() {
        listener.taskStatus(TaskStatus.RUNNING, "Extraction des ressources ...\n");

        String source = Constants.installPath + "\\files.zip";
        String destination = Constants.installPath + "\\files";

        try {
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(destination);

            listener.taskStatus(TaskStatus.RUNNING, "Extraction des ressources terminées !\n");
            return installVBox();
        } catch (ZipException e) {
            listener.taskStatus(TaskStatus.FAILED, "Erreur lors de l'extraction des ressources !\n");
            return false;
        }
    }

    protected boolean installVBox() {
        listener.taskStatus(TaskStatus.RUNNING, "Installation de l'hyperviseur ...\n/!\\ Vous devez autoriser l'installation dans la fenêtre qui va s'ouvrir !\n");

        String path = Constants.installPath + "\\files\\virtualbox-win-install.exe --msiparams INSTALLDIR=" + Constants.installPath + "\\VirtualBox --msiparams VBOX_INSTALLDESKTOPSHORTCUT=0 --msiparams VBOX_INSTALLQUICKLAUNCHSHORTCUT=0 --msiparams VBOX_REGISTERFILEEXTENSIONS=0 --silent --ignore-reboot";

        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + path);
            process.waitFor();

            listener.taskStatus(TaskStatus.RUNNING, "Installation de l'hyperviseur terminée !\n");
            return customVmDataPath();
        } catch (IOException | InterruptedException e) {
                listener.taskStatus(TaskStatus.FAILED, "Erreur lors de l'installation de l'hyperviseur !\n");
            return false;
        }
    }

    protected boolean customVmDataPath() {
        listener.taskStatus(TaskStatus.RUNNING, "Configuration de l'emplacement de stockage de votre environnement virtuel ...\n");

        String path = Constants.vboxManagePath + " setproperty machinefolder " + Constants.installPath + "\\VMData";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            listener.taskStatus(TaskStatus.RUNNING, "Configuration de l'emplacement de stockage effectuée !\n");
            return installVBoxExtensionPack();
        } catch (IOException | InterruptedException e) {
            listener.taskStatus(TaskStatus.FAILED, "Erreur lors de la configuration de l'emplacement de stockage de votre environnement virtuel !\n");
            return false;
        }
    }

    protected boolean installVBoxExtensionPack() {
        listener.taskStatus(TaskStatus.RUNNING, "Installation des éléments complémentaires ...\n/!\\ Vous devez autoriser l'installation dans la fenêtre qui va s'ouvrir !\n");

        String path = Constants.vboxManagePath + " extpack install --replace \"" + Constants.installPath + "\\files\\Oracle_VM_VirtualBox_Extension_Pack-7.0.10.vbox-extpack\" --accept-license=33d7284dc4a0ece381196fda3cfe2ed0e1e8e7ed7f27b9a9ebc4ee22e24bd23c";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            listener.taskStatus(TaskStatus.RUNNING, "Installation des éléments complémentaires terminée !\n");
            return disableUpdate();
        } catch (IOException | InterruptedException e) {
            listener.taskStatus(TaskStatus.FAILED, "Erreur lors de l'installation des éléments complémentaires !\n");
            return false;
        }
    }

    protected boolean disableUpdate() {
        listener.taskStatus(TaskStatus.RUNNING, "Désactivation des fonctionnalités non requises ...\n");

        String path = Constants.vboxManagePath + " setextradata global GUI/UpdateDate never";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            listener.taskStatus(TaskStatus.RUNNING, "Désactivation des fonctionnalités non requises terminée !\n");
            return importVmSample();
        } catch (IOException | InterruptedException e) {
            listener.taskStatus(TaskStatus.FAILED, "Erreur lors de la désactivation des fonctionnalités non requises !\n");
            return false;
        }
    }

    protected  boolean importVmSample() {
        listener.taskStatus(TaskStatus.RUNNING, "Importation de votre environnement virtuel ...\n");

        String path = Constants.vboxManagePath + " import \"" + Constants.installPath + "\\files\\Windows 10 Pro Sample.ova\"";

        try {
            Process process = Runtime.getRuntime().exec(path);
            process.waitFor();

            listener.taskStatus(TaskStatus.RUNNING, "Importation de l'environnement virtuel terminée !\n");
            return createConfigFile();
        } catch (IOException | InterruptedException e) {
            listener.taskStatus(TaskStatus.FAILED, "Erreur lors de l'importation de l'environnement virtuel !\n");
            return false;
        }
    }

    protected boolean createConfigFile() {
        listener.taskStatus(TaskStatus.RUNNING, "Création des fichiers de configuration ...\n");
        try {
            File file = new File(Constants.configPath);
            file.createNewFile();

            listener.taskStatus(TaskStatus.RUNNING, "Création des fichiers de configuration terminée !\n");
            return true;
        } catch (IOException e) {
            listener.taskStatus(TaskStatus.FAILED, "Erreur lors de la création des fichiers de configuration !\n");
            return false;
        }
    }

    public void setTaskCompleteListener(EventTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void done() {
        listener.taskStatus(TaskStatus.DONE, "Installation terminée !\n");
    }
}
