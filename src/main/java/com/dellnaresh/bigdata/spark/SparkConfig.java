package com.dellnaresh.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by nmiriyal on 17/06/2016.
 */
@Configuration
public class SparkConfig {
    @Bean
    JavaSparkContext sparkContext(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("BabyNames App");
        sparkConf.setMaster("local[4]");
        return new JavaSparkContext(sparkConf);
    }
}
