package org.example.advs.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdvType> advTypes;

    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<AdvType> getAdvTypes() {
        return advTypes;
    }

    public void setAdvTypes(Set<AdvType> advTypes) {
        this.advTypes = advTypes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
