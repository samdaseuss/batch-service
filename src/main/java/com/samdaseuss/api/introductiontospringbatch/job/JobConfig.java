package com.samdaseuss.api.introductiontospringbatch.job;

import com.samdaseuss.api.introductiontospringbatch.core.domain.PlainText;
import com.samdaseuss.api.introductiontospringbatch.core.domain.ResultText;
import com.samdaseuss.api.introductiontospringbatch.core.repository.PlainTextRepository;
import com.samdaseuss.api.introductiontospringbatch.core.repository.ResultTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PlainTextRepository plainTextRepository;
    private final ResultTextRepository resultTextRepository;

    @Bean("batchJob")
    public Job batchJob(Step batchStep) {
        return jobBuilderFactory
                .get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(batchStep)
                .build();
    }

    @JobScope
    @Bean("batchStep")
    public Step batchStep(
            ItemReader batchReader,
            ItemProcessor plainTextProcessor,
            ItemWriter plainTextWriter
    ){
        return stepBuilderFactory
                .get("batchStep")
                .<PlainText, String>chunk(5)
                .reader(batchReader)
                .processor(plainTextProcessor)
                .writer(plainTextWriter)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<PlainText> batchReader() {
        return new RepositoryItemReaderBuilder<PlainText>()
                .name("plainTextReader")
                .repository(plainTextRepository)
                .methodName("findBy")
                .pageSize(5)
                .arguments(List.of())
                .sorts(Collections.singletonMap("id", Sort.Direction.DESC))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<PlainText, String> plainTextProcessor() {
        return item -> "processed " + item.getText();
    }

    @StepScope
    @Bean
    public ItemWriter<String> plainTextWriter() {
        return items -> {
            items.forEach(item -> resultTextRepository.save(new ResultText(null, item)));
            System.out.println("=== chunk is finished");
        };
    }
}
