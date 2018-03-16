package papermgr.uimodel;

import java.util.List;

public class EUIPageList<T>{
	private long total;
	private List<T> rows;
	
	public EUIPageList(long total,List<T> rows){
		this.total=total;
		this.rows=rows;
	}
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
