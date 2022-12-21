package dev.palmes.ilovejava.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@Entity
public class Post {
    public static final int MAX_CONTENT_SIZE = 10000;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User author;


    @Column(length = MAX_CONTENT_SIZE)
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private boolean removed = false;

    @ManyToOne
    private Post parent;

    @OneToMany
    @OrderBy("creationDate ASC")
    private List<Post> children;

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
        this.content = "\n" + content;
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
        parent.addChildren(this);
        this.parent = parent;
    }

    public List<Post> getChildren() {
        return children;
    }

    public void addChildren(Post children) {
        this.children.add(children);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        thread.addPost(this);
        this.thread = thread;
    }
}
