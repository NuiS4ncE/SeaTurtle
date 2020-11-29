
package SeaTurtle.model;

public class Article implements Comparable<Article> {
    
    private String title;
    private String url;
    
    public Article(String title) {
        this.title = title;
        this.url = null;
    }
    
    public Article(String title, String link) {
        this.title = title;
        this.url = link;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl (String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        StringBuilder articleDetails = new StringBuilder("Artikkeli: " + title + ".");
        if (url != null) {
            articleDetails.append(" <" + url + ">");
        }
        return articleDetails.toString();
    }

    @Override
    public int compareTo(Article other) {
        return this.title.compareTo(other.getTitle());
    }

    @Override
    public int hashCode() {
        int result = 6;
        result += this.title.hashCode();
        result += this.url.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Article)) {
            return false;
        }

        Article other = (Article) obj;
        return this.toString().equals(other.toString());
    }
    
}
