package com.dolphine.spring.playground.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

/**
 * Created by Tiago Dolphine (tiago.dolphine@ifood.com.br) on 18/03/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @NonNull
    private String id;
    private String name;
    private Optional<Integer> age;
    private Optional<String> phone;


}
