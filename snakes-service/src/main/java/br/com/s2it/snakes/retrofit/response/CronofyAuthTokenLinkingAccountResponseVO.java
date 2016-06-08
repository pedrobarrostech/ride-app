package br.com.s2it.snakes.retrofit.response;

public class CronofyAuthTokenLinkingAccountResponseVO {
	private String provider_name;
	private String profile_id;
	private String profile_name;
	
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
		builder.append("CronofyAuthTokenLinkingAccountResponseVO [provider_name=").append(provider_name)
				.append(", profile_id=").append(profile_id).append(", profile_name=").append(profile_name).append("]");
		return builder.toString();
	}
}
