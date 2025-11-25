package barant.curso.simpsonsapi.core.presentation.ext

import android.widget.ImageView
import coil3.load

fun ImageView.fromUrl(url:String){
    this.load("https://cdn.thesimpsonsapi.com/500".plus(url))

}