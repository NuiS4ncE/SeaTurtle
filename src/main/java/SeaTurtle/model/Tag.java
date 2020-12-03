package SeaTurtle.model;

import SeaTurtle.ui.ConsoleColors;

public class Tag implements Comparable<Tag>{
    
    private String tag;
    private Integer id;
    private String bookId;
    
    public Tag(String tag) {
        this.tag = tag;
        this.id = null;
        this.bookId = null;
    }

    public Tag(String tag, String bookId) {
        this.tag = tag;
        this.id = null;
        this.bookId = bookId;
    }

    public Tag(String tag, Integer id, String bookId) {
        this.tag = tag;
        this.id = id;
        this.bookId = bookId;
    }

        
    public String getTag() {
        return this.tag;
    }    
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String s) {
        this.bookId = s;
    }

    public Integer getId() {
        return this.id;
    }

    
    @Override
    public String toString() {
        return this.tag + " " + this.bookId;
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
