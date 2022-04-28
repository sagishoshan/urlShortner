package urlShortner.bl.jobs;

import io.dropwizard.jobs.Job;
import io.dropwizard.jobs.annotations.Every;
import org.quartz.JobExecutionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import urlShortner.bl.cmds.CleanExpiredUrlsCmd;


@Every("30min")
public class CleanExpiredUrlsJob extends Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(CleanExpiredUrlsJob.class);

    @Override
    public void doJob(JobExecutionContext jobExecutionContext) {
        LOGGER.info("********** Starting CleanExpiredUrlsJob **********");

        CleanExpiredUrlsCmd cmd = new CleanExpiredUrlsCmd();
        cmd.execute();

        LOGGER.info("********** Finished CleanExpiredUrlsJob **********");
    }
}
