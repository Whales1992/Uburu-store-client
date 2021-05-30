package com.android.uburu.networks.rectrofit.dto

data class LoginDto(
    val `data`: Data?,
    val message: String?,
    val status: Boolean?
) {
    data class Data(
        val address: Address?,
        val createdDate: String?,
        val email: String?,
        val fullNames: String?,
        val id: Int?,
        val password: String?,
        val phone: String?,
        val roles: Roles?,
        val status: String?,
        val updatedDate: String?,
        val userName: String?
    ) {
        data class Address(
            val createdDate: String?,
            val id: Int?,
            val lga: String?,
            val state: String?,
            val street: String?,
            val updatedDate: String?
        )

        data class Roles(
            val createdDate: String?,
            val id: Int?,
            val roles: String?,
            val updatedDate: String?
        )
    }
}