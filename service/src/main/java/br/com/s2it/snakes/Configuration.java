package br.com.s2it.snakes;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Value("${retrofit.cronofyapi.baseurl}")
	private String cronofyAPIBaseURL;
	
	@Value("${spring.mail.host}")
	private String emailHost;
	
	@Value("${spring.mail.port}")
	private String emailPost;
	
	@Value("${spring.mail.user}")
	private String emailUser;
	
	@Value("${spring.mail.password}")
	private String emailPasswd;
		
	@Bean(name="retrofitCronofyAPI")
	public RestAdapter cronofyAPI() {
		return new RestAdapter.Builder().setEndpoint(cronofyAPIBaseURL).setLogLevel(LogLevel.FULL).build();
	}
	
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailHost);
		mailSender.setPort(Integer.valueOf(emailPost));
		mailSender.setUsername(emailUser);
		mailSender.setPassword(emailPasswd);
		
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		
		return mailSender;
	}
}
