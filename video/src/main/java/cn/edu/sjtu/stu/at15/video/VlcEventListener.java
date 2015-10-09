package cn.edu.sjtu.stu.at15.video;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 * Created by at15 on 10/9/2015.
 */
public class VlcEventListener extends MediaPlayerEventAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(VlcEventListener.class);

    private static void dbg(String msg){
        LOGGER.debug(msg);
    }
    @Override
    public void mediaChanged(MediaPlayer mediaPlayer, libvlc_media_t media, String mrl) {
       dbg("media changed");
    }

    @Override
    public void opening(MediaPlayer mediaPlayer) {
        dbg("opening");
    }

    @Override
    public void buffering(MediaPlayer mediaPlayer, float newCache) {
        dbg("buffering");
    }

    @Override
    public void playing(MediaPlayer mediaPlayer) {
        dbg("playing");
    }

    @Override
    public void paused(MediaPlayer mediaPlayer) {
        dbg("paused");
    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {
        dbg("stopped");
    }

    @Override
    public void forward(MediaPlayer mediaPlayer) {
        dbg("forward");
    }

    @Override
    public void backward(MediaPlayer mediaPlayer) {
        dbg("backward");
    }

    @Override
    public void finished(MediaPlayer mediaPlayer) {
        dbg("finished");
    }

    @Override
    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
        dbg("time changed");
    }

    @Override
    public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
    }

    @Override
    public void seekableChanged(MediaPlayer mediaPlayer, int newSeekable) {
    }

    @Override
    public void pausableChanged(MediaPlayer mediaPlayer, int newPausable) {
    }

    @Override
    public void titleChanged(MediaPlayer mediaPlayer, int newTitle) {
    }

    @Override
    public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {
    }

    @Override
    public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
        dbg("length changed");
    }

    @Override
    public void videoOutput(MediaPlayer mediaPlayer, int newCount) {
        dbg("video output");
    }

    @Override
    public void scrambledChanged(MediaPlayer mediaPlayer, int newScrambled) {
    }

    @Override
    public void elementaryStreamAdded(MediaPlayer mediaPlayer, int type, int id) {
        dbg("elementary stream added");
    }

    @Override
    public void elementaryStreamDeleted(MediaPlayer mediaPlayer, int type, int id) {
        dbg("elementary stream deleted");
    }

    @Override
    public void elementaryStreamSelected(MediaPlayer mediaPlayer, int type, int id) {
        dbg("elementary stream selected");
    }

    @Override
    public void corked(MediaPlayer mediaPlayer, boolean corked) {
        dbg("corked");
    }

    @Override
    public void muted(MediaPlayer mediaPlayer, boolean muted) {
        dbg("muted");
    }

    @Override
    public void volumeChanged(MediaPlayer mediaPlayer, float volume) {
        dbg("vol changed");
    }

    @Override
    public void audioDeviceChanged(MediaPlayer mediaPlayer, String audioDevice) {
    }

    @Override
    public void error(MediaPlayer mediaPlayer) {
        dbg("error");
    }

    // === Events relating to the current media =================================

    @Override
    public void mediaSubItemAdded(MediaPlayer mediaPlayer, libvlc_media_t subItem) {
        dbg("media sub item added");
    }

    @Override
    public void mediaDurationChanged(MediaPlayer mediaPlayer, long newDuration) {
        dbg("media duration changed");
    }

    @Override
    public void mediaParsedChanged(MediaPlayer mediaPlayer, int newStatus) {
        dbg("media parse changed");
    }

    @Override
    public void mediaFreed(MediaPlayer mediaPlayer) {
        dbg("media freed");
    }

    @Override
    public void mediaStateChanged(MediaPlayer mediaPlayer, int newState) {
        dbg("media state changed");
    }

    @Override
    public void mediaMetaChanged(MediaPlayer mediaPlayer, int metaType) {
        dbg("media meta changed");
    }

    @Override
    public void mediaSubItemTreeAdded(MediaPlayer mediaPlayer, libvlc_media_t item) {
        dbg("media subitem tree added");
    }

    // === Synthetic/semantic events ============================================

    @Override
    public void newMedia(MediaPlayer mediaPlayer) {
        dbg("new media");
    }

    @Override
    public void subItemPlayed(MediaPlayer mediaPlayer, int subItemIndex) {
        dbg("sub item played");
    }

    @Override
    public void subItemFinished(MediaPlayer mediaPlayer, int subItemIndex) {
        dbg("sub item finished");
    }

    @Override
    public void endOfSubItems(MediaPlayer mediaPlayer) {
        dbg("end of sub items");
    }
}
