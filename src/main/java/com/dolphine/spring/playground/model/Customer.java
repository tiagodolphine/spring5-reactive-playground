package com.dolphine.spring.playground.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Optional;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @NonNull
    private String id;
    private String name;
    private Optional<Integer> age;
    private Optional<String> phone;


}
