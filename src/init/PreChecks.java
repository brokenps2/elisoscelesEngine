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
        if(javaVersion.startsWith("11") || javaVersion.startsWith("12") || javaVersion.startsWith("13") || javaVersion.startsWith("14") || javaVersion.startsWith("15") || javaVersion.startsWith("16") || javaVersion.startsWith("17") || javaVersion.startsWith("18")) {
            System.out.println("");
            System.out.println("Error: Java version is not supported");
            System.out.println("Java 11 and later do not support the Nashorn API, which is crucial for the scripting engine to work");

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(320, 240);
            frame.setTitle("Error");
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);

            JLabel label = new JLabel("Error: Java version is not supported");
            label.setFont(new Font("SansSerif", Font.PLAIN, 16));
            label.setBounds(0, 0, 320, 240);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.TOP);
            frame.add(label);
            JLabel label3 = new JLabel("being removed in later versions");
            label3.setFont(new Font("Serif", Font.PLAIN, 12));
            label3.setBounds(0, 0, 320, 240);
            label3.setHorizontalAlignment(JLabel.CENTER);
            label3.setVerticalAlignment(JLabel.CENTER);
            frame.add(label3);
            JLabel label2 = new JLabel("Game engine requires Java 11 or OLDER due to Nashorn");
            label2.setFont(new Font("Serif", Font.PLAIN, 12));
            label2.setBounds(0, 0, 320, 240);
            label2.setHorizontalAlignment(JLabel.CENTER);
            label2.setVerticalAlignment(JLabel.CENTER);
            frame.add(label2);


            return false;

        }

        return true;

    }

}
