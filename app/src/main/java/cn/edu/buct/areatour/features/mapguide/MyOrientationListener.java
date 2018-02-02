package cn.edu.buct.areatour.features.mapguide;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
/**
 * <pre>
 *     author : iSharpener 李炳
 *     e-mail : 1437138419@qq.com
 *     time   : 2017/11/1
 *     desc   : 地图方向传感器监听类
 *     version: 1.0
 * </pre>
 */
public class MyOrientationListener implements SensorEventListener{
    //传感器管理者
    private SensorManager mSensorManager;
    //上下文
    private Context mContext;
    //传感器
    private Sensor mSensor;

    //方向传感器有三个坐标，现在只关注X
    private float mLastX;
    //构造函数
    public MyOrientationListener(Context context) {
        this.mContext = context;
    }
    //开始监听
    @SuppressWarnings("deprecation")
    public void start(){
        //获得传感器管理者
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager!=null){//是否支持
            //获得方向传感器
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
        if(mSensor!=null){//如果手机有方向传感器，精度可以自己去设置，注册方向传感器
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }
    //结束监听
    public void stop(){
        //取消注册的方向传感器
    //   mSensorManager.unregisterListener(this);

    }
    //传感器发生改变时
    @SuppressWarnings("deprecation")
    @Override
    public void onSensorChanged(SensorEvent event) {
        //判断返回的传感器类型是不是方向传感器
        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            //只获取x的值
            float x = event.values[SensorManager.DATA_X];
            //为了防止经常性的更新
            if(Math.abs(x-mLastX)>= 0){
                if(onOrientationListener!=null){
                    onOrientationListener.onOrientationChanged(x);
                }
            }
            mLastX = x;
        }
    }

    //当传感器精度发生改变，当前不用
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    private OnOrientationListener onOrientationListener;

    public void setOnOrientationListener(OnOrientationListener onOrientationListener) {
        this.onOrientationListener = onOrientationListener;
    }

    //回掉方法
    public interface OnOrientationListener{
        void onOrientationChanged(float x);
    }
}