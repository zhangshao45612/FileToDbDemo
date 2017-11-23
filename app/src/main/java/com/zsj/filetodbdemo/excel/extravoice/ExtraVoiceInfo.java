package com.zsj.filetodbdemo.excel.extravoice;

public class ExtraVoiceInfo {
	private int id;
	/**
	 * 单词内容
	 */
	private String content;
	/**
	 * 音标
	 */
	private String phonetic;
	/**
	 * 词性
	 */
	private String property;
	/**
	 * 释义
	 */
	private String paraphrase;
	/**
	 * 美式发音名称
	 */
	private String usVoiceName;
	/**
	 * 英式发音名称
	 */
	private String ukVoiceName;

	public ExtraVoiceInfo() {
	}

	public ExtraVoiceInfo(int id, String content, String phonetic, String property, String paraphrase,
			String usVoiceName, String ukVoiceName) {
		super();
		this.id = id;
		this.content = content;
		this.phonetic = phonetic;
		this.property = property;
		this.paraphrase = paraphrase;
		this.usVoiceName = usVoiceName;
		this.ukVoiceName = ukVoiceName;
	}

	public ExtraVoiceInfo(String content, String phonetic, String property, String paraphrase, String usVoiceName,
			String ukVoiceName) {
		super();
		this.content = content;
		this.phonetic = phonetic;
		this.property = property;
		this.paraphrase = paraphrase;
		this.usVoiceName = usVoiceName;
		this.ukVoiceName = ukVoiceName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhonetic() {
		return phonetic;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getParaphrase() {
		return paraphrase;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

	public String getUsVoiceName() {
		return usVoiceName;
	}

	public void setUsVoiceName(String usVoiceName) {
		this.usVoiceName = usVoiceName;
	}

	public String getUkVoiceName() {
		return ukVoiceName;
	}

	public void setUkVoiceName(String ukVoiceName) {
		this.ukVoiceName = ukVoiceName;
	}

	@Override
	public String toString() {
		return "content:" + content + "   phonetic:" + phonetic + "   property:" + property + "   paraphrase:"
				+ paraphrase + "   usVoiceName:" + usVoiceName + "   ukVoiceName:" + ukVoiceName;
	}

}
