package dev.palmes.ilovejava.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<Integer> i = new AtomicReference<>(0);
        return (
                this.getContent().length() <= maxChar ?
                        this.getContent() :
                        String.join(" ", Arrays.stream(this.getContent().split(" ")).filter(s -> {
                            i.getAndSet(i.get() + s.length());
                            return i.get() <= maxChar;
                        }).toArray(String[]::new)) + " ..."
        );
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
