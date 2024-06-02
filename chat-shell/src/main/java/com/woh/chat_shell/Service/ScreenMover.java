package com.woh.chat_shell.Service;

import javazoom.jl.player.Player;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
@Component
public class ScreenMover {

    public void moveScreenForMac() throws IOException, InterruptedException {
        String initialPositionCommand = "osascript -e 'tell application \"System Events\" to tell process \"Terminal\" to get position of window 1'";
        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", initialPositionCommand});
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String initialPosition = reader.readLine().replaceAll("[{}]", "");
        String[] positions = initialPosition.split(", ");
        int initialX = Integer.parseInt(positions[0]);
        int initialY = Integer.parseInt(positions[1]);
        Thread soundThread = new Thread(this::playSound);
        soundThread.start();
        for (int i = 0; i < 3; i++) {
            int newY = initialY + 10;
            String moveDownCommand = String.format("osascript -e 'tell application \"System Events\" to tell process \"Terminal\" to set position of window 1 to {%d, %d}'", initialX, newY);
            String moveUpCommand = String.format("osascript -e 'tell application \"System Events\" to tell process \"Terminal\" to set position of window 1 to {%d, %d}'", initialX, initialY);
            Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", moveDownCommand});
            Thread.sleep(100);
            Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", moveUpCommand});
            Thread.sleep(100);
        }
        soundThread.join();

    }
    public void moveScreenForWindows() throws IOException, InterruptedException {
        String[] command = {
                "powershell",
                "-Command",
                "Add-Type -TypeDefinition 'using System;using System.Runtime.InteropServices;public class User32 {[DllImport(\"user32.dll\", SetLastError=true)]public static extern IntPtr GetForegroundWindow();[DllImport(\"user32.dll\", SetLastError=true)][return: MarshalAs(UnmanagedType.Bool)]public static extern bool GetWindowRect(IntPtr hWnd, out RECT lpRect);[DllImport(\"user32.dll\", SetLastError=true)][return: MarshalAs(UnmanagedType.Bool)]public static extern bool SetWindowPos(IntPtr hWnd, int hWndInsertAfter, int X, int Y, int cx, int cy, uint uFlags);public struct RECT {public int Left;public int Top;public int Right;public int Bottom;}}';$handle = [User32]::GetForegroundWindow();$rect = New-Object User32+RECT;[User32]::GetWindowRect($handle, [ref]$rect) | Out-Null;$initialX = $rect.Left;$initialY = $rect.Top;for ($i = 0; $i -lt 3; $i++) {$newY = $initialY + 10;[User32]::SetWindowPos($handle, 0, $initialX, $newY, 0, 0, 0x0001) | Out-Null;Start-Sleep -Milliseconds 100;[User32]::SetWindowPos($handle, 0, $initialX, $initialY, 0, 0, 0x0001) | Out-Null;Start-Sleep -Milliseconds 100;}"
        };

        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    }
    public void playSound (){
            try {
                FileInputStream fis = new FileInputStream("src/main/java/com/woh/chat_shell/Sounds/nudge.mp3");
                Player playMP3 = new Player(fis);
                playMP3.play();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
