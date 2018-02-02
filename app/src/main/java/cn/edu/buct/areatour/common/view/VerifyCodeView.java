package cn.edu.buct.areatour.common.view;

import android.widget.RelativeLayout;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/11/11
 *     desc   : 自定义输入验证码组件
 *     version: 1.0
 * </pre>
 */



public class VerifyCodeView extends RelativeLayout {
    private EditText editText;
    private TextView[] textViews;
    private View[] outLineViews;
    private static int MAX = 6;
    private String inputContent;

    public VerifyCodeView(Context context) {
        this(context, null);
    }

    public VerifyCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_verify_code, this);

        textViews = new TextView[MAX];
        textViews[0] = (TextView) findViewById(R.id.tv_0);
        textViews[1] = (TextView) findViewById(R.id.tv_1);
        textViews[2] = (TextView) findViewById(R.id.tv_2);
        textViews[3] = (TextView) findViewById(R.id.tv_3);
        textViews[4] = (TextView) findViewById(R.id.tv_4);
        textViews[5] = (TextView) findViewById(R.id.tv_5);
        outLineViews = new View[MAX];
        outLineViews[0] = findViewById(R.id.out_line_0);
        outLineViews[1] = findViewById(R.id.out_line_1);
        outLineViews[2] = findViewById(R.id.out_line_2);
        outLineViews[3] = findViewById(R.id.out_line_3);
        outLineViews[4] = findViewById(R.id.out_line_4);
        outLineViews[5] = findViewById(R.id.out_line_5);

        editText = (EditText) findViewById(R.id.edit_text_view);

        editText.setCursorVisible(false);//隐藏光标
        setEditTextListener();
    }

    private void setEditTextListener() {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                outLineViews[start].setBackgroundColor(UIUtils.getResources().getColor(R.color.green_primary));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputContent = editText.getText().toString();

                if (inputCompleteListener != null) {
                    if (inputContent.length() >= MAX) {
                        inputCompleteListener.inputComplete();
                    } else {
                        inputCompleteListener.invalidContent();
                    }
                }

                for (int i = 0; i < MAX; i++) {
                    if (i < inputContent.length()) {
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                    } else {
                        textViews[i].setText("");
                    }
                }
            }
        });
    }


    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {

        void inputComplete();

        void invalidContent();
    }

    public String getEditContent() {
        return inputContent;
    }

}