package br.com.s2it.snakes.models;

public class CarDamage {
	private String left;
	private String top;
	private String text;
	private String description;
	private Boolean active;
	
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CarDamage [left=").append(left).append(", top=").append(top)
				.append(", text=").append(text).append(", description=").append(description).append(", active=")
				.append(active).append("]");
		return builder.toString();
	}
}
