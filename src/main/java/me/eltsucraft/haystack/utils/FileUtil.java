package me.eltsucraft.haystack.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtil {
    public static boolean deleteDirectory(File folder) {
        if (!folder.exists()) {
            return true;
        }

        File[] children = folder.listFiles();

        if (children == null) {
            return false;
        }

        boolean success = true;

        for (File child : children) {
            if (child.isDirectory()) {
                boolean ok = deleteDirectory(child);

                if (!ok) {
                    success = false;
                }
            } else {
                if (!child.delete()) {
                    success = false;
                }
            }
        }

        if (!folder.delete()) {
            success = false;
        }

        return success;
    }

    public static boolean copyDirectory(File folder, File target) {
        if (!target.exists() && !target.mkdirs()) {
            return false;
        }

        File[] children = folder.listFiles();

        if (children == null) {
            return false;
        }

        boolean success = true;

        for (File child : children) {
            File destination = new File(target, child.getName());
            if (child.isDirectory()) {
                boolean ok = copyDirectory(child, destination);
                if (!ok) {
                    success = false;
                }
            } else {
                try {
                    Files.copy(child.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    success = false;
                }
            }
        }

        return success;
    }
}
