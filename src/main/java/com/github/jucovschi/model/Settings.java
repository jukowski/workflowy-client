package com.github.jucovschi.model;

public class Settings {
	String username;
	String theme;
	String last_seen_message_json_string;
	String saved_views_json;
	String unsubscribe_from_summary_emails;
	String font;
	String backup_to_dropbox;
	String email;
	String show_keyboard_shortcuts;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getLast_seen_message_json_string() {
		return last_seen_message_json_string;
	}
	public void setLast_seen_message_json_string(
			String last_seen_message_json_string) {
		this.last_seen_message_json_string = last_seen_message_json_string;
	}
	public String getSaved_views_json() {
		return saved_views_json;
	}
	public void setSaved_views_json(String saved_views_json) {
		this.saved_views_json = saved_views_json;
	}
	public String getUnsubscribe_from_summary_emails() {
		return unsubscribe_from_summary_emails;
	}
	public void setUnsubscribe_from_summary_emails(
			String unsubscribe_from_summary_emails) {
		this.unsubscribe_from_summary_emails = unsubscribe_from_summary_emails;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public String getBackup_to_dropbox() {
		return backup_to_dropbox;
	}
	public void setBackup_to_dropbox(String backup_to_dropbox) {
		this.backup_to_dropbox = backup_to_dropbox;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShow_keyboard_shortcuts() {
		return show_keyboard_shortcuts;
	}
	public void setShow_keyboard_shortcuts(String show_keyboard_shortcuts) {
		this.show_keyboard_shortcuts = show_keyboard_shortcuts;
	}
}
