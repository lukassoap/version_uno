/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.playlistversionuno.modelos;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import java.util.ListIterator;

public class SimpleAudioPlayer {
    private MediaPlayer mediaPlayer;
    private String currentSong;
    private ListIterator<Cancion> iterator;// or could change this //  changing for cancion
    private boolean isManualStop = false;

    public SimpleAudioPlayer(ListIterator<Cancion> iterator) {
        this.iterator = iterator;
        playNextSong(); // Start with the first song
    }

    private void playSong(String filePath) {
        stop(); // Stop current if playing

        URL resource = getClass().getResource("/" + filePath);// en vez de resource path
        File audioFile = new File(filePath);
        if (!audioFile.exists()) {
            System.out.println("❌ File not found: " + filePath);
            return;
        }

        Media media = new Media(resource.toExternalForm()); // cambie esto revisar
        mediaPlayer = new MediaPlayer(media);
        currentSong = filePath;
        isManualStop = false;

        mediaPlayer.setOnReady(() -> {
            System.out.println("▶ Now playing: " + filePath);
            mediaPlayer.play();
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            if (!isManualStop) {
                System.out.println("✔ Finished: " + currentSong);
                playNextSong();
            }
        });
    }

    public void playNextSong() {
        if (iterator.hasNext()) {
            String next = iterator.next().getTitulo();
            playSong(next);
        } else {
            System.out.println("🎵 No next song.");
        }
    }

    public void playPreviousSong() {
        if (iterator.hasPrevious()) {
            String prev = iterator.previous().getTitulo();
            playSong(prev);
        } else {
            System.out.println("🎵 No previous song.");
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            System.out.println("⏸ Paused.");
        }
    }

    public void resumeAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
            System.out.println("▶ Resumed.");
        }
    }

    public void restart() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(javafx.util.Duration.ZERO);
            mediaPlayer.play();
            System.out.println("🔁 Restarted.");
        }
    }

    public void stop() {
        isManualStop = true;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            System.out.println("⏹ Stopped.");
        }
    }

    public void jump(double seconds) {
        if (mediaPlayer != null) {
            javafx.util.Duration duration = javafx.util.Duration.seconds(seconds);
            mediaPlayer.seek(duration);
            System.out.println("⏩ Jumped to: " + seconds + " seconds.");
        }
    }

    public void gotoChoice(int c) {
        switch (c) {
            case 1: pause(); break;
            case 2: resumeAudio(); break;
            case 3: restart(); break;
            case 4: stop(); break;
            case 5:
                System.out.print("Enter time (seconds): ");
                java.util.Scanner sc = new java.util.Scanner(System.in);
                double time = sc.nextDouble();
                jump(time);
                break;
            case 6: playNextSong(); break;
            case 7: playPreviousSong(); break;
            default: System.out.println("❌ Invalid choice.");
        }
    }
}
