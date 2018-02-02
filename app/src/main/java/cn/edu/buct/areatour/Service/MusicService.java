package cn.edu.buct.areatour.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.buct.areatour.common.module.AudioModule;

public class MusicService extends Service {
    private MusicBinder mBinder =new MusicBinder();
    public class MusicBinder extends Binder{
        public int getSeconds(){
            if(mMediaPlayer.isPlaying()){
                return mMediaPlayer.getDuration()/1000;
            }else{
                return 0;
            }
        }
        public void setTime(int time){
            mMediaPlayer.seekTo(time*1000);
        }

    }
    private MediaPlayer mMediaPlayer; // 声明播放器
    //private Cursor mCursor; // 声明游标
    private int mPlayPosition = 0; // 当前播放的歌曲

    // 注册意图
    public static final String FIRST_PLAY_ACTION = "FIRST_PLAY_ACTION";
    public static final String PLAY_ACTION = "PLAY_ACTION";
    public static final String PAUSE_ACTION = "PAUSE_ACTION";
    public static final String NEXT_ACTION = "NEXT_ACTION";
    public static final String PREVIOUS_ACTION = "PREVIOUS_ACTION";

    private List<String> datasorce=new ArrayList<String>();

    @Override
    public IBinder onBind(Intent arg0) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
//        mMediaPlayer =  MediaPlayer.create(this, Uri.fromFile(new File(filePath)));

        // 通过一个URI可以获取所有音频文件
       /* Uri MUSIC_URL = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // 这里我过滤了一下，因为我机里有些音频文件是游戏音频，很短
        // 我这里作了处理，默认大于10秒的可以看作是系统音乐
        mCursor = getContentResolver().query(MUSIC_URL, mCursorCols,
                "duration > 10000", null, null);*/
    }

    @Override
    public int  onStartCommand(Intent intent, int flags,int startId) {
        super.onStartCommand(intent, flags ,startId);

        // 根据不同的action，做不同的相应
        String action = intent.getAction();
        //播放
        if (action.equals(FIRST_PLAY_ACTION)){
            //获取传递进来的音频列表和要播放的音频索引
            List<AudioModule> audioModules=(List<AudioModule>)intent.getSerializableExtra("audioModules");
            for (int i=0;i<audioModules.size();i++){
                datasorce.add("http://39.106.122.103:8080/documents/audio/"+audioModules.get(i).getAudioName()+".mp3");
            }
            mPlayPosition=intent.getIntExtra("current",0);
            firstplay();
        }else if (action.equals(PLAY_ACTION)) {
            play();

            //暂停
        } else if (action.equals(PAUSE_ACTION)) {
            pause();
            //下一首
        } else if (action.equals(NEXT_ACTION)) {
            next();
            //前一首
        } else if (action.equals(PREVIOUS_ACTION)) {
            previous();
        }

        return START_NOT_STICKY;
    }

    // 打开音乐播放界面播放
    public void firstplay() {
        //初始化音乐播放器
        inite();
    }
    // 播放音乐
    public void play() {
        //初始化音乐播放器
        mMediaPlayer.start();
    }

    // 暂停时，结束服务
    public void pause() {
        //暂停音乐播放
        mMediaPlayer.pause();
    }

    //上一首
    public void previous() {
        //得到前一首的歌曲
        if (mPlayPosition == 0) {
            mPlayPosition = datasorce.size() - 1;
        } else {
            mPlayPosition--;
        }
        //开始播放
        inite();
    }

    // 下一首
    public void next() {
        //得到后一首歌曲
        if (mPlayPosition == datasorce.size() - 1) {
            mPlayPosition = 0;
        } else {
            mPlayPosition++;
        }
        //开始播放
        inite();
    }

    // 初始化播放器
    public void inite() {
        //充值MediaPlayer
        mMediaPlayer.reset();
        // 获取歌曲位置
        String dataSource = datasorce.get(mPlayPosition);
//        mMediaPlayer =  MediaPlayer.create(this, Uri.fromFile(new File(dataSource)));
        // 歌曲信息
       /* String info = getInfoByPosition(mCursor, mPlayPosition);
        // 用Toast显示歌曲信息
        Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT)
                .show();*/
        try {
            // 播放器绑定资源
            mMediaPlayer.setDataSource(dataSource);
            // 播放器准备
            mMediaPlayer.prepare();
            // 播放
            mMediaPlayer.start();
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (IllegalStateException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /*// 根据位置来获取歌曲位置
    public String getDateByPosition(Cursor c, int position) {
        c.moveToPosition(position);
        int dataColumn = c.getColumnIndex(MediaStore.Audio.Media.DATA);
        String data = c.getString(dataColumn);
        return data;
    }

    // 获取当前播放歌曲演唱者及歌名
    public String getInfoByPosition(Cursor c, int position) {
        c.moveToPosition(position);
        int titleColumn = c.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int artistColumn = c.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        String info = c.getString(artistColumn) + " "
                + c.getString(titleColumn);
        return info;

    }*/

    // 服务结束时要释放MediaPlayer
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
    }
}
