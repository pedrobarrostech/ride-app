package br.com.s2it.snakes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.s2it.snakes.models.Authentication;
import br.com.s2it.snakes.repositories.AuthenticationRepository;
import br.com.s2it.snakes.retrofit.response.CronofyAuthTokenResponseVO;
import br.com.s2it.snakes.translators.AuthenticationTranslate;

@Service
public class AuthenticationService {
	
	@Autowired
	private AuthenticationRepository repository;
	
	public void saveByCronofyAuthTokenVO(String hash, CronofyAuthTokenResponseVO vo) {
		deleteByHashIfExist(hash);
		
		Authentication auth = AuthenticationTranslate.toEntity(hash, vo);
		repository.save(auth);
	}
	
	public void deleteByHashIfExist(String hash) {
		Authentication auth = repository.findByHash(hash);
		if(auth != null && auth.getId() != null) 
			repository.delete(auth);
	}
}
