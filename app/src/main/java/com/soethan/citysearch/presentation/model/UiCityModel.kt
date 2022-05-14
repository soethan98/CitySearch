package com.soethan.citysearch.presentation.model

data class UiCityModel(val title:String,
val id:Int)



object MockClass{
    fun generate():List<UiCityModel>{
        val mockList = mutableListOf<UiCityModel>()
        for (i in 1..200){
            mockList.add(UiCityModel("$i-ANVBBV",id = i))
        }
        return mockList
    }
}

//val mockList = listOf(UiCityModel(id = 1,title = "A"),
//    UiCityModel(id = 1,title = "B"),
//    UiCityModel(id = 1,title = "C"),
//    UiCityModel(id = 1,title = "D"),
//    UiCityModel(id = 1,title = "E"),
//    UiCityModel(id = 1,title = "F"),
//    UiCityModel(id = 1,title = "G"),
//    UiCityModel(id = 1,title = "H"),
//    UiCityModel(id = 1,title = "I"),
//    UiCityModel(id = 1,title = "AJ"),
//    UiCityModel(id = 1,title = "K"),
//    UiCityModel(id = 1,title = "L"),
//    UiCityModel(id = 1,title = "M"),
//    UiCityModel(id = 1,title = "N"),
//    UiCityModel(id = 1,title = "O"),
//    UiCityModel(id = 1,title = "P"),UiCityModel(id = 1,title = "Q"))