package init;

import javax.management.Notification;
import javax.swing.*;
import java.awt.*;

public class PreChecks {

    public static boolean checkBeforeInit() {

        String javaVersion = System.getProperty("java.version");
        String osType = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String cpuType = System.getProperty("os.arch");
        String ramAmount = String.valueOf(Runtime.getRuntime().maxMemory());

        System.out.println("Java version: " + javaVersion);
        System.out.println("OS type: " + osType);
        System.out.println("OS version: " + osVersion);
        System.out.println("CPU type: " + cpuType);

        //possibly the worst way of doing this

        return true;

    }

}
