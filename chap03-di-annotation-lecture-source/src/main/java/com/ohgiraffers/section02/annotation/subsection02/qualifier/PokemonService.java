package com.ohgiraffers.section02.annotation.subsection02.qualifier;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pokemonServiceQualifier")
public class PokemonService {

    /* 설명.
     *  @Qualifier 를 통해 원하는 bean 이름(id)으로 하나의 bean 을 주입받을 수 있다.
     *  (@Primary 보다 우선순위가 높다.)
    * */

//    @Autowired
//    @Qualifier("squirtle")
    private Pokemon pokemon;

    @Autowired      // 자동으로 달아주는 것을 알고 있지만 그래도 항상 적어주자.
    public PokemonService(@Qualifier("squirtle") Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}
