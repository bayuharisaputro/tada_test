package com.example.tada_test.model

import com.google.gson.annotations.SerializedName


data class Links (

  @SerializedName("self" ) var self : String? = null,
  @SerializedName("web"  ) var web  : String? = null

)