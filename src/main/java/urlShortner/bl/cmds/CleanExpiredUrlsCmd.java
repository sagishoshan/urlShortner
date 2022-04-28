package urlShortner.bl.cmds;

import urlShortner.AppContext;
import urlShortner.bl.model.UrlInfo;

import java.util.Map;

import static urlShortner.bl.utils.TtlUtils.isTtlExpired;

public class CleanExpiredUrlsCmd extends BaseCmd<Boolean> {

    public void execute() {
        Map<String, UrlInfo> urlMappingByKey = AppContext.getInstance().getUrlInfoMappedByKey();

        for (Map.Entry<String, UrlInfo> entry : urlMappingByKey.entrySet()) {
            String  hashedKey = entry.getKey();
            UrlInfo urlInfo   = entry.getValue();

            Boolean isTtlExpired = isTtlExpired(urlInfo);

            if (isTtlExpired) {
                urlMappingByKey.remove(hashedKey);
            }
        }

        setResult(true);
    }
}
