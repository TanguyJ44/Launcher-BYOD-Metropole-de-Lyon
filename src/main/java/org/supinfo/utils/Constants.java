package org.supinfo.utils;

public class Constants {

    public static String osName;
    public static String osArch;
    public static String installPath;
    public static String configPath;
    public static String vboxManagePath;

    public static void onInit() {
        osName = System.getProperty("os.name");
        osArch = System.getProperty("os.arch");
        installPath = System.getenv("APPDATA") + "\\lyon-byod";
        configPath = installPath + "\\config.json";
        vboxManagePath = installPath + "\\VirtualBox\\VBoxManage.exe";
    }
}
