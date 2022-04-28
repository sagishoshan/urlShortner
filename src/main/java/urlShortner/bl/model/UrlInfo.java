package urlShortner.bl.model;

import java.time.LocalDateTime;

public class UrlInfo {

    //region Members
    private String        url;
    private LocalDateTime lastUsedAt;
    //endregion

    //region Constructor
    public UrlInfo(String url, LocalDateTime lastUsedAt) {
        this.url = url;
        this.lastUsedAt = lastUsedAt;
    }
    //endregion

    //region Getters & Setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getLastUsedAt() {
        return lastUsedAt;
    }

    public void setLastUsedAt(LocalDateTime lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }
    //endregion
}
