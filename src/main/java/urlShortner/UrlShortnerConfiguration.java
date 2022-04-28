package urlShortner;

import io.dropwizard.Configuration;
import io.dropwizard.jobs.JobConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class UrlShortnerConfiguration extends Configuration implements JobConfiguration {

    //region Members
    @NotNull
    @Valid
    private String hashAlgorithm;

    @NotNull
    @Valid
    @Max(32)
    private Integer shortUrlLength;
    //endregion

    //region Getters & Setters
    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public Integer getShortUrlLength() {
        return shortUrlLength;
    }

    public void setShortUrlLength(Integer shortUrlLength) {
        this.shortUrlLength = shortUrlLength;
    }

    //endregion
}
