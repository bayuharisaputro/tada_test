package com.example.tada_test.model

import com.google.gson.annotations.SerializedName


data class Facets (

  @SerializedName("facets"     ) var facets     : ArrayList<Facets> = arrayListOf(),
  @SerializedName("name"       ) var name       : String?           = null,
  @SerializedName("otherTerms" ) var otherTerms : Int?              = null,
  @SerializedName("prettyName" ) var prettyName : Int?              = null

)