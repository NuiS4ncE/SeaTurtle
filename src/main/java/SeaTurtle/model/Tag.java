package SeaTurtle.model;

import SeaTurtle.ui.ConsoleColors;

public class Tag implements Comparable<Tag>{
    
    private String tag;
    private Integer id;
    
    public Tag(String tag) {
        this.tag = tag;
        this.id = null;
    }

    public Tag(String tag, Integer id) {
        this.tag = tag;
        this.id = id;
    }
        
    public String getTag() {
        return this.tag;
    }    
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    
    @Override
    public String toString() {
        return this.tag;
    }


    @Override
    public int compareTo(Tag other) {
        return this.tag.compareTo(other.getTag());
    }

    @Override
    public int hashCode() {
        return this.tag.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag)) {
            return false;
        }

        Tag other = (Tag) obj;         
        return this.toString().equals(other.toString());
        
    }
}
