package ru.gb.android.workshop2.presentation.list.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.gb.android.workshop2.marketsample.databinding.FragmentProductListBinding
import ru.gb.android.workshop2.presentation.list.product.adapter.ProductsAdapter
import ru.gb.android.workshop2.presentation.list.product.model.ProductState

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val adapter = ProductsAdapter()

    private val viewModel by viewModels <ProductViewModel> {
        FeatureServiceLocator.provideProductViewModelFactory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.loadProduct()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect{ state ->
                    binding.progress.visibility = View.VISIBLE

                    when {
                        state.hasError -> showError(state.errorProvider(requireContext()))

                        state.isLoading -> {
                            showAllProducts(state.productList)
                            binding.swipeRefreshLayout.isRefreshing = false
                        }
                    }
                }
            }
        }
    }

    fun showAllProducts(productList: List<ProductState>) {
        binding.recyclerView.visibility = View.VISIBLE
        adapter.submitList(productList)
        binding.progress.visibility = View.GONE
    }

    fun showError(error: String) {
        Toast.makeText(
            requireContext(),
            error,
            Toast.LENGTH_SHORT
        ).show()
    }
}
