package cn.edu.buct.areatour.features.audioplay;





import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.Service.MusicService;
import cn.edu.buct.areatour.common.activity.BaseActivity;
import cn.edu.buct.areatour.common.module.AudioModule;





/**

 * <pre>

 *     author : hewro 陈弘扬 曹原

 *     e-mail : ihewro@163.com

 *     time   : 2017/11/12

 *     desc   : 音频详情页面

 *     version: 1.0

 * </pre>

 */



public class AudioDetailActivity extends BaseActivity {



    @BindView(R.id.toolbar)

    Toolbar toolbar;

    @BindView(R.id.audio_detail_tab)

    TabLayout audioDetailTab;

    @BindView(R.id.audio_detail_viewPager)

    ViewPager audioDetailViewPager;

    @BindView(R.id.audio_detail_app_bar)

    AppBarLayout audioDetailAppBar;

    @BindView(R.id.collapsing_toolbar)

    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.playButton)

    ButtonBarLayout playButton;

    @BindView(R.id.play_Button1)

    ImageButton playbutton1;

    @BindView(R.id.play_Button2)

    ImageButton playbutton2;

    @BindView(R.id.play_Text)

    TextView playtext;

    @BindView(R.id.ic_pre)

    ImageButton prebutton;

    @BindView(R.id.ic_next)

    ImageButton nextbutton;

    @BindView(R.id.time_total)

    TextView totaltime;

    @BindView(R.id.time_current)

    TextView currenttime;

    @BindView(R.id.mediacontroller_progress)

    SeekBar mediacontrollerprogress;







    private CollapsingToolbarLayoutState state;

    private boolean isPlaying=false;

    private List<AudioModule> audioModules;

    private int current;

    private int seconds;

    private ProgressThread progressThread =new ProgressThread();



    private enum CollapsingToolbarLayoutState {

        EXPANDED,//展开状态

        COLLAPSED, //折叠状态

        INTERMEDIATE//中间状态

    }

    //将activity和service绑定，让activity里能调用service里的方法
   private MusicService.MusicBinder musicBinder;
    private ServiceConnection connection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            musicBinder=(MusicService.MusicBinder) service;
            seconds=musicBinder.getSeconds();
            String ttime=seconds/60+":"+seconds%60;
            totaltime.setText(ttime);
            //进度条从零开始前进
            progressThread.init();
            new Thread(progressThread).start();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
   public void BindService(){
           //绑定activity和service
           Intent intent=new Intent(AudioDetailActivity.this,MusicService.class);
           bindService(intent, connection,BIND_AUTO_CREATE);

   }
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_audio_detail);

        ButterKnife.bind(this);


        //获取传递进来的音频列表和要播放的音频索引
        Intent getIntent = getIntent();
        audioModules=(List<AudioModule>)getIntent.getSerializableExtra("mAudioList");
        current=getIntent.getIntExtra("current",0);
        //加载页面就开始播放音频
        Intent startIntent = new Intent(AudioDetailActivity.this,MusicService.class);
        startIntent.setAction("FIRST_PLAY_ACTION");
        startIntent.putExtra("audioModules",(Serializable)audioModules);
        startIntent.putExtra("current",current);
        startService(startIntent);
        isPlaying=true;
        playbutton1.setImageResource(R.drawable.music_pause);
        playbutton2.setImageResource(R.drawable.music_pause);
        playtext.setText("正在播放");


        BindService();


        StatusBarUtil.setTranslucentForImageView(this, toolbar);



        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        initTabLayout();


        //点击播放按钮的事件

        playbutton1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                audioDetailAppBar.setExpanded(true);
                if(isPlaying){
                    isPlaying=false;
                    playbutton1.setImageResource(R.drawable.music_play);
                    playbutton2.setImageResource(R.drawable.music_play);
                    Intent startIntent = new Intent(AudioDetailActivity.this,MusicService.class);
                    startIntent.setAction("PAUSE_ACTION");
                    startService(startIntent);
                    playtext.setText("立即播放");
                    progressThread.Pause();

                }else{
                    isPlaying=true;
                    playbutton1.setImageResource(R.drawable.music_pause);
                    playbutton2.setImageResource(R.drawable.music_pause);
                    Intent startIntent = new Intent(AudioDetailActivity.this,MusicService.class);
                    startIntent.setAction("PLAY_ACTION");
                    startService(startIntent);
                    playtext.setText("正在播放");
                    progressThread.Continue();
                }
            }

        });

        playbutton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                audioDetailAppBar.setExpanded(true);
                if(isPlaying){
                    isPlaying=false;
                    playbutton1.setImageResource(R.drawable.music_play);
                    playbutton2.setImageResource(R.drawable.music_play);
                    Intent startIntent = new Intent(AudioDetailActivity.this,MusicService.class);
                    startIntent.setAction("PAUSE_ACTION");
                    startService(startIntent);
                    playtext.setText("立即播放");

                }else{
                    isPlaying=true;
                    playbutton1.setImageResource(R.drawable.music_pause);
                    playbutton2.setImageResource(R.drawable.ic_pause);
                    Intent startIntent = new Intent(AudioDetailActivity.this,MusicService.class);
                    startIntent.setAction("PLAY_ACTION");
                    startService(startIntent);
                    playtext.setText("正在播放");

                }

            }

        });

        prebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isPlaying=true;
                playbutton1.setImageResource(R.drawable.music_pause);
                playbutton2.setImageResource(R.drawable.ic_pause);
                playtext.setText("正在播放");
                Intent startIntent = new Intent(AudioDetailActivity.this,MusicService.class);
                startIntent.setAction("PREVIOUS_ACTION");
                startService(startIntent);
                //停止线程，重新绑定服务
                progressThread.Stop();
                unbindService(connection);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                BindService();



            }

        });
        nextbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isPlaying=true;
                playbutton1.setImageResource(R.drawable.music_pause);
                playbutton2.setImageResource(R.drawable.ic_pause);
                playtext.setText("正在播放");
                Intent startIntent = new Intent(AudioDetailActivity.this,MusicService.class);
                startIntent.setAction("NEXT_ACTION");
                startService(startIntent);
                //停止线程,绑定服务
                progressThread.Stop();
                unbindService(connection);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                BindService();
            }

        });
        mediacontrollerprogress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {//数值的改变
               if(arg1==100){
                   nextbutton.performClick();
               }
            }
            @Override
            public void onStartTrackingTouch(SeekBar arg0) {//开始拖动

            }
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {//停止拖动
                if(!isPlaying){
                    playbutton1.performClick();
                }
                progressThread.Pause();
                progressThread.SetTime((int)(arg0.getProgress()*seconds/100));
                musicBinder.setTime((int)(arg0.getProgress()*seconds/100));
                progressThread.Continue();
            }

        });


    }





    /**

     * 初始化TabLayout 数据

     */

    private void initTabLayout() {

        setViewPager(audioDetailViewPager);

        audioDetailTab.setupWithViewPager(audioDetailViewPager);

        audioDetailTab.setTabMode(TabLayout.MODE_SCROLLABLE);

    }



    /**

     * 设置ViewPager

     */

    private void setViewPager(ViewPager viewPager) {



        //碎片列表

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        fragmentList.add(new AudioIntroFragment());

//        fragmentList.add(new SpotItemCommentFragment());



        //标题列表

        List<String> pageTitleList = new ArrayList<>();

        pageTitleList.add("详情");

        pageTitleList.add("评论");



        //新建适配器

        AudioIntroViewPagerAdapter adapter = new AudioIntroViewPagerAdapter(getSupportFragmentManager(), fragmentList, pageTitleList);



        //设置ViewPager

        viewPager.setAdapter(adapter);



        changeAppBar();

    }







    private void changeAppBar() {

        audioDetailAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override

            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {



                if (verticalOffset == 0) {

                    if (state != CollapsingToolbarLayoutState.EXPANDED) {

                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开

                        collapsingToolbar.setTitle("EXPANDED");//设置title为EXPANDED

                    }

                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {

                        collapsingToolbar.setTitle("");//设置title不显示

                        playButton.setVisibility(View.VISIBLE);//显示播放按钮

                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠

                    }

                } else {

                    if (state != CollapsingToolbarLayoutState.INTERMEDIATE) {

                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {

                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                            playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮

                        }

                        collapsingToolbar.setTitle("INTERMEDIATE");//设置title为INTERNEDIATE

                        state = CollapsingToolbarLayoutState.INTERMEDIATE;//修改状态标记为中间

                    }

                }

            }

        });

    }



    /**

     * 点击toolbar上的按钮事件

     *

     * @param item

     * @return

     */

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            finish();

        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
    //修改进度条的线程
    public class ProgressThread  implements Runnable {
        private boolean isPause=false;
        private boolean isStop=false;
        private int i=0;
        public void init(){
            isPause=false;
            isStop=false;
        }
        public void Pause(){
            isPause=true;
        }
        public void Stop(){
            isPause=true;
            isStop=true;
        }
        public void Continue (){
            isPause=false;
        }
        public void SetTime (int time){
            i=time;
        }

        @Override
        public void run() {
            mediacontrollerprogress.setProgress(0);
            i=0;
            while(!isStop){
                while(!isPause && i<=seconds){
                    Message message =new Message();
                    message.what = i;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    i++;
                }
                if(i>seconds){
                    Stop();
                }
            }
        }
    }
        //接收修改进度条的线程的数据并在handler里修改ui界面
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg){
            if(msg.what>=0){
                String ctime=msg.what/60+":"+msg.what%60;
                currenttime.setText(ctime);
                mediacontrollerprogress.setProgress(msg.what*100/seconds);
            }
        }
    };
}