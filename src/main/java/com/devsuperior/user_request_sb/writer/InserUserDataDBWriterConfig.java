package com.devsuperior.user_request_sb.writer;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsuperior.user_request_sb.entities.User;

@Configuration
public class InserUserDataDBWriterConfig {
	
	private static Logger logger = LoggerFactory.getLogger(InserUserDataDBWriterConfig.class);
	
	@Bean
public ItemWriter<User> inserUserDataDBWriter(@Qualifier("appDB") DataSource dataSource ){
		logger.info("[WRITER STEP] Inserting user data ...");
		
		return new JdbcBatchItemWriterBuilder<User>()
				.dataSource(dataSource)
				.sql("INSERT INTO tb_user (login, name, avatar_url) VALUES(:login, :name, :avatarUrl)")
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.build();
	}

}
