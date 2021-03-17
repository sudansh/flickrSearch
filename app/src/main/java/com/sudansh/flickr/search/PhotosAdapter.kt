package com.sudansh.flickr.search

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sudansh.flickr.data.local.Photo

class PhotosAdapter :
    PagedListAdapter<Photo, PhotoViewHolder>(photoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoViewHolder.create(parent)

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        val photoDiffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id
        }

    }
}