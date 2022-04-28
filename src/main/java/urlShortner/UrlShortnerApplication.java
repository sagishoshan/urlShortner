package urlShortner;

import io.dropwizard.Application;
import io.dropwizard.jobs.JobsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import urlShortner.api.UrlShortnerResource;
import urlShortner.bl.jobs.CleanExpiredUrlsJob;
import urlShortner.bl.model.UrlInfo;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UrlShortnerApplication extends Application<UrlShortnerConfiguration> {

    //region Public Methods
    public static void main(final String[] args) throws Exception {
        new UrlShortnerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<UrlShortnerConfiguration> bootstrap) {
        setupJobs(bootstrap);
    }

    @Override
    public String getName() {
        return "urlShortner";
    }

    @Override
    public void run(final UrlShortnerConfiguration configuration, final Environment environment) {
        initResources(environment);
        initAppContext(configuration);
    }
    //endregion

    //region Private Methods
    private void setupJobs(Bootstrap bootstrap) {
        CleanExpiredUrlsJob job        = new CleanExpiredUrlsJob();
        JobsBundle          jobsBundle = new JobsBundle(job);
        bootstrap.addBundle(jobsBundle);
    }

    private void initResources(Environment environment) {
        environment.healthChecks().register("default", new DefaultHealthCheck());
        environment.jersey().register(new UrlShortnerResource());
    }

    private void initAppContext(UrlShortnerConfiguration configuration) {
        initUrlbyKeyMap();
        initHashAltorithm(configuration);
        initShortUrlLength(configuration);
    }

    private void initUrlbyKeyMap() {
        Map<String, UrlInfo> urlMappingByKey = new ConcurrentHashMap<>();
        AppContext.getInstance().setUrlInfoMappedByKey(urlMappingByKey);
    }

    private void initHashAltorithm(UrlShortnerConfiguration configuration) {
        String hashingAlgorithm = configuration.getHashAlgorithm();
        try {
            MessageDigest messageDigestAlgorithm = MessageDigest.getInstance(hashingAlgorithm);
            AppContext.getInstance().setMessageDigest(messageDigestAlgorithm);
        }
        catch (NoSuchAlgorithmException e) {
            throw new WebApplicationException(String.format("Algorithm %s not found.", hashingAlgorithm),
                                              Response.Status.NOT_FOUND);
        }
    }

    private void initShortUrlLength(UrlShortnerConfiguration configuration) {
        AppContext.getInstance().setShortUrlLength(configuration.getShortUrlLength());
    }
    //endregion
}
