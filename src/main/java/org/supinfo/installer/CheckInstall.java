package org.supinfo.installer;

import org.supinfo.utils.Constants;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckInstall {

    public static boolean isInstalled() {
        Path configPath = Paths.get(Constants.configPath);
        return Files.exists(configPath);
    }

}
