package hr.algebra.tvtime.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail showsPrintJobDetail() {
        return JobBuilder.newJob(ShowsPrintJob.class).withIdentity("showPrintJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger showsPrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();

        return TriggerBuilder.newTrigger().forJob(showsPrintJobDetail())
                .withIdentity("showPrintTrigger").withSchedule(scheduleBuilder).build();
    }

}

