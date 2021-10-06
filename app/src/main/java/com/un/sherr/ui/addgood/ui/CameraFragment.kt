package com.un.sherr.ui.addgood.ui

import android.content.Context
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.media.ImageReader
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Size
import android.util.SparseArray
import android.view.*
import androidx.core.content.ContextCompat
import com.un.sherr.base.BaseFragment
import com.un.sherr.R
import com.un.sherr.databinding.FragmentCameraBinding
import com.un.sherr.ui.MainActivity
import com.un.sherr.ui.addgood.adapters.CamerasAdapter
import com.un.sherr.utils.Utils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CameraFragment : BaseFragment(), View.OnClickListener {

    private lateinit var cameraDimensions: Size
    private var cameraDevice: CameraDevice? = null
    private lateinit var cameraCaptureSession: CameraCaptureSession
    private lateinit var captureRequestBuilder: CaptureRequest.Builder

    private var backgroundHandler: Handler? = null
    private var backgroundThread: HandlerThread? = null
    private var file: File? = null
    private var flashlightVisible = false

    private var frontCamera = false

    private val ORIENTATIONS = SparseArray<Int>()

    private lateinit var adapter: CamerasAdapter

    private lateinit var binding: FragmentCameraBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ORIENTATIONS.append(Surface.ROTATION_0, 90)
        ORIENTATIONS.append(Surface.ROTATION_90, 0)
        ORIENTATIONS.append(Surface.ROTATION_180, 270)
        ORIENTATIONS.append(Surface.ROTATION_270, 180)

//        texture.surfaceTextureListener = surfaceListener
        binding.btnTakePicture.setOnClickListener(this)
        binding.ivFlash.setOnClickListener(this)
        binding.ivCross.setOnClickListener(this)
        binding.ivReverseCamera.setOnClickListener(this)
        adapter = CamerasAdapter(requestManager)
        binding.rvPhotos.adapter = adapter/*
        adapter.list = ArrayList(Utils.getImages(context!!).map { it.path })
        adapter.notifyDataSetChanged()*/
    }

