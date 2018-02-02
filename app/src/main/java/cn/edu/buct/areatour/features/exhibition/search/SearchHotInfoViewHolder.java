package cn.edu.buct.areatour.features.exhibition.search;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.buct.areatour.R;
import cn.edu.buct.areatour.common.utils.UIUtils;


/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2017/10/27
 *     desc   : search界面的“大家都在搜”部分（显示热门搜索信息）
 *     version: 1.0
 * </pre>
 */
public class SearchHotInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.faxian_center_wiki)
    MyScrollView mWiki;
    @BindView(R.id.faxian_center_tv)
    TextView     mTv;
    @BindView(R.id.faxian_bottom_arrow)
    ImageView    mIvArrow;
    @BindView(R.id.faxian_center_container)
    LinearLayout mContainer;
    @BindView(R.id.search_look_more)
    RelativeLayout lookMoreLayout;

    private boolean isOpened;

    private final String[] mDatas;

    public SearchHotInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mDatas = new String[]{"故宫","天安门","养心殿","太和殿","北海公园","天桥","御花园","恭王府","南锣鼓巷","故宫","天安门","养心殿","太和殿","北海公园","天桥","御花园","恭王府","南锣鼓巷","故宫","天安门","养心殿","太和殿","北海公园","天桥","御花园","恭王府","南锣鼓巷","故宫","天安门","养心殿","太和殿","北海公园","天桥","御花园","恭王府","南锣鼓巷","故宫","天安门","养心殿","太和殿","北海公园","天桥","御花园","恭王府","南锣鼓巷"};
            initData();

            mTv.setOnClickListener(this);
            mIvArrow.setOnClickListener(this);
            lookMoreLayout.setOnClickListener(this);
        }


    /**
     *初始化数据
     *
     */

    private void initData() {
        if (mDatas == null) {
            mContainer.setVisibility(View.GONE);
            return;
        }
        mContainer.setVisibility(View.VISIBLE);

        FlowLayout mLayout = new FlowLayout(UIUtils.getContext());
        mWiki.addView(mLayout);

        ViewGroup.LayoutParams layoutParams = mWiki.getLayoutParams();
        layoutParams.height = UIUtils.dip2px(80);
        mWiki.setLayoutParams(layoutParams);

        mLayout.setSpace(UIUtils.dip2px(12), UIUtils.dip2px(12));

        for (int i = 0; i < mDatas.length; i++) {

            TextView tv = new TextView(UIUtils.getContext());
            tv.setText(mDatas[i]);
            tv.setPadding(UIUtils.dip2px(5), UIUtils.dip2px(5), UIUtils.dip2px(5), UIUtils.dip2px(5));
            //设置字体颜色的选择器
            ColorStateList colorSateList = (ColorStateList) UIUtils.getResources().getColorStateList(R.color.faxian_text_selector);
            tv.setTextColor(colorSateList);

            GradientDrawable normal = new GradientDrawable();
            normal.setShape(GradientDrawable.RECTANGLE);
            normal.setCornerRadius(UIUtils.dip2px(3));
            normal.setColor(Color.parseColor("#ffffff"));

            GradientDrawable pressed = new GradientDrawable();
            pressed.setShape(GradientDrawable.RECTANGLE);
            pressed.setCornerRadius(UIUtils.dip2px(3));
            pressed.setColor(Color.parseColor("#97445C"));

            StateListDrawable selector = new StateListDrawable();
            selector.addState(new int[]{android.R.attr.state_pressed}, pressed);
            selector.addState(new int[]{}, normal);

            tv.setBackgroundDrawable(selector);
            final int index = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), "" + mDatas[index], Toast.LENGTH_SHORT).show();
                }
            });
            mLayout.addView(tv);
        }
    }

    /**
     * 收起、折叠按钮点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (!isOpened) {
            ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180).start();
            animate(UIUtils.dip2px(80), UIUtils.dip2px(200));
            mTv.setText("收起");
            //设置可滑动
            mWiki.setCanScroll(true);
        } else {
            ObjectAnimator.ofFloat(mIvArrow, "rotation", -180, 0).start();
            animate(UIUtils.dip2px(200), UIUtils.dip2px(80));
            mTv.setText("查看更多");
            //设置不可滑动
            mWiki.setCanScroll(false);
        }
        isOpened = !isOpened;
    }

    /**
     * 收起折叠动画显示
     *
     * @param start
     * @param end
     */
    private void animate(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mWiki.getLayoutParams();
                layoutParams.height = value;
                mWiki.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }
}
