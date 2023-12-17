package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {

    private static final int NUMBER_OF_PORTS = 49152;
    private static final String FORMAT = "%1$5s";
    private final static Logger LOGGER = LogManager.getLogger();
    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:MultipleStringLiterals"})
    private static final HashMap<Integer, String> SERVICES =
        new HashMap<>() {{
            put(135, "EPMAP");
            put(137, "Служба имен NetBIOS");
            put(138, "Служба датаграмм NetBIOS");
            put(139, "Служба сеансов NetBIOS");
            put(445, "Microsoft-DS Active Directory");
            put(843, "Adobe Flash");
            put(1900, "Simple Service Discovery Protocol (SSDP)");
            put(3702, "Динамическое обнаружение веб-служб");
            put(5353, "Многоадресный DNS");
            put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
            put(17500, "Dropbox");
            put(27017, "MongoDB");
            put(80, "HTTP (HyperText Transfer Protocol)");
            put(21, "FTP (File Transfer Protocol)");
            put(25, "SMTP (Simple Mail Transfer Protocol)");
            put(22, "SSH (Secure Shell)");
            put(443, "HTTPS (HyperText Transfer Protocol Secure)");
            put(53, "DNS (Domain Name System)");
            put(3306, "MySQL Database");
            put(5432, "PostgreSQL Database");
            put(3389, "Remote Desktop Protocol (RDP)");
            put(1521, "Oracle Database");
            put(49152, "Windows RPC (Remote Procedure Call)");
            put(5672, "AMQP (Advanced Message Queuing Protocol)");
            put(49153, "Windows RPC (Remote Procedure Call)");
            put(23, "Telnet - Telnet protocol to ensure effective communication along with the remote server.");
            put(110, "POP3 - Post Office Protocol version 3 for email retrieval.");
            put(143, "IMAP - Internet Message Access Protocol for email retrieval.");
            put(67, "DHCP - Dynamic Host Configuration Protocol for IP address allocation.");
            put(68, "DHCP - Dynamic Host Configuration Protocol for IP address allocation.");
            put(123, "NTP - Network Time Protocol for time synchronization.");
            put(548, "AFP - Apple Filing Protocol for file sharing between Macs.");
            put(8080, "HTTP Proxy - HTTP proxy server.");
            put(1080, "SOCKS - SOCKS proxy server.");
            put(1433, "MSSQL - Microsoft SQL Server database server.");
            put(389, "LDAP - Lightweight Directory Access Protocol for directory services.");
            put(5722, "SMB2 - SMB version 2 protocol.");
            put(500, "IKE - Internet Key Exchange protocol for VPN connections.");
        }};

    public static Map<Integer, String> checkPorts() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < NUMBER_OF_PORTS; i++) {
            if (isTCPFree(i)) {
                if (!isUDPFree(i)) {
                    linkedHashMap.put(i, "UDP");
                }
            } else {
                linkedHashMap.put(i, "TCP");
            }
        }
        return linkedHashMap;
    }

    public static boolean isTCPFree(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isUDPFree(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            datagramSocket.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void printPorts(Map<Integer, String> ports) {
        LOGGER.info("Протокол Служба Сервис");
        for (var entry: ports.entrySet()) {
            String result = entry.getValue() + "       " + String.format(FORMAT, entry.getKey());
            if (SERVICES.containsKey(entry.getKey())) {
                result += "   " + SERVICES.get(entry.getKey());
            }
            LOGGER.info(result);
        }
    }

    private Task6() {
    }
}
