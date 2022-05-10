package com.example.tada_test.model

import com.google.gson.annotations.SerializedName


data class CountFacets (

  @SerializedName("hasimage"  ) var hasimage  : Int? = null,
  @SerializedName("ondisplay" ) var ondisplay : Int? = null

)