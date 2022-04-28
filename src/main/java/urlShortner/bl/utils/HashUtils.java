package urlShortner.bl.utils;

import urlShortner.AppContext;

import java.math.BigInteger;
import java.security.MessageDigest;

public class HashUtils {
    public static String createShortHash(final String input, Integer keyLength) {
        String        retVal;
        MessageDigest md = AppContext.getInstance().getMessageDigest();

        // Compute message digest of the input
        byte[] messageDigest = md.digest(input.getBytes());
        String hashedText = convertToHex(messageDigest);
        retVal = hashedText.substring(0, keyLength);

        return retVal;
    }

    private static String convertToHex(final byte[] messageDigest) {
        BigInteger bigint  = new BigInteger(1, messageDigest);
        String     hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }

        return hexText;
    }
}
