package cn.edu.sjtu.stu.at15.video;


import com.sun.jna.NativeLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by at15 on 10/9/2015.
 */
public class StreamClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(StreamClient.class);
    protected MediaPlayerFactory mediaPlayerFactory;

    public StreamClient() {
       this("C:/Program Files/VideoLAN/VLC");
    }

    public StreamClient(String libPath){
        LOGGER.info("add libvlc path" + libPath);
        NativeLibrary.addSearchPath("libvlc", libPath);
        mediaPlayerFactory = new MediaPlayerFactory();
    }

    public void run(String file) throws Exception {
        MediaPlayer player = mediaPlayerFactory.newHeadlessMediaPlayer();
        // TODO: change the listener
        player.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {
                LOGGER.info("stream finished");
            }
        });

        String mrl = "rtsp://:8554/vlc";
        // TODO: allow display and save file at same time
//        String options = ":sout=#transcode{vcodec=h264,venc=x264{cfr=16},scale=1,acodec=mp4a,ab=160,channels=2,samplerate=44100}:file{dst=D:/pt/t.mp4}";
        player.playMedia(mrl, getOptions(file));

        boolean shouldStop = false;
        while (!shouldStop) {
            Thread.sleep(1000);
            LOGGER.debug("waiting for vlc to finish receiving stream");
            if (player.getTime() == -1) {
                player.stop();
                player.release();
                shouldStop = true;
            }
        }

        mediaPlayerFactory.release();
        writeFinishFlag(file);
    }

    public String getOptions(String file) {
        // ":sout=#file{dst=D:/pt/t.mp4}"
        return ":sout=#file{dst=" + file + "}";
    }

    public void writeFinishFlag(String file) throws IOException{
        // Write the file flag for finish
        String finishFlag = file + ".finish";
        File flag = new File(finishFlag);
        if(flag.exists()){
            LOGGER.warn("finish flag already exists!");
            return;
        }
        flag.createNewFile();
        LOGGER.info("created finish flag " + finishFlag);
    }
}
