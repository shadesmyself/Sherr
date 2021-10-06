package  com.un.sherr.ui.offer.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.databinding.OfferImageCardBinding
import com.un.sherr.ui.offer.ui.FullPictureFragment.Companion.IMG
import com.un.sherr.ui.offer.adapters.CardAdapter

class PictureFragment : BaseFragment() {

    private lateinit var url: String

    var mCardView: CardView? = null
        private set

    private lateinit var binding: OfferImageCardBinding

    companion object {
        fun getInstance(image: String): PictureFragment {
            val fragment = PictureFragment()
            fragment.url = image
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = OfferImageCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCardView = binding.cardView
        binding.cardView.maxCardElevation = binding.cardView.cardElevation * CardAdapter.MAX_ELEVATION_FACTOR
        requestManager
            .load(url)
            .into(binding.img)

        binding.img.setOnClickListener {
            findNavController().navigate(
                R.id.action_detailOfferFragment_to_fullPictureFragment,
                bundleOf(IMG to url)
            )
        }
    }
}
