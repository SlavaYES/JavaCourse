package ru.slava;

import com.github.kiulian.downloader.OnYoutubeDownloadListener;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.YoutubeException;
import com.github.kiulian.downloader.model.VideoDetails;
import com.github.kiulian.downloader.model.YoutubeVideo;
import com.github.kiulian.downloader.model.formats.AudioFormat;
import com.github.kiulian.downloader.model.formats.AudioVideoFormat;
import com.github.kiulian.downloader.model.formats.Format;
import com.github.kiulian.downloader.model.formats.VideoFormat;

import javax.security.sasl.SaslServer;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws YoutubeException, IOException, InterruptedException, ExecutionException, TimeoutException {
        YoutubeDownloader downloader = new YoutubeDownloader();
        String videoUrl = "_idmAk9b9wE";
        YoutubeVideo video = downloader.getVideo(videoUrl);

        VideoDetails details = video.details();
        PrintStream printStream = new PrintStream(System.out, true, "utf-8");

        printStream.println(details.title());
//        printStream.println(details.description());
//        printStream.println(details.liveUrl());

//        printStream.println(details.viewCount());
//        details.thumbnails().forEach(
//                image -> printStream.println("Thumbnail: " + image));

        // Get url link for download and watch on site browzer
        List<AudioVideoFormat> audioVideo = video.videoWithAudioFormats();
        audioVideo.forEach(iter -> {
//            printStream.println("Audio Video - " + iter.audioQuality() + " " + iter.videoQuality() + " : " + iter.url());
            printStream.println("Audio Video - " + iter.audioQuality() + " " + iter.videoQuality());
        });

        List<AudioFormat> audio = video.audioFormats();
        audio.forEach(iter -> {
//            printStream.println("Audio - " + iter.audioQuality() + " : " + iter.url());
            printStream.println("Audio - " + iter.audioQuality());
        });

        List<VideoFormat> videoF = video.videoFormats();
        videoF.forEach(iter -> {
//            printStream.println("Video - " + iter.videoQuality() + " : " + iter.url());
            printStream.println("Video - " + iter.videoQuality());
        });

        // DownLoad
        Format formatByItag = video.findFormatByItag(137);
        if (formatByItag != null) {
            System.out.println(formatByItag.url());
        } else {
            System.out.println("Not");
        }

        File outDir = new File("my_video");
        Format format = videoF.get(0);
        File file = video.download(format, outDir);

        Future<File> future = video.downloadAsync(format, outDir, new OnYoutubeDownloadListener() {
            @Override
            public void onDownloading(int progress) {
                System.out.printf("Download %d%%\n", progress);
            }

            @Override
            public void onFinished(File file) {
                System.out.println("Finished file: " + file);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: " + throwable.getLocalizedMessage());
            }
        });
    }
}
