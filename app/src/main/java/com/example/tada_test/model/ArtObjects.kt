package com.example.tada_test.model

import com.google.gson.annotations.SerializedName


data class ArtObjects (

  @SerializedName("links"                 ) var links                 : Links?            = Links(),
  @SerializedName("id"                    ) var id                    : String?           = null,
  @SerializedName("objectNumber"          ) var objectNumber          : String?           = null,
  @SerializedName("title"                 ) var title                 : String?           = null,
  @SerializedName("hasImage"              ) var hasImage              : Boolean?          = null,
  @SerializedName("principalOrFirstMaker" ) var principalOrFirstMaker : String?           = null,
  @SerializedName("longTitle"             ) var longTitle             : String?           = null,
  @SerializedName("showImage"             ) var showImage             : Boolean?          = null,
  @SerializedName("permitDownload"        ) var permitDownload        : Boolean?          = null,
  @SerializedName("webImage"              ) var webImage              : WebImage?         = WebImage(),
  @SerializedName("headerImage"           ) var headerImage           : HeaderImage?      = HeaderImage(),
  @SerializedName("productionPlaces"      ) var productionPlaces      : ArrayList<String> = arrayListOf()

)