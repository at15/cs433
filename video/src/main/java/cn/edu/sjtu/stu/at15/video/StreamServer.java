package cn.edu.sjtu.stu.at15.video;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

/**
 * Created by at15 on 10/9/2015.
 */
public class StreamServer {

    public static void main(String[] args) throws Exception {

        NativeLibrary.addSearchPath("libvlc", "C:/Program Files/VideoLAN/VLC");
        String media = "file:///D:/pt/short.mp4";
        String options = formatRtpStream("239.1.1.1", 5004);

        System.out.println("Streaming '" + media + "' to '" + options + "'");

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(args);
        HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();

        mediaPlayer.addMediaPlayerEventListener(new VlcEventListener());

        mediaPlayer.playMedia(media,
                options,
                ":no-sout-rtp-sap",
                ":no-sout-standard-sap",
                ":sout-all",
                ":sout-keep"
        );



        Thread.currentThread().join();
    }

    private static String formatRtpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#rtp{dst=");
        sb.append(serverAddress);
        sb.append(",port=");
        sb.append(serverPort);
        sb.append(",mux=ts}");
        return sb.toString();
    }
}
