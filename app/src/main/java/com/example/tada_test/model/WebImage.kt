package com.example.tada_test.model

import com.google.gson.annotations.SerializedName


data class WebImage (

  @SerializedName("guid"              ) var guid              : String? = null,
  @SerializedName("offsetPercentageX" ) var offsetPercentageX : Int?    = null,
  @SerializedName("offsetPercentageY" ) var offsetPercentageY : Int?    = null,
  @SerializedName("width"             ) var width             : Int?    = null,
  @SerializedName("height"            ) var height            : Int?    = null,
  @SerializedName("url"               ) var url               : String? = null

)