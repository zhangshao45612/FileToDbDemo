package com.zsj.filetodbdemo.excel.vocabulary;

public class VocabularyInfo {
	private int id;
	/** 教材名称 **/
	private String bookName;
	/** 起始页 **/
	private int startPage;
	/** 结束页 **/
	private int endPage;
	/** 当前第几单元 **/
	private int curUnit;
	/** 单元名称 **/
	private String curUnitName;
	/** 每个单元有几个单词（包含单元名称） **/
	private int wordNums;

	public VocabularyInfo(int id, String bookName, int startPage, int endPage, int curUnit, String curUnitName, int wordNums) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.startPage = startPage;
		this.endPage = endPage;
		this.curUnit = curUnit;
		this.curUnitName = curUnitName;
		this.wordNums = wordNums;
	}

	public VocabularyInfo(String bookName, int startPage, int endPage, int curUnit, String curUnitName, int wordNums) {
		super();
		this.bookName = bookName;
		this.startPage = startPage;
		this.endPage = endPage;
		this.curUnit = curUnit;
		this.curUnitName = curUnitName;
		this.wordNums = wordNums;
	}

	
	public VocabularyInfo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getCurUnit() {
		return curUnit;
	}

	public void setCurUnit(int curUnit) {
		this.curUnit = curUnit;
	}

	public String getCurUnitName() {
		return curUnitName;
	}

	public void setCurUnitName(String curUnitName) {
		this.curUnitName = curUnitName;
	}

	public int getWordNums() {
		return wordNums;
	}

	public void setWordNums(int wordNums) {
		this.wordNums = wordNums;
	}

}
