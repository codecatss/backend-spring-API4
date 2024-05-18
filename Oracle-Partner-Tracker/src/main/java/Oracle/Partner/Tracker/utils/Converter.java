package Oracle.Partner.Tracker.utils;

import java.nio.charset.StandardCharsets;

public class Converter {
    public byte[] stringToByte(String text) {
        return text.getBytes(StandardCharsets.UTF_8);
    }

    public String byteToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String byteToHexadecimal(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    public byte[] hexadecimalToByte(String hexadecimal) {
        int length = hexadecimal.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexadecimal.charAt(i), 16) << 4)
                    + Character.digit(hexadecimal.charAt(i+1), 16));
        }
        return data;
    }
}

