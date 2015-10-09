package cn.edu.sjtu.stu.at15.video;

// http://andreinc.net/2013/12/06/java-7-nio-2-tutorial-writing-a-simple-filefolder-monitor-using-the-watch-service-api/
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 10/9/2015.
 */
public class FolderWatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(FolderWatcher.class);

    public static void main(String[] args) throws Exception{
        WatchService watchService = FileSystems.getDefault().newWatchService();
        // TODO: how to use absolute file path ...
        Path folder = Paths.get("hw");

        folder.register(watchService, ENTRY_CREATE);
        WatchKey key = null;
        while (true){
            key = watchService.take();
            WatchEvent.Kind<?> kind = null;
            for(WatchEvent<?> watchEvent: key.pollEvents()){
                kind = watchEvent.kind();
                if(ENTRY_CREATE == kind){
                    Path newPath = ((WatchEvent<Path>) watchEvent).context();
                    LOGGER.debug("file create " + newPath);
                }
            }
        }
    }
}