//    private val surfaceListener = object : TextureView.SurfaceTextureListener {
//        override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {}
//        override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {}
//        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean = false
//        override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
//            openCamera()
//        }
//
//    }

    fun openCamera() {
        val cameraManager = requireActivity().getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = if (frontCamera && cameraManager.cameraIdList.size > 1)
            cameraManager.cameraIdList[1]
        else
            cameraManager.cameraIdList[0]

        val cameraCharacterisitcs = cameraManager.getCameraCharacteristics(cameraId)
        val map = cameraCharacterisitcs.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)

        cameraDimensions = map!!.getOutputSizes(SurfaceTexture::class.java)[0]

        if (Utils.checkCameraPermission(requireContext()))
            cameraManager.openCamera(cameraId, stateCallback, null)
    }

    private val stateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera
            startCameraPreview()
        }

        override fun onDisconnected(camera: CameraDevice) {
            cameraDevice!!.close()
            cameraDevice = null
        }

        override fun onError(camera: CameraDevice, error: Int) {
            cameraDevice!!.close()
            cameraDevice = null
        }
    }

    fun startCameraPreview() {
        val surfaceTexture = binding.texture.surfaceTexture
//        surfaceTexture.setDefaultBufferSize(cameraDimensions.width, cameraDimensions.height)
        surfaceTexture?.setDefaultBufferSize(cameraDimensions.width, 1080)
        val surface = Surface(surfaceTexture)

        captureRequestBuilder = cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        captureRequestBuilder.addTarget(surface)

        cameraDevice!!.createCaptureSession(listOf(surface), object : CameraCaptureSession.StateCallback() {
            override fun onConfigureFailed(session: CameraCaptureSession) {
                showToast("Configuration changed")
            }

            override fun onConfigured(session: CameraCaptureSession) {
                if (cameraDevice == null) {
                    showToast("cameraDevice is null")
                    return
                }
                adapter.list = ArrayList(Utils.getImages(context!!).map { it.path })
                activity!!.runOnUiThread { adapter.notifyDataSetChanged() }
                cameraCaptureSession = session
                updatePreview()
            }
        }, null)
    }

    fun updatePreview() {
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
        cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null, backgroundHandler)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnTakePicture -> {
                if (cameraDevice == null) return
                val manager = requireActivity().getSystemService(Context.CAMERA_SERVICE) as CameraManager
                val characteristics = manager.getCameraCharacteristics(cameraDevice!!.id)

                val sizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)!!.getOutputSizes(ImageFormat.JPEG)

                if (!sizes.isNullOrEmpty()) {
                    val width = sizes[0].width
                    val height = sizes[0].height

                    val reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1)
                    val outputSurfaces = ArrayList<Surface>(2)
                    outputSurfaces.add(reader.surface)
                    outputSurfaces.add(Surface(binding.texture.surfaceTexture))

                    val captureBuilder = cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
                    captureBuilder.addTarget(reader.surface)
                    captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)

                    val rotation = requireActivity().windowManager.defaultDisplay.rotation
                    captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation))

                    val fileName = (System.currentTimeMillis() / 1000).toString()

                    val start = ContextCompat.getExternalFilesDirs(requireContext(), "photos")[0]
                    start.mkdirs()
                    file = File(start.path + "/$fileName.jpg")
                    val readListener = ImageReader.OnImageAvailableListener {
                        val image = it.acquireLatestImage()
                        val buffer = image.planes[0].buffer

                        val bytes = ByteArray(buffer.capacity())
                        buffer.get(bytes)
                        try {
                            save(bytes)
                        } catch (e: IOException) {
                            showToast(e.toString())
                        } finally {
                            image.close()
                        }
                    }

                    reader.setOnImageAvailableListener(readListener, backgroundHandler)

                    val captureListener = object : CameraCaptureSession.CaptureCallback() {
                        override fun onCaptureCompleted(session: CameraCaptureSession, request: CaptureRequest, result: TotalCaptureResult) {
                            super.onCaptureCompleted(session, request, result)
                            showToast(getString(R.string.saved))
                            startCameraPreview()
                        }
                    }

                    cameraDevice!!.createCaptureSession(outputSurfaces, object : CameraCaptureSession.StateCallback() {
                        override fun onConfigureFailed(session: CameraCaptureSession) {
                            showToast(session.toString())
                        }

                        override fun onConfigured(session: CameraCaptureSession) {
                            session.capture(captureBuilder.build(), captureListener, backgroundHandler)
                        }

                    }, backgroundHandler)
                }
            }
            R.id.ivFlash -> {
                updatePreview()
                if (flashlightVisible)
                    captureRequestBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_OFF)
                else
                    captureRequestBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_TORCH)
                flashlightVisible = !flashlightVisible
            }
            R.id.ivCross -> {
                requireActivity().onBackPressed()
            }
            R.id.ivReverseCamera -> {
                frontCamera = !frontCamera
                if (cameraDevice != null)
                    cameraDevice!!.close()
                if (backgroundThread != null) {
                    backgroundThread!!.quitSafely()
                    backgroundThread!!.join()
                }
                backgroundThread = null
                backgroundHandler = null
                openCamera()
                //texture.surfaceTextureListener = surfaceListener
            }
        }
    }

    private fun save(byteArray: ByteArray) {
        val outputStream = FileOutputStream(file!!)
        outputStream.write(byteArray)
        outputStream.close()
    }

    override fun onResume() {
        super.onResume()
        startBackgroundThread()
        if (binding.texture.isAvailable) {
            openCamera()
        } else {
//            texture.surfaceTextureListener = surfaceListener
        }
        (activity as MainActivity).showBottomMenu(false)
    }

    override fun onPause() {
        if(backgroundThread != null) {
            backgroundThread!!.quitSafely()
            backgroundThread!!.join()
        }
        backgroundThread = null
        backgroundHandler = null

        super.onPause()
    }

    private fun startBackgroundThread() {
        backgroundThread = HandlerThread("background thread")
        backgroundThread!!.start()
        backgroundHandler = Handler(backgroundThread!!.looper)
    }
}