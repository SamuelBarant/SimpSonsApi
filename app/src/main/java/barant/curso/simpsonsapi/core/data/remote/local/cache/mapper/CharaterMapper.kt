package barant.curso.simpsonsapi.core.data.remote.local.cache.mapper

import barant.curso.simpsonsapi.core.data.remote.local.cache.entity.CharacterEntity
import barant.curso.simpsonsapi.feature.character.domain.Character

fun CharacterEntity.toDomain() : Character{
    return Character(
        id = this.id,
        name = this.name,
        age = this.age,
        gender = this.gender,
        birthdate = this.birthdate,
        occupation = this.occupation,
        status = this.status,
        phrase = this.phrase,
        img = this.img
    )
}

fun Character.toEntity() : CharacterEntity{
    return CharacterEntity(
        id = this.id,
        name = this.name,
        age = this.age,
        gender = this.gender,
        birthdate = this.birthdate,
        occupation = this.occupation,
        status = this.status,
        phrase = this.phrase,
        img = this.img
    )
}