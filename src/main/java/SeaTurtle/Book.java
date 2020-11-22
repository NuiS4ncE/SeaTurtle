package SeaTurtle;

public class Book implements Comparable<Book>{
    
    private String title;
    private String author;
    private Integer pageCount;
    
    public Book(String title) {
        this.title = title;
        this.author = null;
        this.pageCount = null;
    }
    
    public Book(String title, String author, int pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public Integer getPageCount() {
        return pageCount;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    
    @Override
    public String toString() {
        StringBuilder bookDetails = new StringBuilder("Kirjan nimi: " + title + ".");
        if (author != null) {
            bookDetails.append(" Kirjoittaja: " + author + ".");
        }if (pageCount != null) {
            bookDetails.append(" "+ pageCount + " sivua.");
        }
        return bookDetails.toString();
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.getTitle());
    }
}
