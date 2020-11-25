package SeaTurtle;

import SeaTurtle.ui.ConsoleColors;

public class Book implements Comparable<Book>{
    
    private String title;
    private String author;
    private String pageCount;
    private String bookmark;
    
    public Book(String title) {
        this.title = title;
        this.author = null;
        this.pageCount = null;
        this.bookmark = null;
    }
    
    public Book(String title, String author, String pageCount) {
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
    
    public String getPageCount() {
        return pageCount;
    }
    
    public String getBookmark() {
        return bookmark;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }
    
    public void setBookmark (String bookmark) {
        this.bookmark = bookmark;
    }
    
    @Override
    public String toString() {
        StringBuilder bookDetails = new StringBuilder("Kirjan nimi: " + title + ".");
        if (author != null) {
            bookDetails.append(" Kirjoittaja: " + author + ".");
        }if (pageCount != null ) {
            bookDetails.append(" "+ pageCount + " sivua.");
        }if (bookmark != null) {
            bookDetails.append(" Kirjanmerkki sivulla " + bookmark + ".");
            int percentageRead = (int) Math.round(Integer.parseInt(bookmark) * 100.0/Integer.parseInt(pageCount));
            bookDetails.append(" Kirjasta luettu ");
            if(percentageRead == 0) {
                bookDetails.append(ConsoleColors.RED + "0 %." + ConsoleColors.RESET);
            } else if (percentageRead == 100) {
                bookDetails.append(ConsoleColors.GREEN + "100 %." + ConsoleColors.RESET);
            } else {
                bookDetails.append(ConsoleColors.YELLOW + percentageRead + " %." + ConsoleColors.RESET);
            }
        }
        return bookDetails.toString();
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.getTitle());
    }
}
