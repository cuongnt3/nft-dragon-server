package com.zitga.idle.utils;

import com.zitga.core.message.http.HttpResponse;
import com.zitga.core.utils.http.HttpRequestHelper;
import com.zitga.utils.BitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ServerUtils {

    private static final Logger logger = LoggerFactory.getLogger(ServerUtils.class);

    private static final String PUBLIC_IP_CHECK_URL = "https://api.my-ip.io/ip";
    private static String publicServerAddress = null;

    static {
        try {
            HttpResponse response = HttpRequestHelper.get(PUBLIC_IP_CHECK_URL);
            publicServerAddress = response.getData();

            logger.info("Public server address: {}", publicServerAddress);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String getPublicServerAddress() {
        return publicServerAddress;
    }

    public static String getStackTrace() {
        Throwable throwable = new Throwable();
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));

        return stringWriter.toString();
    }

    public static String toStringFromBitMask(long bitMask) {
        StringBuilder builder = new StringBuilder();
        builder.append("| ");

        for (int i = 0; i < Long.SIZE; i++) {
            if (BitUtils.isOn(bitMask, i)) {
                builder.append(i);
                builder.append(" ");
            }
        }

        builder.append(" |");
        return builder.toString();
    }

    public static String toStringFromBitMask(long bitMask, long secondBitMask) {
        StringBuilder builder = new StringBuilder();
        builder.append("| ");

        for (int i = 0; i < 2 * Long.SIZE; i++) {

            boolean isOn = false;
            if (i < Long.SIZE) {
                isOn = BitUtils.isOn(bitMask, i);
            } else {
                isOn = BitUtils.isOn(secondBitMask, i);
            }

            if (isOn) {
                builder.append(i);
                builder.append(" ");
            }
        }

        builder.append(" |");
        return builder.toString();
    }

    public static String getAddress(SocketAddress senderAddress) {
        if (senderAddress == null) {
            return "";
        }

        String address = senderAddress.toString();
        if (senderAddress instanceof InetSocketAddress) {
            address = ((InetSocketAddress) senderAddress).getAddress().toString();
            address = address.replace("/", "");
        }

        return address;
    }
}
