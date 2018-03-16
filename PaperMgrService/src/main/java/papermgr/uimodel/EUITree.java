package papermgr.uimodel;

import java.util.List;

public class EUITree {
	private String id;
	private String text;
	private String state;
	private List<EUITree> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<EUITree> getChildren() {
		return children;
	}

	public void setChildren(List<EUITree> children) {
		this.children = children;
	}
}
