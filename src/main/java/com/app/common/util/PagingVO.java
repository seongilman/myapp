package com.app.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * PagingVO
 * 페이징
 * @author seongilman
 * @create 2017. 05. 09
 */
public class PagingVO implements Serializable {

	private static final long serialVersionUID = 1952275543463278952L;

	/** 현재 페이지 */
	private int curPage;
	/** 이전 페이지 */
	private int curPrevPage;
	/** 다음 페이지 */
	private int curNextPage;
	
	private int scale;
	/** 총 페이지의 마지막 */
	private int totalLastPage;
	/** 현재 페이지 그룹내 마지막 페이지 */
	private int viewLastPage;
	/** 현재 페이지 그룹내 첫 페이지 */
	private int viewFirstPage;
	/** 다음 페이지 그룹 */
	private int nextPage;
	/** 이전 페이지 그룹 */
	private int prePage;
	/** 게시물 수 */
	private int totalCount;
	/** 페이지 리스트 */
	private List<Integer> pageList;

    /**
     * Instantiates a new Paging.
     */
    public PagingVO() {
	}

    /**
     * Instantiates a new Paging.
     *
     * @param curPage the cur page
     * @param totalCount the total count
     * @param listScale the list scale
     * @param pageScale the page scale
     */
    public PagingVO(int curPage, int totalCount, int listScale, int pageScale) {
		this.curPage = curPage;
		this.setTotalCount(totalCount);
		this.setScale(listScale);
		if (totalCount <= listScale) {
			this.totalLastPage = 1;
		} else {
			this.totalLastPage = (int) Math.ceil((double) totalCount
					/ listScale);
		}

		if (this.totalLastPage < this.curPage) {
			this.curPage = 1;
		}

		this.prePage = (this.curPage - 1) / pageScale * pageScale;

		if (this.prePage == 0) {
			this.prePage = 1;
		}
		
		if(this.curPage -1 == 0){
			this.curPrevPage = 1;
		}else{
			this.curPrevPage = this.curPage -1;
		}

		this.viewLastPage = ((this.curPage - 1) / pageScale + 1) * pageScale;
		this.viewFirstPage = viewLastPage - (pageScale - 1);

		if (this.totalLastPage <= pageScale) {
			this.viewLastPage = this.totalLastPage;
//			this.nextPage = this.viewLastPage + 1;
			this.nextPage = this.viewLastPage;
		} else {
			if (this.viewLastPage > this.totalLastPage) {
				this.viewLastPage = this.totalLastPage;
				this.nextPage = 0;
			} else {
//				this.nextPage = this.viewLastPage + 1;
				this.nextPage = this.viewLastPage;
			}
		}
		
		if(this.curPage +1 > this.totalLastPage){
			this.curNextPage = this.totalLastPage;
		}else{
			this.curNextPage = this.curPage+1;
		}

		List<Integer> page = new ArrayList<Integer>();

		for (int i = viewFirstPage; i <= viewLastPage; i++) {
			page.add(i);
		}
		this.setPageList(page);
	}

    /**
     * Gets cur page.
     *
     * @return the cur page
     */
    public int getCurPage() {
		return curPage;
	}

    /**
     * Sets cur page.
     *
     * @param curPage the cur page
     */
    public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

    /**
     * Gets total last page.
     *
     * @return the total last page
     */
    public int getTotalLastPage() {
		return totalLastPage;
	}

    /**
     * Sets total last page.
     *
     * @param totalLastPage the total last page
     */
    public void setTotalLastPage(int totalLastPage) {
		this.totalLastPage = totalLastPage;
	}

    /**
     * Gets view last page.
     *
     * @return the view last page
     */
    public int getViewLastPage() {
		return viewLastPage;
	}

    /**
     * Sets view last page.
     *
     * @param viewLastPage the view last page
     */
    public void setViewLastPage(int viewLastPage) {
		this.viewLastPage = viewLastPage;
	}

    /**
     * Gets next page.
     *
     * @return the next page
     */
    public int getNextPage() {
		return nextPage;
	}

    /**
     * Sets next page.
     *
     * @param nextPage the next page
     */
    public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

    /**
     * Gets pre page.
     *
     * @return the pre page
     */
    public int getPrePage() {
		return prePage;
	}

    /**
     * Sets pre page.
     *
     * @param prePage the pre page
     */
    public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

    /**
     * Sets page list.
     *
     * @param pageList the page list
     */
    public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

    /**
     * Gets page list.
     *
     * @return the page list
     */
    public List<Integer> getPageList() {
		return pageList;
	}

    /**
     * Sets scale.
     *
     * @param scale the scale
     */
    public void setScale(int scale) {
		this.scale = scale;
	}

    /**
     * Gets scale.
     *
     * @return the scale
     */
    public int getScale() {
		return scale;
	}

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public int getTotalCount() {
		return totalCount;
	}

    /**
     * Gets view first page.
     *
     * @return the view first page
     */
    public int getViewFirstPage() {
		return viewFirstPage;
	}

    /**
     * Sets view first page.
     *
     * @param viewFirstPage the view first page
     */
    public void setViewFirstPage(int viewFirstPage) {
		this.viewFirstPage = viewFirstPage;
	}

    /**
     * Gets cur prev page.
     *
     * @return the cur prev page
     */
    public int getCurPrevPage() {
		return curPrevPage;
	}

    /**
     * Sets cur prev page.
     *
     * @param curPrePage the cur pre page
     */
    public void setCurPrevPage(int curPrePage) {
		this.curPrevPage = curPrePage;
	}

    /**
     * Gets cur next page.
     *
     * @return the cur next page
     */
    public int getCurNextPage() {
		return curNextPage;
	}

    /**
     * Sets cur next page.
     *
     * @param curNextPage the cur next page
     */
    public void setCurNextPage(int curNextPage) {
		this.curNextPage = curNextPage;
	}
	
	

}