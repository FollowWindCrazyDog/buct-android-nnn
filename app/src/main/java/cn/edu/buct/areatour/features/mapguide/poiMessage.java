package cn.edu.buct.areatour.features.mapguide;

import android.os.Parcel;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by Xiaopeng on 2017/11/10.
 */
public class poiMessage implements SearchSuggestion{
    private String poiName;
    private boolean mIsHistory = false;

    public poiMessage(String suggestion) {
        this.poiName = suggestion.toLowerCase();
    }

    public poiMessage(Parcel source) {
        this.poiName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return poiName;
    }

    public static final Creator<poiMessage> CREATOR = new Creator<poiMessage>() {
        @Override
        public poiMessage createFromParcel(Parcel in) {
            return new poiMessage(in);
        }
        @Override
        public poiMessage[] newArray(int size) {
            return new poiMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poiName);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}
