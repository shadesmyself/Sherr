package  com.un.sherr.ui.offer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.ui.offer.FullPictureFragment.Companion.IMG
import com.un.sherr.ui.offer.adapters.CardAdapter
import kotlinx.android.synthetic.main.offer_image_card.*

class PictureFragment : BaseFragment() {

    private lateinit var url: String
    var mCardView: CardView? = null
        private set

    companion object {
        fun getInstance(image: String): PictureFragment {
            val fragment = PictureFragment()
            fragment.url = image
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.offer_image_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCardView = cardView
        cardView!!.maxCardElevation = cardView.cardElevation * CardAdapter.MAX_ELEVATION_FACTOR
        requestManager
            .load(url)
            .into(img)

        img.setOnClickListener {
            findNavController().navigate(R.id.action_detailOfferFragment_to_fullPictureFragment,
                bundleOf(IMG to url))
        }
    }
}
