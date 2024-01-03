package com.samdaseuss.api.introductiontospringbatch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean("batchJob")
    public Job batchJob(Step batchStep) {
        return jobBuilderFactory
                .get("batchJob")
                .incrementer(new RunIdIncrementer()) // 잡을 실행할 때 횟수를
                .start(batchStep)
                .build();
    }

    @JobScope
    @Bean("batchStep")
    public Step batchStep(Tasklet tasklet) {
        return stepBuilderFactory
                .get("batchStep")
                .tasklet(tasklet)
                .build();
    }

    @StepScope
    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("batch test");
            return RepeatStatus.FINISHED;
        };
    }
}
