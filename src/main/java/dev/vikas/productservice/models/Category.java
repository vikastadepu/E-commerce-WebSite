package dev.vikas.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class  Category extends BaseModel {
    @Column
    private String name;

    @OneToMany(mappedBy = "category",fetch=FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private List<Product> products= new ArrayList<>();
}
