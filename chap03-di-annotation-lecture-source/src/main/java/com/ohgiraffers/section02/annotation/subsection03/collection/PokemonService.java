package com.ohgiraffers.section02.annotation.subsection03.collection;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("pokemonServiceCollection")
public class PokemonService {

    /* 설명. 같은 타입의 bean 이 2개 이상일 경우 List 또는 Map 형태의 Collection 형태로 주입받을 수 있다. */

//    /* 필기. 1. 같은 타입의 bean 들을 List 형태로 주입 받을 수 있다. */
//
//    /* 설명. 포켓몬 3마리가 다같이 때리기 */
//    private List<Pokemon> pokemonList;
//
//    // 만약에 포켓몬 타입의 애들이 여러마리면은~
//    @Autowired
//    public PokemonService(List<Pokemon> pokemonList) {
//        this.pokemonList = pokemonList;
//    }
//
//    public void pokemonAttack() {
//        pokemonList.forEach(Pokemon::attack);       // 반환형이 없는(void) consumer 타입의 메소드를 참조할 수 있다.
//    }

    private Map<String, Pokemon> pokemonMap;

    @Autowired
    public PokemonService(Map<String, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
    }

    /* 설명. key 값은 bean 의 ID가 넘어오고, value 값은 bean 객체의 주소값이 넘어온다. */
    public void pokemonAttack() {
        pokemonMap.forEach((k, v) -> {
            System.out.println("key: " + k);        // bean 객체의 ID가 넘어옴
            System.out.println("value: " + v);      //
            v.attack();
        });
    }
}
