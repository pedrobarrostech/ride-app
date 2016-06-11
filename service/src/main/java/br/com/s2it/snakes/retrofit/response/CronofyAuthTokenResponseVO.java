package br.com.s2it.snakes.retrofit.response;

public class CronofyAuthTokenResponseVO {
	private String access_token;
	private String token_type;
	private Long expires_in;
	private String refresh_token;
	private String scope;
	private String account_id;
	private CronofyAuthTokenLinkingAccountResponseVO linking_profile;
	
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
	public CronofyAuthTokenLinkingAccountResponseVO getLinking_profile() {
		return linking_profile;
	}
	public void setLinking_profile(CronofyAuthTokenLinkingAccountResponseVO linking_profile) {
		this.linking_profile = linking_profile;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CronofyAuthTokenResponseVO [access_token=").append(access_token).append(", token_type=")
				.append(token_type).append(", expires_in=").append(expires_in).append(", refresh_token=")
				.append(refresh_token).append(", scope=").append(scope).append(", account_id=").append(account_id)
				.append(", linking_profile=").append(linking_profile).append("]");
		return builder.toString();
	}
}
