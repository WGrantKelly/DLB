package AutoFill;

import java.util.ArrayList;

public class DlbElement{
    private String key;
    private DlbElement parent;
    private ArrayList<DlbElement> children = new ArrayList<DlbElement>();
    public DlbElement(String key) {
        this.key = key;
    }

    public void setParent(DlbElement parent) {
        this.parent = parent;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setChildren(ArrayList<DlbElement> children) {
        this.children = children;
    }

    public String getKey() {
        return key;
    }

    public DlbElement getParent() {
        return parent;
    }

    public ArrayList<DlbElement> getChildren() {
        return children;
    }
    public void addchild(DlbElement element)
    {
        children.add(element);
    }
    public boolean hasChildNodes()
    {
        if(getChildren().size()>0)
        {
            return true;
        }
        return false;
    }
}