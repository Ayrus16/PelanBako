package id.ac.unpas.pelanbako.networks.responses

import id.ac.unpas.pelanbako.models.item

data class ItemPostResponse(
    val message: String,
    val success: Boolean,
    val data: item?
)
