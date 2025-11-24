package barant.curso.simpsonsapi.feature.character.data.remote.api

import barant.curso.simpsonsapi.feature.character.domain.Character

fun CharacterApiModel.toDomain(): Character {
    return Character(
        this.id,
        this.name,
        this.age,
        this.gender,
        this.birthdate,
        this.occupation,
        this.status,
        this.phrases,
        this.portrait_path,
    )
}