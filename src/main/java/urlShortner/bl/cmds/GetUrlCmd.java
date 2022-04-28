package urlShortner.bl.cmds;

import urlShortner.AppContext;
import urlShortner.bl.model.UrlInfo;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static urlShortner.bl.utils.TtlUtils.isTtlExpired;
import static urlShortner.bl.utils.UrlUtils.addParamsToUrl;

public class GetUrlCmd extends BaseCmd<String> {

    //region Members
    private final List<String>         pathParams;
    private final Map<String, String>  queryParams;
    private final Map<String, UrlInfo> urlMappingByKey;
    private       String               key;
    //endregion

    //region Constructor
    public GetUrlCmd(List<String> pathParams, Map<String, String> queryParams) {
        this.key = pathParams.get(0);
        pathParams.remove(0);

        this.pathParams = pathParams;
        this.queryParams = queryParams;
        this.urlMappingByKey = AppContext.getInstance().getUrlInfoMappedByKey();
    }
    //endregion

    //region Methods
    public void execute() {
        if (key.length() > 0) {
            if (urlMappingByKey.containsKey(key)) {
                UrlInfo urlInfo = urlMappingByKey.get(key);

                Boolean isTtlExpired = isTtlExpired(urlInfo);

                if (isTtlExpired) {
                    urlMappingByKey.remove(key);
                    throw new WebApplicationException("Key is no longer saved", Response.Status.BAD_REQUEST);
                }
                else {
                    urlInfo.setLastUsedAt(LocalDateTime.now());
                    String url           = urlInfo.getUrl();
                    String urlWithParams = addParamsToUrl(url, pathParams, queryParams);
                    setResult(urlWithParams);
                }
            }
            else {
                throw new WebApplicationException("Key does not exist", Response.Status.BAD_REQUEST);
            }
        }
        else {
            throw new WebApplicationException("An empty key was given", Response.Status.BAD_REQUEST);
        }
    }
    //endregion
}
