package com.zsj.filetodbdemo.assetsfiles;

public class AssetsFileInfo {
	// 索引
	private int id = 0;
	/**
	 * 文件名称
	 */
	private String fileName = null;
	/**
	 * 文件内容
	 */
	private byte[] fileBuffer = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileBuffer() {
		return fileBuffer;
	}

	public void setFileBuffer(byte[] fileBuffer) {
		this.fileBuffer = fileBuffer;
	}

	public AssetsFileInfo(String fileName, byte[] fileBuffer) {
		super();
		this.fileName = fileName;
		this.fileBuffer = fileBuffer;
	}

	public AssetsFileInfo() {
	}

}
