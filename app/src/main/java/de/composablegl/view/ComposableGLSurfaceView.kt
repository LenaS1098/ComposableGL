package de.composablegl.view

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.Log
import de.composablegl.renderer.ComposableGLRenderer


class ComposableGLSurfaceView(context: Context?) : GLSurfaceView(context) {


    private val renderer : ComposableGLRenderer

    init {

        Log.d("renderer","init")
        val color = floatArrayOf(0.043f, 0.294f, 0.976f,1.0f)

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = ComposableGLRenderer(color)

        setRenderer(renderer)

        // Render the view only when there is a change in the drawing data
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY


        preserveEGLContextOnPause = true

        requestRender()

    }
    fun redraw(color: FloatArray){
        renderer.redrawBox(color)
    }


    override fun onPause() {
        super.onPause()
    }


}