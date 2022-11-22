package dev.palmes.ilovejava.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Thread {
    @Id
    @GeneratedValue
    private UUID id;

    private boolean locked;

    private String title;

    @OneToOne
    @JoinColumn(nullable = false)
    private Post entry;

    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Tag> tags;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post getEntry() {
        return entry;
    }

    public void setEntry(Post entry) {
        this.entry = entry;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
