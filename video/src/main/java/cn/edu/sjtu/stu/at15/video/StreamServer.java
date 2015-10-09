package cn.edu.sjtu.stu.at15.video;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

/**
 * Created by at15 on 10/9/2015.
 */
public class StreamServer {

    public static void main(String[] args) throws Exception {
//        if(args.length != 1) {
//            System.out.println("Specify a single MRL to stream");
//            System.exit(1);
//        }
        NativeLibrary.addSearchPath("libvlc", "C:/Program Files/VideoLAN/VLC");
//        String media = args[0];
        String media = "file:///D:/pt/Thor - The Dark World - Trailer 2.mp4";
        String options = formatRtpStream("239.1.1.1", 5004);

        System.out.println("Streaming '" + media + "' to '" + options + "'");

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(args);
        HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();

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
