package de.composablegl.renderer

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.util.Log
import de.composablegl.shapes.Box
import java.util.concurrent.atomic.AtomicBoolean
import javax.microedition.khronos.egl.EGL10
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.egl.EGLContext
import javax.microedition.khronos.opengles.GL10

class ComposableGLRenderer(private val color:FloatArray): GLSurfaceView.Renderer {

     lateinit var mBox : Box


    override fun onSurfaceCreated(gl: GL10, config: EGLConfig?) {
        // Set the background frame color
        GLES20.glClearColor(0.478f, 0.274f, 0.823f, 1.0f)
        //initialize Box
        mBox = Box(color)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)

    }

    override fun onDrawFrame(gl: GL10) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        mBox.draw()
    }

    fun redrawBox(color:FloatArray){
        Log.d("renderer","redraw")
        if(this::mBox.isInitialized){
            mBox.setColor(color)
        }
    }



}