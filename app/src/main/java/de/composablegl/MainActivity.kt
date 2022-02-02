package de.composablegl

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import de.composablegl.renderer.ComposableGLRenderer
import de.composablegl.ui.theme.ComposableGLTheme
import de.composablegl.view.ComposableGLSurfaceView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableGLTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val temperatur = remember{ mutableStateOf(26.0f)}
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        AllInOne(temperature = temperatur.value)
        //TempScreen(temperature = temperatur.value)
        Text(text = temperatur.value.toString(), modifier = Modifier.padding(30.dp), fontSize = 35.sp, textAlign = TextAlign.Center)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp))
        {
            Button(onClick = { temperatur.value -= 10f }) {
                Text("-10")
            }
            Button(onClick = { temperatur.value -= 5f }) {
                Text("-5")
            }
            Button(onClick = {  temperatur.value += 5f }) {
                Text("+5")
            }
            Button(onClick = {  temperatur.value += 10f }) {
                Text("+10")
            }
        }
    }
}

@Composable
fun AllInOne(temperature: Float){
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        factory = { it ->
            ComposableGLSurfaceView(it)
        }, update = {   view ->
            view.queueEvent(Runnable {
                view.redraw(getColorByTemp(temperature))

            })
        })
}

@Composable
fun TempScreen(temperature: Float){
    Text(temperature.toString())
    val color = getColorByTemp(temperature)
    TempView(color = color)
}


fun getColorByTemp(temperature: Float):FloatArray{
    return if(temperature >= 20f){
        Log.d("renderer","color change green")
        floatArrayOf(0.125f,0.850f,0.149f,1.0f)
    }else if(temperature < 20f && temperature>=0f){
        Log.d("renderer","color change yellow")
        floatArrayOf(0.976f,0.929f,0.043f,1.0f)
    } else{
        Log.d("renderer","color change blue")
        floatArrayOf(0.976f, 0.043f, 0.043f, 1.0f)
    }
}

@Composable
fun TempView(color:FloatArray){
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        factory = { it ->
            ComposableGLSurfaceView(it)
        }, update = {   view ->
            view.queueEvent(Runnable {
               view.redraw(color)

            })
        })
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposableGLTheme {
        Greeting("Android")
    }
}