package SeaTurtle.model;

import SeaTurtle.ui.ConsoleColors;

public class Book implements Comparable<Book>{
    
    private String title;
    private String author;
    private String pageCount;
    private String bookmark;
    private Integer id;
    
    public Book(String title) {
        this.title = title;
        this.author = null;
        this.pageCount = null;
        this.bookmark = null;
        this.id = null;
    }
    
    public Book(String title, String author, String pageCount, String bookmark, Integer id) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.bookmark = bookmark;
        this.id = id;
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
    
    public String getBookmark() {
        return bookmark;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }
    
    public void setBookmark (String bookmark) {
        if(pageCount != null) {
            this.bookmark = bookmark;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder bookDetails = new StringBuilder("Kirjan nimi: " + ConsoleColors.BLUE + title + ConsoleColors.RESET + ".");
        if (author != null) {
            bookDetails.append(" Kirjoittaja: " + author + ".");
        }if (pageCount != null ) {
            bookDetails.append(" "+ pageCount + " sivua.");
            if (bookmark != null) {
                bookDetails.append(" Kirjanmerkki sivulla " + bookmark + ".");
                int percentageRead = (int) Math.round(Integer.parseInt(bookmark) * 100.0/Integer.parseInt(pageCount));
                bookDetails.append(" Kirjasta luettu ");
                if (percentageRead == 100) {
                    bookDetails.append(ConsoleColors.GREEN + "100 %" + ConsoleColors.RESET + ".");
                } else {
                    bookDetails.append(ConsoleColors.YELLOW + percentageRead + " %" + ConsoleColors.RESET + ".");
                }
            } else {
                bookDetails.append(" Kirjasta luettu " + ConsoleColors.RED + "0 %" + ConsoleColors.RESET + ".");
            }
        }
        return bookDetails.toString();
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.getTitle());
    }

    @Override
    public int hashCode() {
        int result = 6;
        result += this.title.hashCode();
        if (this.author != null) {
            result += this.author.hashCode();
        }
        if (this.pageCount != null) {
            result += this.pageCount.hashCode();
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }

        Book other = (Book) obj;         
        return this.toString().equals(other.toString());
        
    }
}
