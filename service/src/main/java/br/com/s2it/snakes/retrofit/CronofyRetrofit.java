package br.com.s2it.snakes.retrofit;

import br.com.s2it.snakes.retrofit.response.CronofyAuthTokenResponseVO;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface CronofyRetrofit {
	@Headers("Content-Type: application/json; charset=utf-8")
	@POST("/oauth/token")	
	public CronofyAuthTokenResponseVO requestAccessToken(@Body CronofyAuthTokenVO authVO);
}
