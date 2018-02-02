package cn.edu.buct.areatour.features.exhibition.search.searchbyimage;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.ALog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.edu.buct.areatour.R;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/08
 *     desc   : 以图搜索之关键词的碎片，即ViewPager的第二页的碎片
 *     version: 1.0
 * </pre>
 */

public class SearchImageByWordFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.search_by_word_button)
    FloatingActionButton searchByWordButton;
    @BindView(R.id.search_by_word_web_view)
    WebView searchByWordWebView;
    @BindView(R.id.search_by_word_hint)
    TextView searchByWordHint;

    private String requestUrl = "http://image.baidu.com/search/wiseala?tn=wiseala&active=1&ie=utf8&from=index&fmpage=index&pos=top&word=";

    public SearchImageByWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_image_by_word, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.search_by_word_button)
    public void onViewClicked() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(getActivity());
        final View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.item_search_input_dialog, null);
        customizeDialog.setTitle("输入搜索关键词");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("搜索",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text =
                                dialogView.findViewById(R.id.search_by_word_input);
                        String loadUrl = requestUrl + edit_text.getText();
                        ALog.d(loadUrl);
                        searchByWordHint.setVisibility(View.GONE);

                        searchByWordWebView.getSettings().setJavaScriptEnabled(true);
                        searchByWordWebView.setWebViewClient(new WebViewClient());
                        searchByWordWebView.loadUrl(loadUrl);
                    }
                });
        customizeDialog.show();
    }
}
