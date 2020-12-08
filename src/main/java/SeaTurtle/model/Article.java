
package SeaTurtle.model;

import SeaTurtle.ui.ConsoleColors;
import org.apache.commons.validator.UrlValidator;

public class Article implements Comparable<Article> {
    
    private String title;
    private String url;
    private String[] schemes = {"http","https"};
    private UrlValidator urlValidator = new UrlValidator(schemes);
    
    public Article(String title) {
        this.title = title;
        this.url = null;
    }
    
    public Article(String title, String url) {
        this.title = title;
        this.url = url;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl (String url) {
        if (!urlValidator.isValid(url.trim())) {
            throw new IllegalArgumentException("URL-osoite oli virheellinen");
        }
        this.url = url;
    }
    
    @Override
    public String toString() {
        StringBuilder articleDetails = new StringBuilder("Artikkeli: " + ConsoleColors.YELLOW + title + ConsoleColors.RESET + ".");
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
        if (this.url != null) {
            result += this.url.hashCode();
        }

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
