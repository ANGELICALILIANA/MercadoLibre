package com.example.mercadolibre.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCategoryItem(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String?,
    @SerialName("picture")
    val picture: String?,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("total_items_in_this_category")
    val total_items_in_this_category: Long?,
    @SerialName("path_from_root")
    val path_from_root: List<PathFromRoot?>,
    @SerialName("children_categories")
    val children_categories: List<ChildrenCategory>?,
    @SerialName("attribute_types")
    val attribute_types: String?,
    @SerialName("settings")
    val settings: Settings?,
    @SerialName("channels_settings")
    val channels_settings: List<ChannelSettings?>,
    @SerialName("meta_categ_id")
    val meta_categ_id: String? = null,
    @SerialName("attributable")
    val attributable: Boolean?,
    @SerialName("date_created")
    val date_created: String?
)

@Serializable
data class PathFromRoot(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)

@Serializable
data class ChildrenCategory(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)

@Serializable
data class Settings(
    @SerialName("adult_content")
    val adult_content: Boolean,
    @SerialName("buying_allowed")
    val buying_allowed: Boolean,
    @SerialName("buying_modes")
    val buying_modes: List<String?>,
    @SerialName("catalog_domain")
    val catalog_domain: String,
    @SerialName("coverage_areas")
    val coverage_areas: String,
    @SerialName("currencies")
    val currencies: List<String>,
    @SerialName("fragile")
    val fragile: Boolean,
    @SerialName("immediate_payment")
    val immediate_payment: String?,
    @SerialName("item_conditions")
    val item_conditions: List<String?>,
    @SerialName("items_reviews_allowed")
    val items_reviews_allowed: Boolean,
    @SerialName("listing_allowed")
    val listing_allowed: Boolean,
    @SerialName("max_description_length")
    val max_description_length: Int,
    @SerialName("max_pictures_per_item")
    val max_pictures_per_item: Int,
    @SerialName("max_pictures_per_item_var")
    val max_pictures_per_item_var: Int,
    @SerialName("max_sub_title_length")
    val max_sub_title_length: Int,
    @SerialName("max_title_length")
    val max_title_length: Int,
    @SerialName("max_variations_allowed")
    val max_variations_allowed: Int,
    @SerialName("maximum_price")
    val maximum_price: Double? = null,
    @SerialName("maximum_price_currency")
    val maximum_price_currency: String? = null,
    @SerialName("minimum_price")
    val minimum_price: Double?,
    @SerialName("minimum_price_currency")
    val minimum_price_currency: String,
    @SerialName("mirror_category")
    val mirror_category: String? = null,
    @SerialName("mirror_master_category")
    val mirror_master_category: String? = null,
    @SerialName("mirror_slave_categories")
    val mirrorSlaveCategories: List<String>,
    @SerialName("price")
    val price: String,
    @SerialName("reservation_allowed")
    val reservationAllowed: String,
    @SerialName("restrictions")
    val restrictions: List<String>,
    @SerialName("rounded_address")
    val roundedAddress: Boolean,
    @SerialName("seller_contact")
    val sellerContact: String,
    @SerialName("shipping_options")
    val shippingOptions: List<String>,
    @SerialName("shipping_profile")
    val shippingProfile: String,
    @SerialName("show_contact_information")
    val showContactInformation: Boolean,
    @SerialName("simple_shipping")
    val simpleShipping: String,
    @SerialName("stock")
    val stock: String,
    @SerialName("sub_vertical")
    val subVertical: String,
    @SerialName("subscribable")
    val subscribable: Boolean,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("vertical")
    val vertical: String,
    @SerialName("vip_subdomain")
    val vipSubdomain: String,
    @SerialName("buyer_protection_programs")
    val buyerProtectionPrograms: List<String>,
    @SerialName("status")
    val status: String
)

@Serializable
data class ChannelSettings(
    @SerialName("channel")
    val channel: String,
    @SerialName("settings")
    val settings: ChannelSettingDetails
)

@Serializable
data class ChannelSettingDetails(
    @SerialName("minimum_price")
    val minimum_price: Int? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("buying_modes")
    val buying_modes: List<String>? = null,
    @SerialName("immediate_payment")
    val immediate_payment: String? = null
)