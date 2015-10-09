package cn.edu.sjtu.stu.at15.video;


import com.sun.jna.NativeLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;

/**
 * Created by at15 on 10/9/2015.
 */
public class StreamClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(StreamClient.class);

    public static void main(String[] args) throws Exception {
        NativeLibrary.addSearchPath("libvlc", "C:/Program Files/VideoLAN/VLC");

        MediaPlayerFactory factory = new MediaPlayerFactory();
        MediaPlayer player = factory.newHeadlessMediaPlayer();
        player.addMediaPlayerEventListener(new VlcEventListener());

        String mrl = "rtp://239.1.1.1:5004";
        // TODO: change the transcode, ife only have :file, it will show it on screen directly
//        String options = ":sout=#transcode{vcodec=h264,venc=x264{cfr=16},scale=1,acodec=mp4a,ab=160,channels=2,samplerate=44100}:file{dst=D:/pt/t.mp4}";
        String options = "";

        player.playMedia(mrl, options);

        while (true) {
            Thread.sleep(1000);
            LOGGER.debug(String.valueOf(player.getTime()));
        }

//        player.stop();
//
//        player.release();
//        factory.release();
    }
}
