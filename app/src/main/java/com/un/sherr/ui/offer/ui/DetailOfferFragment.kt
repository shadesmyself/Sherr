package com.un.sherr.ui.offer.ui


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.maps.android.clustering.ClusterManager
import com.un.sherr.R
import com.un.sherr.base.BaseApplication
import com.un.sherr.base.BaseMapFragment
import com.un.sherr.custom.ShadowTransformer
import com.un.sherr.databinding.FragmentDetailOfferBinding
import com.un.sherr.di.ViewModelProviderFactory
import com.un.sherr.models.ClusterMarker
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.dialog.DialogWithTwoButtons
import com.un.sherr.ui.offer.ui.CreateCommentFragment.Companion.ID
import com.un.sherr.ui.offer.adapters.CardFragmentPagerAdapter
import com.un.sherr.ui.offer.vm.DetailOfferViewModel
import com.un.sherr.utils.MyClusterManagerRenderer
import com.un.sherr.utils.Utils
import kotlinx.android.synthetic.main.bottom_sheet_description_offer.*
import kotlinx.android.synthetic.main.bottom_sheet_map_offer.*
import kotlinx.android.synthetic.main.content_detail_offer.*
import javax.inject.Inject


class DetailOfferFragment : BaseMapFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    lateinit var viewModel: DetailOfferViewModel
    private lateinit var binding: FragmentDetailOfferBinding

    private val args: DetailOfferFragmentArgs by navArgs()

    private var mGoogleMap: GoogleMap? = null
    private var mClusterManager: ClusterManager<ClusterMarker>? = null
    private var mClusterManagerRenderer: MyClusterManagerRenderer? = null
    private val markers = ArrayList<ClusterMarker>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailOfferBinding.inflate(layoutInflater)
        return binding.root
    }


    lateinit var sheetBehaviorMap: BottomSheetBehavior<View>
    lateinit var sheetBehaviorDescription: BottomSheetBehavior<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(
            DetailOfferViewModel::class.java)
        sheetBehaviorMap = BottomSheetBehavior.from(bottom_sheet)
        sheetBehaviorDescription = BottomSheetBehavior.from(bottom_sheet_description)
        val img: ArrayList<String> = arrayListOf()
        img.add("https://i.pinimg.com/564x/1d/a6/3c/1da63c8207b372798b021b475614b65c.jpg")
        img.add("https://i.pinimg.com/564x/56/55/a7/5655a787b0988e9ba58f7dbaf414b20c.jpg")
        img.add("https://i.pinimg.com/564x/8b/dd/56/8bdd56983d6136208f5884a34b37e1c2.jpg")

       val userToken = BaseApplication.userToken.getString("UserToken", "")

        val adapter = CardFragmentPagerAdapter(childFragmentManager, 4f, img)
        val fragmentCardShadowTransformer = ShadowTransformer(viewPager, adapter)
        fragmentCardShadowTransformer.enableScaling(true)
        viewPager.adapter = adapter
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer)
        indicator.setViewPager(viewPager)
        viewPager.offscreenPageLimit = 3
        viewPager.setCurrentItem(1, false)

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        lease_btn.setOnClickListener {
            if (!userToken.isNullOrEmpty()){
            findNavController().navigate(
               R.id.action_detailOfferFragment_to_leaseOfferFragment,
               bundleOf(ID to "id")
           )
            } else{
                showDialog(getString(R.string.rent_dialog_hint))
            }
        }
        msg_btn.setOnClickListener {
            if (!userToken.isNullOrEmpty()){
                findNavController().navigate(
                    R.id.chatsFragment
                )
            } else{
                showDialog(getString(R.string.send_msg_dialog_hint, "name"))
            }
        }
        openCloseBottomSheets()
        viewModel.loadOrder(args.id)
        initObservables()
    }

    private fun initObservables() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(activity, "error, ${error}", Toast.LENGTH_LONG).show()
        }
        viewModel.progressDialog.observe(viewLifecycleOwner) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.ordersData.observe(viewLifecycleOwner) {
            title_detail.text = it.title
            price_detail.text = resources.getString(R.string.price_value1, it.costDay.toString())
            tvDescription.text = it.description
        }
    }

    lateinit var cDesc: BottomSheetBehavior.BottomSheetCallback
    lateinit var cMap: BottomSheetBehavior.BottomSheetCallback

    //all the magic is here
    private fun openCloseBottomSheets() {

        cMap = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {}

            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(p0: View, p1: Int) {
                viewPager.visibility = if (p1 == BottomSheetBehavior.STATE_EXPANDED) View.GONE else View.VISIBLE
                indicator.visibility = if (p1 == BottomSheetBehavior.STATE_EXPANDED) View.GONE else View.VISIBLE

                when (p1) {
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        viewPager.animate().alpha(0.0f).duration = 1000
                        if (sheetBehaviorDescription.state != BottomSheetBehavior.STATE_EXPANDED)
                            bottom_sheet_description.animate().alpha(0.0f).duration = 1000
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        if (sheetBehaviorDescription.state != BottomSheetBehavior.STATE_EXPANDED)
                            bottom_sheet_description.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        viewPager.animate().alpha(1.0f).duration = 1000
                        bottom_sheet_description.animate().alpha(1.0f).duration = 1000
                        bottom_sheet_description.visibility = View.VISIBLE
                    }
                }
            }
        }
        sheetBehaviorMap.addBottomSheetCallback(cMap)

        cDesc = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {}

            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(p0: View, p1: Int) {
                when (p1) {
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> { //open
                        val params = CoordinatorLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            gravity = Gravity.BOTTOM
                            topMargin = Utils.dpToPixels(130, activity!!).toInt()
//                            leftMargin = Utils.dpToPixels(16, activity!!).toInt()
//                            rightMargin = Utils.dpToPixels(16, activity!!).toInt()
//                            bottomMargin = Utils.dpToPixels(16, activity!!).toInt()
                        }
                        btns.layoutParams = params

                        val animate = TranslateAnimation(
                            0f,  // fromXDelta
                            0f,  // toXDelta
                            Utils.dpToPixels(-130, activity!!),  // fromYDelta
                            Utils.dpToPixels(0, activity!!)
                        ) // toYDelta

                        animate.duration = 500
                        btns!!.startAnimation(animate)
                        transView.visibility = View.GONE
                        title.visibility = View.VISIBLE
                        btns.background = ContextCompat.getDrawable(activity!!, R.drawable.blue_corner_bg)
                        sheetBehaviorMap.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {//close
                        val params = CoordinatorLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            gravity = Gravity.TOP
                            topMargin = Utils.dpToPixels(130, activity!!).toInt()
//                            leftMargin = Utils.dpToPixels(16, activity!!).toInt()
//                            rightMargin = Utils.dpToPixels(16, activity!!).toInt()
//                            bottomMargin = Utils.dpToPixels(16, activity!!).toInt()
                        }
                        btns.layoutParams = params

                        val animate = TranslateAnimation(
                            0f,  // fromXDelta
                            0f,  // toXDelta
                            Utils.dpToPixels(200, activity!!),  // fromYDelta
                            0f
                        ) // toYDelta

                        animate.duration = 500
                        btns!!.startAnimation(animate)
                        transView.visibility = View.VISIBLE
                        title.visibility = View.GONE
                        btns.setBackgroundColor(Color.TRANSPARENT)
                        sheetBehaviorMap.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                }
            }
        }
        sheetBehaviorDescription.addBottomSheetCallback(cDesc)
    }

    override fun onMapReady(map: GoogleMap?) {
        mGoogleMap = map
        addMapMarkers()
    }

    private fun addMapMarkers() {
        if (mGoogleMap != null) {
            if (mClusterManager == null) {
                mClusterManager = ClusterManager(requireActivity().applicationContext, mGoogleMap)
            }
            if (mClusterManagerRenderer == null) {
                mClusterManagerRenderer = MyClusterManagerRenderer(requireActivity(), mGoogleMap!!, mClusterManager!!)
                mClusterManager!!.renderer = mClusterManagerRenderer
            }
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            LocationServices.getFusedLocationProviderClient(requireActivity()).lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val location: Location? = task.result
//                    showToast("addMapMarkers: location: " + location.toString())
                    try {
                        val snippet = "This is you"

                        val avatar: Int = R.drawable.place // set the default avatar

                        val newClusterMarker = ClusterMarker(
                            LatLng(location!!.latitude, location.longitude),
                            "name", snippet, avatar
                        )
                        mClusterManager!!.addItem(newClusterMarker)
                        markers.add(newClusterMarker)
                    } catch (e: NullPointerException) {
                        showToast(e.toString())
                    }

                    mClusterManager!!.cluster()
                    setCameraView(location!!)
                }
            }
        }
    }

    private fun setCameraView(l: Location) { // Set a boundary to start
        val bottomBoundary: Double = l.latitude - .1
        val leftBoundary: Double = l.longitude - .1
        val topBoundary: Double = l.latitude + .1
        val rightBoundary: Double = l.longitude + .1
        val mMapBoundary = LatLngBounds(
            LatLng(bottomBoundary, leftBoundary),
            LatLng(topBoundary, rightBoundary)
        )
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0))
    }

    private fun showDialog(title: String) {
        val dialog = DialogWithTwoButtons.newInstance(
            title = title,
            firstButtonText = getString(R.string.authorization),
            secondButtonText = getString(R.string.registration)
        )
        dialog.apply {
            onFirstButtonClick = {
                findNavController().navigate(R.id.authorizationFragment)
            }
            onSecondButtonClick = {
                findNavController().navigate(R.id.registrationFragment)
            }
        }
        dialog.show(requireActivity().supportFragmentManager, DialogWithTwoButtons::class.java.simpleName)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomMenu(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sheetBehaviorMap.removeBottomSheetCallback(cMap)
        sheetBehaviorDescription.removeBottomSheetCallback(cDesc)
    }
}
