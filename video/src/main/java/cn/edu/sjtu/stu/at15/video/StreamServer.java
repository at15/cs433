package cn.edu.sjtu.stu.at15.video;

import com.sun.jna.NativeLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

/**
 * Created by at15 on 10/9/2015.
 *
 * Stream media using rtsp protocol
 */
public class StreamServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamServer.class);

    protected MediaPlayerFactory mediaPlayerFactory;
    protected String options = ":sout=#rtp{sdp=rtsp://:8554/vlc}";

    public StreamServer() {
        this("C:/Program Files/VideoLAN/VLC");
    }

    public StreamServer(String libPath) {
        LOGGER.info("add libvlc path" + libPath);
        NativeLibrary.addSearchPath("libvlc", libPath);
        mediaPlayerFactory = new MediaPlayerFactory();
    }

    public void run(String media) throws Exception {
        boolean shouldStop = false;
        HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
        // TODO: listen to finished event only
        mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventAdapter(){
            @Override
            public void finished(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {
                LOGGER.info("stream finished");
            }
        });
        mediaPlayer.playMedia(media,
                options,
                ":no-sout-rtp-sap",
                ":no-sout-standard-sap",
                ":sout-all",
                ":sout-keep"
        );

        while (!shouldStop){
            LOGGER.debug("wait for vlc to finish streaming, let's take a nap");
            Thread.sleep(1000);
            if(mediaPlayer.getTime() == -1){
                mediaPlayer.stop();
                mediaPlayer.release();
                shouldStop = true;
            }
        }

        mediaPlayerFactory.release();
    }
}
