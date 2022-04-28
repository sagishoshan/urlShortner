package urlShortner.bl.cmds;

import urlShortner.AppContext;
import urlShortner.api.requests.CreateShortUrlRequest;
import urlShortner.api.responses.CreateShortUrlResponse;
import urlShortner.bl.model.UrlInfo;
import urlShortner.bl.utils.HashUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Map;

import static urlShortner.bl.utils.UrlUtils.isUrlValid;

public class CreateShortUrlCmd extends BaseCmd<CreateShortUrlResponse> {

    //region Members
    private final Map<String, UrlInfo> urlInfoMappedByKey;
    private final Integer              SHORT_URL_LENGTH = AppContext.getInstance().getShortUrlLength();
    private       String               url;

    public CreateShortUrlCmd(CreateShortUrlRequest request) {
        this.url = request.getUrl();
        this.urlInfoMappedByKey = AppContext.getInstance().getUrlInfoMappedByKey();
    }

    public void execute() {
        Boolean isUrlValid = isUrlValid(url);

        if (isUrlValid) {
            String hashedKey = HashUtils.createShortHash(url, SHORT_URL_LENGTH);
            storeUrlInMap(hashedKey);
            setResult(new CreateShortUrlResponse(hashedKey));
        }
        else {
            throw new WebApplicationException("The given input is not a valid URL", Response.Status.BAD_REQUEST);
        }
    }

    private void storeUrlInMap(String hashedKey) {
        UrlInfo urlInfo = new UrlInfo(url, LocalDateTime.now());
        urlInfoMappedByKey.put(hashedKey, urlInfo);
    }
}
