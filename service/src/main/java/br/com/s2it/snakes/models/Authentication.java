package br.com.s2it.snakes.models;

import org.springframework.data.annotation.Id;

public class Authentication {
	
	@Id
	private String id;
	
	private String hash;
	
	private String access_token;
	private String token_type;
	private Long expires_in;
	private String refresh_token;
	private String scope;
	private String account_id;
	private String provider_name;
	private String profile_id;
	private String profile_name;
	
	public Authentication() {		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}

	public String getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}

	public String getProfile_name() {
		return profile_name;
	}

	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Authentication [id=").append(id).append(", hash=").append(hash).append(", access_token=")
				.append(access_token).append(", token_type=").append(token_type).append(", expires_in=")
				.append(expires_in).append(", refresh_token=").append(refresh_token).append(", scope=").append(scope)
				.append(", account_id=").append(account_id).append(", provider_name=").append(provider_name)
				.append(", profile_id=").append(profile_id).append(", profile_name=").append(profile_name).append("]");
		return builder.toString();
	}	
}
