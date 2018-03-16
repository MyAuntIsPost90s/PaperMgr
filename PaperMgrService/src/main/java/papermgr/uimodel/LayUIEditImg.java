package papermgr.uimodel;

public class LayUIEditImg {
	private String src;
	private String title;
	
	public LayUIEditImg(String src){
		this.src=src;
	}
	
	public LayUIEditImg(String src,String title){
		this.src=src;
		this.title=title;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
