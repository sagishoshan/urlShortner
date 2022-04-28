package urlShortner.bl.utils;

import urlShortner.bl.model.UrlInfo;

import java.time.Duration;
import java.time.LocalDateTime;

public class TtlUtils {

    private static final Integer TTL_LIMIT_IN_SECONDS = 30 * 60;

    public static Boolean isTtlExpired(UrlInfo urlInfo) {
        Boolean retVal = true;

        LocalDateTime lastUsedAt          = urlInfo.getLastUsedAt();
        Duration      duration            = Duration.between(LocalDateTime.now(), lastUsedAt);
        long          differenceInSeconds = Math.abs(duration.getSeconds());

        if (TTL_LIMIT_IN_SECONDS >= differenceInSeconds) {
            retVal = false;
        }

        return retVal;
    }
}
