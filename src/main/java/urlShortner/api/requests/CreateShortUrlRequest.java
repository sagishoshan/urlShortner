package urlShortner.api.requests;

import javax.validation.constraints.NotNull;

public class CreateShortUrlRequest {
    @NotNull
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
