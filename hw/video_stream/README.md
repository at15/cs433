# Upload video stream to HDFS

DUE: 10-08

## generate video stream use vlc

- [ ] config vlc
- [ ] setup multicast server
- [ ] setup multicast client

## receive video stream and upload to hdfs

- [ ] receive video stream and store in local folder as file. and no temporary files (? strange request)
- [ ] use the file as cache and upload to HDFS, use FSDataOutputStream.write(buffer,0,bytesRead)
- [ ] clean all files when upload finish.

The teacher said store all the frames and upload as image ... which is quite strange ...

so the step is

- [ ] download a small video (ie: 30mb)
- [ ] multicast this video as stream use vlc
- [ ] receive multicast and write to file as cache
- [ ] upload cache to hdfs
- [ ] clean up cache.

## Possible enhancement

- [ ] play the video from hdfs
- [ ] generate the video use camera, ie: opencv or webrtc. (guess java does not have support for opencv)

## Links

- use vlc to push video stream http://blog.chinaunix.net/uid-26527046-id-4409467.html
- ip multicast gns3+vlc http://www.pythoner.io/2012/11/26/ip-multicast-gns-vlc/

- vlc multicast http://wenku.baidu.com/link?url=b3Z-UL8-gad60rlm9jBVr5oTlP33j23Uz2y9o0Mrr0tZAmmkIynkl4z9idM0NoqhjWJnpG7EoleqOzDixcvTaji3vvl5br6E_XEqOvn7N4_
- how to vlc commandline on mac https://wiki.videolan.org/Mac_OS_X/
- rtp java http://wenku.baidu.com/link?url=f0HdM_bbYB50SK2rHNM7bMIzGev-jFjMVYCEX5rs1QKZjQXDm3ZbNoOxXYpfgLfy7rpPWfJxTZkkw0rTbmofLZj4Bf5aVT2rXf8ITL74oDC

receive data through rtp using jmf

- http://www.cs.odu.edu/~cs778/spring04/lectures/jmfsolutions/examplesindex.html