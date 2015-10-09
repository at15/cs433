package cn.edu.sjtu.stu.at15.video;


import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;

/**
 * Created by at15 on 10/9/2015.
 */
public class StreamClient {
    public static void main(String[] args) throws Exception {
        NativeLibrary.addSearchPath("libvlc", "C:/Program Files/VideoLAN/VLC");

        MediaPlayerFactory factory = new MediaPlayerFactory();
        MediaPlayer player = factory.newHeadlessMediaPlayer();

        String mrl = "rtp://239.1.1.1:5004";
        String options = ":sout=#transcode{vcodec=h264,venc=x264{cfr=16},scale=1,acodec=mp4a,ab=160,channels=2,samplerate=44100}:file{dst=D:/pt/t.mp4}";

        player.playMedia(mrl, options);

        Thread.sleep(20000); // <--- sleep for 20 seconds, you should use events - but this is just a demo

        player.stop();

        player.release();
        factory.release();
    }
}
