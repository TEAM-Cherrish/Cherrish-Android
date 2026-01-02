package com.cherrish.android.data.model

import com.cherrish.android.data.dto.response.DummyResponseDto

data class DummyModel(
    val id: Long,
    val name: String,
    val age: Int
)

fun DummyResponseDto.toModel() = DummyModel(
    id = this.id,
    name = this.name,
    age = this.age
)
