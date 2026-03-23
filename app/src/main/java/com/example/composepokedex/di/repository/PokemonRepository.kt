package com.example.composepokedex.di.repository

import com.example.composepokedex.data.remote.PokeApi
import com.example.composepokedex.data.remote.responses.Pokemon
import com.example.composepokedex.data.remote.responses.PokemonList
import com.example.composepokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped //O repository existirá enquanto nossa activity existir
class PokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            pokeApi.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }

        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            pokeApi.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }

        return Resource.Success(response)
    }

}