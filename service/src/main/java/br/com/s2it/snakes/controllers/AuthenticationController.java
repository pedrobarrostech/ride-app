package br.com.s2it.snakes.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.s2it.snakes.retrofit.CronofyAuthTokenVO;
import br.com.s2it.snakes.retrofit.CronofyRetrofit;
import br.com.s2it.snakes.retrofit.response.CronofyAuthTokenResponseVO;
import br.com.s2it.snakes.services.AuthenticationService;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	@Qualifier("retrofitCronofyAPI")
	private RestAdapter cronofyAPI;
	
	@Value("${snakes.client.id}")
	private String clientId;
	
	@Value("${snakes.client.secret}")
	private String clientSecret;	
	
	@Value("${authentication.authorization.redirect_url}")
	private String authorizationRedirectURL;
	
	@Autowired
	private AuthenticationService service;
	
	@RequestMapping("/response")
	public void authorizationResponse(final @RequestParam("code") String code, final @RequestParam("state") String state) {
		CronofyAuthTokenVO vo = new CronofyAuthTokenVO();
		vo.setClient_id(clientId);
		vo.setClient_secret(clientSecret);
		vo.setCode(code);
		vo.setRedirect_uri(authorizationRedirectURL);
		
		try {
			CronofyRetrofit retrofit = cronofyAPI.create(CronofyRetrofit.class);
			CronofyAuthTokenResponseVO result = retrofit.requestAccessToken(vo);

			service.saveByCronofyAuthTokenVO(state, result);
		} catch(RetrofitError e) {
			e.printStackTrace();
		}
	}
}
