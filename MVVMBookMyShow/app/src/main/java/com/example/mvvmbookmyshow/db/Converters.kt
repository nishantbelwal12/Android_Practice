package com.example.mvvmbookmyshow.db

import androidx.room.TypeConverter
import com.example.mvvmbookmyshow.models.MovieData.Genre
import com.example.mvvmbookmyshow.models.MovieData.ProductionCompany
import com.example.mvvmbookmyshow.models.MovieData.ProductionCountry
import com.example.mvvmbookmyshow.models.MovieData.SpokenLanguage

class Converters {

    @TypeConverter
    fun fromGenre(genre:List<Genre>):Int{
        return genre.size
    }

    @TypeConverter
    fun toGenre(name:Int):List<Genre>{
        return arrayListOf(Genre(0,""))
    }

    @TypeConverter
    fun fromProductionCompany(productionCompany: List<ProductionCompany>):Int{
        return productionCompany.size
    }

    @TypeConverter
    fun toProductionCompany(productionCompanyName: Int):List<ProductionCompany>{
        return arrayListOf(ProductionCompany(productionCompanyName,"logo",productionCompanyName.toString(),"country"))
    }

    @TypeConverter
    fun fromProductionCountry(productionCountry: List<ProductionCountry>):Int{
        return productionCountry.size
    }

    @TypeConverter
    fun toProductionCountry(productionCountryName: Int):List<ProductionCountry>{
        return arrayListOf(ProductionCountry("iso",productionCountryName.toString()))
    }

    @TypeConverter
    fun fromSpokenLanguage(spokenLanguage: List<SpokenLanguage>):Int{
        return spokenLanguage.size
    }

    @TypeConverter
    fun toSpokenLanguage(spokenLanguageName: Int):List<SpokenLanguage>{
        return arrayListOf(SpokenLanguage("name","iso",spokenLanguageName.toString()))
    }

    @TypeConverter
    fun fromGenreIds(genreId: List<Int>):Int{
        return genreId.size
    }

    @TypeConverter
    fun togenreIds(genreIdSize: Int):List<Int>{
        return arrayListOf(genreIdSize)
    }

    @TypeConverter
    fun fromBelongsTo(belong: Any):String{
        return belong.toString()
    }

    @TypeConverter
    fun toBelongsTo(belong: String):Any{
        return belong
    }
}