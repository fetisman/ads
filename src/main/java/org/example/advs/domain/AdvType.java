package org.example.advs.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "adv_type")
public class AdvType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy="advType", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Adv> advs;

    @Column(nullable = false)
    private String advType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Catalog catalog;

    public AdvType() {
    }

    public AdvType(String type){
        this.advType = type;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Set<Adv> getAdvs() {
        return advs;
    }

    public void setAdvs(Set<Adv> advs) {
        this.advs = advs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvType() {
        return advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }
}
