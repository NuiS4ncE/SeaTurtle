package SeaTurtle;

public class Book implements Comparable<Book>{
    
    private String title;
    private String author;
    private String pageCount; 
    //Laitoin sivumäärän Stringinä, koska en keksinyt miten sen saisi muuten tyhjäksi.
    //Jos on ideoita, tätä voi muuttaa! 
    
    public Book(String title) {
        this.title = title;
        author = "-";
        pageCount = "-";
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getPageCount() {
        return pageCount;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }
    
    @Override
    public String toString() {
        return "Kirjan nimi: " + title + ", kirjoittaja: " + author + ", sivumäärä: " + pageCount;
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.getTitle());
    }
}
