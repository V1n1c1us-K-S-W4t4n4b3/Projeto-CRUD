package com.kzdev.projetocrud.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class SubscriberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String?,
    val birth: String?,
    val cpf: String?,
    val tel: String?,
) : Parcelable

// declarando a variavel imutavel com o nome item1 do "tipo(classe/interface)" SubscriberEntity
// val item1: SubscriberEntity
//
// declarando a variavel imutavel com o nome item1 do "tipo(classe/interface)" SubscriberEntity
// e instanciando um objeto do tipo SubscriberEntity passando os valores 100,"dypsi", "23","238979824729837","213871827"
// val item1: SubscriberEntity = SubscriberEntity(100,"dypsi", "23","238979824729837","213871827")