package com.example.mvvmbookmyshow.db

import androidx.room.TypeConverter
import com.example.mvvmbookmyshow.models.MovieData.Genre
import com.example.mvvmbookmyshow.models.MovieData.ProductionCompany
import com.example.mvvmbookmyshow.models.MovieData.ProductionCountry
import com.example.mvvmbookmyshow.models.MovieData.SpokenLanguage

class Converters {

    @TypeConverter
    fun fromGenre(genre:Genre):String{
        return genre.name
    }

    @TypeConverter
    fun toGenre(name:String):Genre{
        return Genre(name.length,name)
    }

    @TypeConverter
    fun fromProductionCompany(productionCompany: ProductionCompany):String{
        return productionCompany.name
    }

    @TypeConverter
    fun toProductionCompany(productionCompanyName: String):ProductionCompany{
        return ProductionCompany(productionCompanyName.length,"logo",productionCompanyName,"country")
    }

    @TypeConverter
    fun fromProductionCountry(productionCountry: ProductionCountry):String{
        return productionCountry.name
    }

    @TypeConverter
    fun toProductionCountry(productionCountryName: String):ProductionCountry{
        return ProductionCountry("iso",productionCountryName)
    }

    @TypeConverter
    fun fromSpokenLanguage(spokenLanguage: SpokenLanguage):String{
        return spokenLanguage.name
    }

    @TypeConverter
    fun toSpokenLanguage(spokenLanguageName: String):SpokenLanguage{
        return SpokenLanguage("name","iso",spokenLanguageName)
    }

    @TypeConverter
    fun fromGenreIds(genreId: List<Int>):Int{
        return genreId.size
    }

    @TypeConverter
    fun togenreIds(genreIdSize: Int):List<Int>{
        return arrayListOf(genreIdSize)
    }

}