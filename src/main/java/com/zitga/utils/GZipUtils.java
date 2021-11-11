package com.zitga.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtils {

    private static final int BUFFER_SIZE = 16 * 1024; // 16 KB

    public static String compress(String data) throws Exception {
        byte[] utf8Bytes = data.getBytes(StandardCharsets.UTF_8);

        byte[] compressed;
        try (ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(BUFFER_SIZE)) {
            try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteOutputStream, BUFFER_SIZE)) {
                gzipOutputStream.write(utf8Bytes);
            }

            byteOutputStream.close();
            compressed = byteOutputStream.toByteArray();
        }

        ByteBuffer buffer = ByteBuffer.allocate(compressed.length).order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(compressed);

        return Base64.encodeBase64String(buffer.array());
    }

    public static String decompress(String compressedText) throws Exception {
        byte[] compressed = compressedText.getBytes(StandardCharsets.UTF_8);
        compressed = Base64.decodeBase64(compressed);

        StringBuilder sb = new StringBuilder();
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(compressed)) {
            try (GZIPInputStream gzipStream = new GZIPInputStream(byteStream, BUFFER_SIZE)) {
                try (BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(gzipStream, StandardCharsets.UTF_8), BUFFER_SIZE)) {

                    String line;
                    while ((line = bufferReader.readLine()) != null) {
                        sb.append(line);
                    }
                }
            }
        }

        return sb.toString();
    }
}
