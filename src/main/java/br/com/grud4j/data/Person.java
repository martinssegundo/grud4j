package br.com.grud4j.data;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TB_PESSOA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person extends PanacheEntity {
    @Column(name = "DS_NOME")
    private String name;
    @Column(name = "DT_NASC")
    private String birthday;


    public static Uni<List<Person>> findByName(String name){
        return Person.find("name ", name).list();
    }
}
