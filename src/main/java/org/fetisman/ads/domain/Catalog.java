package org.fetisman.ads.domain;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.Size;

@Entity
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdvType> advTypes;

    @Size(min = 2, max = 20, message = "Check your catalog data. Require min length - 2 chars & max length - 20 chars")
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
