package barant.curso.simpsonsapi.feature.character.data.local.room.character

import barant.curso.simpsonsapi.feature.character.domain.Character

fun CharacterEntity.toDomain(): Character {
    return Character(
        this.id,
        this.name,
        this.age,
        this.gender,
        this.birthdate,
        this.occupation,
        this.status,
        this.phrases,
        this.portrait_path
    )
}

fun Character.toEntity(): CharacterEntity {
    return CharacterEntity(
        this.id,
        this.name,
        this.age,
        this.gender,
        this.gender,
        this.occupation,
        this.phrase,
        this.status,
        this.img
    )
}