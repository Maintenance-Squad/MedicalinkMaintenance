package dev.mobile.medicalink.db.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.mobile.medicalink.fragments.traitements.Prise
import java.time.LocalDate

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["uuid"],
        childColumns = ["uuidUser"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["uuidUser"])]
)

//TODO("rajouter un uuid lié au médicament dans la base de donnée médicamenteuse")
data class Medoc(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "uuidUser") val uuidUser: String,
    @ColumnInfo(name = "nom") val nom : String,
    @ColumnInfo(name = "dosageNB") val dosageNB: String,
    @ColumnInfo(name = "dosageUnite") val dosageUnite : String,
    @ColumnInfo(name = "dateFinTraitement") val dateFinTraitement : String?,
    @ColumnInfo(name = "typeComprime") val typeComprime : String,
    @ColumnInfo(name = "comprimesRestants") val comprimesRestants : Int?,
    @ColumnInfo(name = "expire") val expire : Boolean,
    @ColumnInfo(name = "effetsSecondaires") val effetsSecondaires : String?,
    @ColumnInfo(name = "prises") val prises : String?,
    @ColumnInfo(name = "totalQuantite") val totalQuantite : Int?,
    )