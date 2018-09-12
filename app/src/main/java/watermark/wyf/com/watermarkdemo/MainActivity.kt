package watermark.wyf.com.watermarkdemo

import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import watermark.wyf.com.watermarkdemo.watermark.Watermark

class MainActivity : AppCompatActivity() {
    private val strings = arrayListOf("文字水印", "图片水印")
    private var str: String = "文字水印"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, strings)
        Spinner.adapter = adapter
        Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                str = strings[p2]
            }

        }

        tv_Button_lt.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.LEFT_TOP, 30f,255)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        BitmapFactory.decodeResource(resources, R.mipmap.test_image), Watermark.LEFT_TOP,255)
                iv_image.setImageBitmap(bitmap)
            }
        }

        tv_Button_lc.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.LEFT_CENTER, 30f,155)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        BitmapFactory.decodeResource(resources, R.mipmap.test_image), Watermark.LEFT_CENTER,155)
                iv_image.setImageBitmap(bitmap)
            }
        }
        tv_Button_lb.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.LEFT_BOTTOM, 30f,55)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        BitmapFactory.decodeResource(resources, R.mipmap.test_image), Watermark.LEFT_BOTTOM,55)
                iv_image.setImageBitmap(bitmap)
            }
        }
        tv_Button_TC.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.TOP_CENTER, 30f,255)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        BitmapFactory.decodeResource(resources, R.mipmap.test_image), Watermark.TOP_CENTER,255)
                iv_image.setImageBitmap(bitmap)
            }
        }
        tv_Button_c.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.CENTER, 30f,255)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        BitmapFactory.decodeResource(resources, R.mipmap.test_image), Watermark.CENTER,255)
                iv_image.setImageBitmap(bitmap)
            }
        }
        tv_Button_BC.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.BOTTOM_CENTER, 30f,255)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        BitmapFactory.decodeResource(resources, R.mipmap.test_image), Watermark.BOTTOM_CENTER,255)
                iv_image.setImageBitmap(bitmap)
            }
        }
        tv_Button_RT.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.RIGHT_TOP, 30f,255)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        BitmapFactory.decodeResource(resources, R.mipmap.test_image), Watermark.RIGHT_TOP,255)
                iv_image.setImageBitmap(bitmap)
            }
        }
        tv_Button_RC.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.RIGHT_CENTER, 30f,255)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        BitmapFactory.decodeResource(resources, R.mipmap.test_image), Watermark.RIGHT_CENTER,255)
                iv_image.setImageBitmap(bitmap)
            }
        }
        tv_Button_RB.setOnClickListener {
            if (str ==strings[0]) {
                val bitmap = Watermark.getInstance().createTextWatermark(BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        "测试添加水印", Color.RED, Watermark.RIGHT_BOTTOM, 30f,255)
                iv_image.setImageBitmap(bitmap)
            }else{
                val bitmap = Watermark.getInstance().createPictureWatermark(this,BitmapFactory.decodeResource(resources, R.mipmap.test_watermark_image),
                        R.mipmap.test_image, Watermark.RIGHT_BOTTOM,255)
                iv_image.setImageBitmap(bitmap)
            }
        }

    }
}
