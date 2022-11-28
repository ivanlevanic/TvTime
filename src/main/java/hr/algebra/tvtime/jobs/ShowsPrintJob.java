package hr.algebra.tvtime.jobs;

import hr.algebra.tvtime.domain.TvShowData;
import hr.algebra.tvtime.repository.JpaTvShowRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ShowsPrintJob extends QuartzJobBean{

    private final Logger log = LoggerFactory.getLogger(ShowsPrintJob.class);

    private final JpaTvShowRepository jpaTvShowRepository;

    public ShowsPrintJob(JpaTvShowRepository jpaTvShowRepository) {
        this.jpaTvShowRepository = jpaTvShowRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Iterable<TvShowData> products = jpaTvShowRepository.findAll();

        if(products.iterator().hasNext()) {
            log.info("These are all available shows:");
            products.forEach(it ->
                    log.info(it.getName() + ", " + it.getPrice() + " EUR, " + it.getStreamingservice() + " (" + it.getSeasons() + " seasons)")
            );
        } else {
            log.info("There are no shows currently in the app");
        }
    }

}
