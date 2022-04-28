package urlShortner.api.responses;

public class CreateShortUrlResponse {
    //region Members
    private String key;

    public CreateShortUrlResponse(String key) {
        this.key = key;
    }
    //endregion

    //region Getters & Setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    //endregion
}
