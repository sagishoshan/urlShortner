package urlShortner.api;

import urlShortner.api.requests.CreateShortUrlRequest;
import urlShortner.api.responses.CreateShortUrlResponse;
import urlShortner.bl.cmds.CreateShortUrlCmd;
import urlShortner.bl.cmds.GetUrlCmd;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class UrlShortnerResource {

    @POST
    @Path("/create")
    public void createShortUrl(@Suspended AsyncResponse asyncResponse, @Valid CreateShortUrlRequest request) {
        CreateShortUrlCmd cmd = new CreateShortUrlCmd(request);
        cmd.execute();

        CreateShortUrlResponse response = cmd.getResult();
        asyncResponse.resume(response);
    }

    @GET
    @Path("/go/{pathParams: .*}")
    public void getFullUrl(@Suspended AsyncResponse asyncResponse, @Context UriInfo uriInfo) {
        List<String> pathParams =
                uriInfo.getPathSegments().stream().skip(1).map(PathSegment::getPath).collect(Collectors.toList());

        Map<String, String> queryParams = uriInfo.getQueryParameters().entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().findFirst().orElse(null)));


        GetUrlCmd cmd = new GetUrlCmd(pathParams, queryParams);
        cmd.execute();
        String newUrl = cmd.getResult();

        try {
            URI      uri      = new URI(newUrl);
            Response response = Response.temporaryRedirect(uri).build();
            asyncResponse.resume(response);
        }
        catch (URISyntaxException e) {
            throw new WebApplicationException(e.getMessage());
        }
    }
}
