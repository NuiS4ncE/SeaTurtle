package SeaTurtle.model;

import SeaTurtle.ui.ConsoleColors;

public class Tag implements Comparable<Tag>{
    
    private String tag;
    private Integer id;
    private String type;
    private String bookId;
    
    public Tag(String type, String tag) {
        this.tag = tag;
        this.id = null;
        this.type = type;
        this.bookId = null;
    }

    public Tag(String type, String tag, String bookId) {
        this.tag = tag;
        this.id = null;
        this.type = type;
        this.bookId = bookId;
    }

    public Tag(String type, String tag, Integer id, String bookId) {
        this.tag = tag;
        this.id = id;
        this.type = type;
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

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getType() {
        return this.type;
    }    
    
    public void setType(String type) {
        this.type = type;
    }

    
    @Override
    public String toString() {
        /*
        if (this.type.equals("ARTICLE")) {
            return this.tag + " (Artikkeli " + this.bookId + ")";
        }
        //return this.tag + " (Kirja " + this.bookId + ")" + " (id  " + this.id + ")";
        */
        return this.tag + " (id  " + this.id + ")";
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
