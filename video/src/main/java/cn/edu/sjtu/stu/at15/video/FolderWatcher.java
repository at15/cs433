package cn.edu.sjtu.stu.at15.video;

// http://andreinc.net/2013/12/06/java-7-nio-2-tutorial-writing-a-simple-filefolder-monitor-using-the-watch-service-api/

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by at15 on 10/9/2015.
 * <p/>
 * Watch folder change and do the upload and clean up
 */
public class FolderWatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(FolderWatcher.class);
    private static final String FINISH = ".finish";
    public final String watchDir;
    public final String uploadBaseUrl;


    protected HDFSClient client;

    public FolderWatcher(String watchDir) {
        this(watchDir, "/user/media");
    }

    public FolderWatcher(String watchDir, String uploadBaseUrl) {
        this.watchDir = watchDir;
        this.uploadBaseUrl = uploadBaseUrl;
    }

    public void run() {
        LOGGER.info("file watcher start running");
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            // TODO: how to use absolute file path ...
            Path folder = Paths.get(watchDir);

            folder.register(watchService, ENTRY_CREATE);
            WatchKey key = null;
            while (true) {
                key = watchService.take();
                WatchEvent.Kind<?> kind = null;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    kind = watchEvent.kind();
                    if (ENTRY_CREATE == kind) {
                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        LOGGER.debug("file create " + newPath);
                        if (newPath.toString().endsWith(FINISH)) {
                            // NOTE: need to add watchDir to path
                            String finishFilePath = watchDir + "/" + newPath.toString();
                            String srcFilePath = finishFilePath.substring(0, finishFilePath.length() - FINISH.length());
                            LOGGER.debug("src file path " + srcFilePath);
                            // check if src file exists
                            File srcFile = new File(srcFilePath);
                            File finishFile = new File(finishFilePath);
                            if (srcFile.exists()) {
                                LOGGER.info("need to upload " + srcFile);
                                upload(srcFile);
                            } else {
                                LOGGER.warn("finish file point to a no existing file ");
                            }
                            // delete the flag.
                            finishFile.delete();
                        }
                    }
                }
                if (!key.reset()) {
                    break; //loop
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void setClient(HDFSClient client) {
        this.client = client;
    }

    // TODO: is this unblocking io？
    public void upload(File file) {
        if (client == null) {
            LOGGER.warn("client is not set, ignore upload " + file.getName());
            return;
        }
        try {
            client.upload(file, getUploadPath(file));
            // remove the file after uploaded
            file.delete();
        } catch (IOException ex) {
            LOGGER.warn("error uploading ", ex);
        }
    }

    // TODO: remove extra trailing slash
    public String getUploadPath(File file) {
        return uploadBaseUrl + "/" + file.getName();
    }
}
