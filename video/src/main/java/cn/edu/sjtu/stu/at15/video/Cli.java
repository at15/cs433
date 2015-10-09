package cn.edu.sjtu.stu.at15.video;

/**
 * Created by at15 on 10/9/2015.
 *
 * The commandline entrance
 */
public class Cli {
    public static void main(String[] args) throws Exception{
        // parse args to determine which server to start
        if(args.length < 1){
            printUsage();
        }
        if(args[0].startsWith("serve")){
            System.out.println("serve media file through rtsp");
            if(args.length > 1){
                String file = args[1];
                // TODO: check if file exists
                StreamServer streamServer = new StreamServer();
                streamServer.run(args[1]);
            }
        }
    }

    public static void printUsage(){
        System.out.println("Usage: \n" +
                "serve <file name>  - stream a file in rtsp to rtsp://:8554/vlc");
    }
}
