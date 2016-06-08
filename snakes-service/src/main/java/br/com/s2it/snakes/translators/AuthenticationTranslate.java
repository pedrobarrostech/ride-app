package br.com.s2it.snakes.translators;

import br.com.s2it.snakes.models.Authentication;
import br.com.s2it.snakes.retrofit.response.CronofyAuthTokenResponseVO;

public class AuthenticationTranslate {
	public static Authentication toEntity(String hash, CronofyAuthTokenResponseVO vo) {
		Authentication auth = new Authentication();
		auth.setAccess_token(vo.getAccess_token());
		auth.setAccount_id(vo.getAccount_id());
		auth.setExpires_in(vo.getExpires_in());
		auth.setHash(hash);
		auth.setProfile_id(vo.getLinking_profile().getProfile_id());
		auth.setProfile_name(vo.getLinking_profile().getProfile_name());
		auth.setProvider_name(vo.getLinking_profile().getProvider_name());
		auth.setRefresh_token(vo.getRefresh_token());
		auth.setScope(vo.getScope());
		auth.setToken_type(vo.getToken_type());
		
		return auth;
	}
}
