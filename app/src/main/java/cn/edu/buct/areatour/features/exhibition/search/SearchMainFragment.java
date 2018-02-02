package cn.edu.buct.areatour.features.exhibition.search;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/26
 *     desc   : 搜索展品、景区，使用FloatingSearchView开源库
 *     version: 1.0
 * </pre>
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.jaeger.library.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;


public class SearchMainFragment extends Fragment {

    @BindView(R.id.search_center_content)
    RecyclerView searchCenterContent;
    @BindView(R.id.search_top_position)
    FrameLayout searchTopPosition;

    Unbinder unbinder;
    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;
    private Toolbar searchToolBar;
    private RecyclerView searchContentRecyclerView;

    //存放听写分析结果文本
    private HashMap<String, String> hashMapTexts = new LinkedHashMap<String, String>();

    RecognizerDialog dialog;  //讯飞提示框





    /**
     * 构造函数
     */
    public SearchMainFragment() {
    }

    /**
     * 在这个时期中绘制搜索界面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchContentRecyclerView = view.findViewById(R.id.search_center_content);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.color_white));

    }

    /**
     * fragment布局绘制完毕时候会回调这个函数
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchContentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchContentRecyclerView.setAdapter(new SearchContentAdapter());
    }


    @Override
    public void onStart() {
        super.onStart();
        SpeechUtility.createUtility(getActivity(), SpeechConstant.APPID +"=59fdcbb6");
        floatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_voice_rec) {
                    //点击语音识别菜单按钮
                    searchVoice();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.green_primary));
    }

    /**
     * 语音搜索实现
     */
    private void searchVoice(){

        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mTts = SpeechRecognizer.createRecognizer(UIUtils.getContext(),null);
        // 交互动画
        dialog = new RecognizerDialog(getActivity(), null);

        // 2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        mTts.setParameter(SpeechConstant.DOMAIN, "iat"); // domain:域名
        mTts.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mTts.setParameter(SpeechConstant.ACCENT, "mandarin"); // mandarin:普通话
        mTts.setParameter(SpeechConstant.ASR_PTT, "0");

        //3.开始听写
        dialog.setListener(new RecognizerDialogListener() {  //设置对话框

            @Override
            public void onResult(RecognizerResult results, boolean isLast) {
                // TODO 自动生成的方法存根
                Log.d("Result", results.getResultString());
                //(1) 解析 json 数据<< 一个一个分析文本 >>
                StringBuffer strBuffer = new StringBuffer();
                try {
                    JSONTokener tokener = new JSONTokener(results.getResultString());
                    Log.i("TAG", "Test"+results.getResultString());
                    Log.i("TAG", "Test"+results.toString());
                    JSONObject joResult = new JSONObject(tokener);

                    JSONArray words = joResult.getJSONArray("ws");
                    for (int i = 0; i < words.length(); i++) {
                        // 转写结果词，默认使用第一个结果
                        JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                        JSONObject obj = items.getJSONObject(0);
                        strBuffer.append(obj.getString("w"));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // (2)读取json结果中的sn字段
                String sn = null;

                try {
                    JSONObject resultJson = new JSONObject(results.getResultString());
                    sn = resultJson.optString("sn");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //(3) 解析语音文本<< 将文本叠加成语音分析结果  >>
                hashMapTexts.put(sn, strBuffer.toString());
                StringBuffer resultBuffer = new StringBuffer();  //最后结果
                for (String key : hashMapTexts.keySet()) {
                    resultBuffer.append(hashMapTexts.get(key));
                }

                floatingSearchView.setSearchFocused(true);
                floatingSearchView.setSearchText(resultBuffer.toString());
            }



            @Override
            public void onError(SpeechError error) {
                // TODO 自动生成的方法存根
                error.getPlainDescription(true);
            }
        });

        dialog.show();  //显示对话框

    }



}
