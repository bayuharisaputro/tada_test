package com.example.tada_test.model

import com.google.gson.annotations.SerializedName


data class DataResponse (

  @SerializedName("elapsedMilliseconds" ) var elapsedMilliseconds : Int?                  = null,
  @SerializedName("count"               ) var count               : Int?                  = null,
  @SerializedName("countFacets"         ) var countFacets         : CountFacets?          = CountFacets(),
  @SerializedName("artObjects"          ) var artObjects          : ArrayList<ArtObjects> = arrayListOf(),
  @SerializedName("facets"              ) var facets              : ArrayList<Facets>     = arrayListOf()

)