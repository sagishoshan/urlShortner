package urlShortner.bl.utils;

import org.apache.commons.validator.routines.UrlValidator;

import java.util.List;
import java.util.Map;

public class UrlUtils {

    private static String[] SCHEMAS = {"http", "https"};

    public static String addParamsToUrl(String url, List<String> pathParams, Map<String, String> queryParams) {
        String retVal = url;

        if (pathParams.size() > 0) {
            retVal = retVal + "/" + String.join("/", pathParams);
        }

        if (queryParams.size() > 0) {
            retVal = retVal + "?";

            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                retVal = retVal + entry.getKey() + "=" + entry.getValue();
            }
        }

        return retVal;
    }

    public static Boolean isUrlValid(String url) {
        Boolean      retVal       = false;
        UrlValidator urlValidator = new UrlValidator(SCHEMAS);

        if (urlValidator.isValid(url)) {
            retVal = true;
        }

        return retVal;
    }
}
