package com.ouieat;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import org.apache.logging.log4j.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class OuieatApplication {
    public static void main(String[] args) {
        SpringApplication.run(OuieatApplication.class, args);
    }

    String getLocalIP() throws SocketException {
        String candidateIP_192 = null;
        String candidateIP_10 = null;
        String candidateIP_en = null;

        for (var networkInterface : Collections.list(
            NetworkInterface.getNetworkInterfaces()
        )) {
            var addresses = Collections.list(
                networkInterface.getInetAddresses()
            );
            var isEthernetInterface = networkInterface
                .getName()
                .startsWith("en");

            String ipv4Address = null;

            for (var address : addresses) {
                boolean isIPv4Address = address.getAddress().length == 4;

                if (isIPv4Address) {
                    ipv4Address = address.getHostAddress();
                }
            }

            // Skip addresses that only have IPv6 representations
            if (ipv4Address == null) continue;

            if (isEthernetInterface) candidateIP_en = ipv4Address;
            if (ipv4Address.startsWith("192.168.")) candidateIP_192 =
                ipv4Address;
            if (ipv4Address.startsWith("10.")) candidateIP_10 = ipv4Address;
        }

        if (candidateIP_192 != null) return candidateIP_192;
        if (candidateIP_en != null) return candidateIP_en;

        return candidateIP_10;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onStartup() {
        try {
            OuiLogger.log(
                Level.INFO,
                "Server Local IP address: " + this.getLocalIP() + ":8080"
            );
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
