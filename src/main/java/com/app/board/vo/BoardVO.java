package com.app.board.vo;

import com.app.common.util.ListObject;

public class BoardVO extends ListObject{
	private int rnum;					// rownum
	private int no;					// 글번호
	private String category;		// 카테고리
	private String title;				// 타이틀
	private String content;			// 글내용
	private int family;				// 부모글 그룹(no와 동일)
	private int parent;				// 부모글번호
	private int depth;				// 글정렬순서
	private int indent;				// 들여쓰기
	private int hits;					// 조회수
	private String writer;			// 작성자 아이디
	private String regDt;			// 등록일
	private String modDt;			// 수정일
	private String nickname;		// 작성자 닉네임
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFamily() {
		return family;
	}
	public void setFamily(int family) {
		this.family = family;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "BoardVO [rnum=" + rnum + ", no=" + no + ", category="
				+ category + ", title=" + title + ", content=" + content
				+ ", family=" + family + ", parent=" + parent + ", depth="
				+ depth + ", indent=" + indent + ", hits=" + hits + ", writer="
				+ writer + ", regDt=" + regDt + ", modDt=" + modDt
				+ ", nickname=" + nickname + "]";
	}
}
