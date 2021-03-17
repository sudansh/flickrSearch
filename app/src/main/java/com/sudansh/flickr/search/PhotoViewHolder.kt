package com.sudansh.flickr.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sudansh.flickr.R
import com.sudansh.flickr.data.local.Photo
import com.sudansh.flickr.data.source.FlickrConstants

class PhotoViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    private var photo: Photo? = null

    fun bind(photo: Photo?) {
        this.photo = photo
        photo?.let {
            Glide.with(itemView.context)
                .load(getPhotoUrl(it))
                .into(thumbnail)
        }

    }

    private fun getPhotoUrl(photo: Photo) = String.format(
        FlickrConstants.IMAGE_URL,
        photo.farm,
        photo.server,
        photo.id,
        photo.secret
    )

    companion object {
        fun create(
            parent: ViewGroup
        ): PhotoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_item, parent, false)
            return PhotoViewHolder(view)
        }
    }
}