package com.myTempCleaner;

import com.myTempCleaner.GUI.MainView;
import javax.swing.JOptionPane;

/**
 *
 * @author A7madY911
 */

public class MainWindow {

    public static void main(String[] args) {
        
        //Check if Windows XP, Vista or 7
        if (System.getProperty("os.version").equalsIgnoreCase("6") || System.getProperty("os.version").equalsIgnoreCase("6.1") || System.getProperty("os.version").equalsIgnoreCase("5") || System.getProperty("os.version").equalsIgnoreCase("5.1")) {
            MainView mView = new MainView();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry! Your OS is not supported.");
        }

    }
}
