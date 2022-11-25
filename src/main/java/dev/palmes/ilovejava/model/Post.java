package dev.palmes.ilovejava.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User author;

    private String content;

    private Date creationDate;

    private boolean removed = false;

    @ManyToOne
    private Post parent;

    @ManyToOne
    private Thread thread;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    /**
     * Create a summary of the post content.
     * ie : Shorten the content to a length of maxChar
     * <ul>
     *     <li>
     *         If the content is shorter than maxChar, directly return content.
     *     </li>
     *     <li>
     *         If the content is longer than maxChar, cut at the last word before
     *         maxChar and append "..." to it.
     *     </li>
     * </ul>
     *
     * @param maxChar The max number of character wanted
     */
    public String getContentSummary(int maxChar) {
        if (this.getContent().length() < 200) {
            return this.getContent();
        }

        String[] words = this.getContent().split(" ");
        Iterator<String> wordsIt = Arrays.stream(words).iterator();
        StringBuilder content = new StringBuilder();

        int charCount = 0;
        while (wordsIt.hasNext() && charCount < maxChar) {
            String word = wordsIt.next();
            charCount += word.length();
            content.append(charCount == 0 ? "" : " ").append(word);
        }

        if (wordsIt.hasNext()) {
            content.append("...");
        }


        return content.toString();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
