package cn.edu.sjtu.stu.at15.video;

/**
 * Created by at15 on 10/9/2015.
 * <p/>
 * The commandline entrance
 */
public class Cli {
    public static void main(String[] args) throws Exception {
        // parse args to determine which server to start
        if (args.length < 1) {
            printUsage();
            return;
        }
        if (args[0].startsWith("serve")) {
            if (args.length > 1) {
                String file = args[1];
                // TODO: check if file exists
                StreamServer server;
                if (args.length > 2) {
                    server = new StreamServer(args[2]);
                } else {
                    server = new StreamServer();
                }
                server.run(file);
            }
            return;
        }
        if (args[0].startsWith("receive")) {
            if (args.length > 1) {
                String file = args[1];
                StreamClient client;
                if (args.length > 2) {
                    client = new StreamClient(args[2]);
                } else {
                    client = new StreamClient();
                }
                client.run(file);
            }
            return;
        }
        if (args[0].startsWith("monitor")) {
            if (args.length > 2) {
                String watchDir = args[1];
                String uploadBaseUrl = args[2];
                FolderWatcher watcher = new FolderWatcher(watchDir, uploadBaseUrl);
                watcher.run();
            }
            return;
        }
        if (args[0].startsWith("upload")) {
            if (args.length > 2) {
                String src = args[1];
                String dst = args[2];
                HDFSClient client = new HDFSClient();
                client.upload(src, dst);
            }
            return;
        }
    }

    public static void printUsage() {
        System.out.println("Usage: \n" +
                        "- serve <file name> [libvlc path]          stream a file in rtsp to rtsp://:8554/vlc \n" +
                        "- receive <file name> [libvlc path]        receive rtsp stream from rtsp://:8554/vlc and save to file \n" +
                        "- upload <file name> <dst>                 upload a file in local file system to hdfs using FSDataOutputStream \n" +
                        "- monitor <watch dir> <upload base url>    watch the tmp dir and upload video when client has finished streaming "
        );
    }
}
