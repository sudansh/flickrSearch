package com.sudansh.flickr.search


import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sudansh.flickr.R
import com.sudansh.flickr.extensions.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val GRID_SPAN = 3

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()
    private lateinit var photosAdapter: PhotosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.resultPhotos.observe(viewLifecycleOwner, {
            photosAdapter.submitList(it)
        })

        viewModel.isProgressVisible.observe(viewLifecycleOwner, { visible ->
            progressBar.visibility = if (visible) View.VISIBLE else View.GONE
        })

        viewModel.errorMessageRes.observe(viewLifecycleOwner, { message ->
            if (message != null) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchQuery.value = query
                searchView.clearFocus()
                requireContext().hideKeyboard(searchView)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private fun initRecyclerView() {
        photosRecyclerView.layoutManager = GridLayoutManager(requireContext(), GRID_SPAN)

        photosAdapter = PhotosAdapter()
        photosRecyclerView.adapter = photosAdapter

    }

}

