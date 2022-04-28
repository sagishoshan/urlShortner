package urlShortner;

import urlShortner.bl.model.UrlInfo;

import java.security.MessageDigest;
import java.util.Map;

public class AppContext {

    //region Singleton
    private static AppContext instance;

    public static AppContext getInstance() {
        if (instance == null) {
            synchronized (AppContext.class) {
                if (instance == null) {
                    instance = new AppContext();
                }
            }
        }
        return instance;
    }
    //endregion

    //region Members
    private Map<String, UrlInfo> urlInfoMappedByKey;
    private MessageDigest        messageDigest;
    private Integer              shortUrlLength;
    private Integer              ttlInSeconds;
    //endregion

    //region Getters & Setters
    public Map<String, UrlInfo> getUrlInfoMappedByKey() {
        return urlInfoMappedByKey;
    }

    public void setUrlInfoMappedByKey(Map<String, UrlInfo> urlInfoMappedByKey) {
        this.urlInfoMappedByKey = urlInfoMappedByKey;
    }

    public MessageDigest getMessageDigest() {
        return messageDigest;
    }

    public void setMessageDigest(MessageDigest messageDigest) {
        this.messageDigest = messageDigest;
    }

    public Integer getShortUrlLength() {
        return shortUrlLength;
    }

    public void setShortUrlLength(Integer shortUrlLength) {
        this.shortUrlLength = shortUrlLength;
    }

    public Integer getTtlInSeconds() {
        return ttlInSeconds;
    }

    public void setTtlInSeconds(Integer ttlInSeconds) {
        this.ttlInSeconds = ttlInSeconds;
    }
    //endregion


}
