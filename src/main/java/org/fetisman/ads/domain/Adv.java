package org.fetisman.ads.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Adv {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "short_Desc")
    @NotBlank(message = "Please fill the short description")
    @Length(max = 256, message = "Message too long (more than 256B).")
    private String shortDesc;

    @Column(name = "long_Desc")
    @NotBlank(message = "Please fill the long description")
    @Length(max = 4096, message = "Message too long (more than 4kB).")
    private String longDesc;

    @NotBlank(message = "Please fill the phone number")
    private String phone;

    private String price;

    @NotBlank(message = "Please chose the type")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private AdvType advType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Adv() {
    }

    public Adv(AdvType advType,
               String shortDesc,
               String longDesc,
               String phone,
               String price,
               User user) {
        this.advType = advType;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.phone = phone;
        this.price = price;
        this.author = user;
    }

    public boolean isLongDescTooLong(){
        return longDesc.length() > 10;
    }

    public boolean isPhoneTooLong(){
        return phone.length() > 10;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public String getTypeTitle(){ // !!!
        return type != null ? getAdvType().getAdvType() : "<none>";
    }

    public User getUser() {
        return author;
    }

    public void setUser(User user) {
        this.author = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AdvType getAdvType() {
        return advType;
    }

    public void setAdvType(AdvType advType) {
        this.advType = advType;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
