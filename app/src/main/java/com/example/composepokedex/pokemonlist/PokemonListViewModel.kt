package com.example.composepokedex.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import com.example.composepokedex.di.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        //não nos referimos diretamente ao bitmap pois as imagens ds API estão em um formato que n funciona com com a API pallete.
        //portanto primeiro tornamos esse bitmap mutável e depois convetê-lo p/ um tipo de configuração de bitmap que funcione com a biblioteca.
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        //a fun generate dará a paleta de cores com todas as interessantes
        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

}