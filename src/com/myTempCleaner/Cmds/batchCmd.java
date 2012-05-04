/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myTempCleaner.Cmds;

/**
 *
 * @author A7madY911
 */
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class batchCmd {

    File tmpf = new File("batch.bat");
    File userTempFolderPath = new File(System.getProperty("java.io.tmpdir"));
    File winTempFolderPath = new File("C:\\Users\\");
    File runDllFile = new File("C:\\Windows\\System32\\RunDll32.exe");
    File CFolderPath = new File("C:\\");

    //Windows Temp Files Cleaner Commands
    public void WinTempFilesBatchCmd() throws IOException {

        if (winTempFolderPath.isDirectory()) {

            delThenCreateFile();

            if (tmpf.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpf));
                bw.write("@echo off");
                bw.newLine();
                bw.write("del /F /S /Q C:\\Windows\\Temp");
                bw.newLine();
                bw.write("del /F /S /Q /A:R C:\\Windows\\Temp");
                bw.newLine();
                bw.write("del /F /S /Q /A:H C:\\Windows\\Temp");
                bw.newLine();
                bw.write("exit");
                bw.close();

                execFile();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error! Can't find Windows Temp Folder.");
        }

    }

    //User Temp Files Cleaner Commands
    public void UserTempFilesBatchCmd() throws IOException {

        if (userTempFolderPath.isDirectory()) {

            delThenCreateFile();

            if (tmpf.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpf));
                bw.write("@echo off");
                bw.newLine();
                bw.write("del /F /S /Q %TMP%");
                bw.newLine();
                bw.write("del /F /S /Q /A:R %TMP%");
                bw.newLine();
                bw.write("del /F /S /Q /A:H %TMP%");
                bw.newLine();
                bw.write("exit");
                bw.close();

                execFile();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Error! User Temporary Folder not found!");
        }

    }

    //All Users Temp Files Cleaner Commands
    public void AllUsersTempFilesBatchCmd() throws IOException {

        if (winTempFolderPath.isDirectory() && userTempFolderPath.isDirectory()) {

            delThenCreateFile();

            if (tmpf.exists()) {

                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpf));
                bw.write("@echo off");
                bw.newLine();
                bw.write("");
                bw.newLine();
                bw.write("exit");
                bw.close();

                execFile();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error! All Users Temporary Folder not found!");
        }

    }

    //History Files Cleaner Commands
    public void HistoryFilesBatchCmd() throws IOException {

        if (runDllFile.exists()) {

            delThenCreateFile();

            if (tmpf.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpf));
                bw.write("@echo off");
                bw.newLine();
                bw.write("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 1");
                bw.newLine();
                bw.write("exit");
                bw.close();

                execFile();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error! Can't find History Folder.");
        }

    }

    //Prefetch Files Cleaner Commands
    public void PrefetchFilesBatchCmd() throws IOException {

        if (winTempFolderPath.isDirectory()) {

            delThenCreateFile();

            if (tmpf.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpf));
                bw.write("@echo off");
                bw.newLine();
                bw.write("del /q c:\\windows\\prefetch\\*.*");
                bw.newLine();
                bw.write("exit");
                bw.close();

                execFile();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error! Can't find Prefetch Folder.");
        }

    }

    //Cookies Files Cleaner Commands
    public void CookiesFilesBatchCmd() throws IOException {

        if (runDllFile.exists()) {

            delThenCreateFile();

            if (tmpf.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpf));
                bw.write("@echo off");
                bw.newLine();
                bw.write("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2");
                bw.newLine();
                bw.write("exit");
                bw.close();

                execFile();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error! Can't find Cookies Folder.");
        }

    }
    
    //Empty Recycle Bin
    public void RecycleBinBatchCmd() throws IOException {

        if (CFolderPath.isDirectory() == true) {

            delThenCreateFile();
            
            if (tmpf.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpf));
                bw.write("@echo off");
                bw.newLine();
                bw.write("rd /q /s c:\\$Recycle.Bin");
                bw.newLine();
                bw.write("exit");
                bw.close();

                execFile();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error!");
        }
    }

    //Special method for clean all button
    public void cleanAll() throws IOException {

        if (winTempFolderPath.isDirectory()) {

            delThenCreateFile();
            if (tmpf.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpf));
                bw.write("@echo off");
                bw.newLine();
                bw.write("");
                bw.write("exit");
                bw.close();

                execFile();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error!");
        }





    }

    //Delete the temp file when exit
    public void fileExitDel() {

        try {
            //File tempFile = new File(fileName);
            if (tmpf.exists()) {
                tmpf.deleteOnExit();
            }
        } catch (Exception ex) {
        }
    }

    //Delete if file exists and create it again
    public void delThenCreateFile() throws IOException {
        if (tmpf.exists()) {
            tmpf.delete();
        }
        tmpf.createNewFile();

        Runtime.getRuntime().exec("attrib +H " + tmpf); //hide batch file
    }

    //execute the file
    public void execFile() {
        try {

            if (tmpf.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Runtime.getRuntime().exec("cmd /c start /min " + tmpf);
                    if (tmpf.exists()) {
                        fileExitDel();
                    }
                    JOptionPane.showMessageDialog(null, "Cleaned!");

                } else {
                    JOptionPane.showMessageDialog(null, "File is not supported! contact the Developer");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error! try again later.");
            }

        } catch (Exception ex) {
        }
    }
}
